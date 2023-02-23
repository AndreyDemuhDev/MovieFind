package com.pidzama.moviefind.ui.main.profile

import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.pidzama.moviefind.R
import com.pidzama.moviefind.data.model.user.User
import com.pidzama.moviefind.databinding.DialogEnterInfoBinding
import com.pidzama.moviefind.databinding.FragmentEditProfileBinding
import com.pidzama.moviefind.ui.auth.login.AuthorisationViewModel
import com.pidzama.moviefind.utils.Constants.Firebase.PATH_USER
import dagger.hilt.android.AndroidEntryPoint

@Suppress("DEPRECATION")
@AndroidEntryPoint
class EditProfileFragment : Fragment() {

    private lateinit var binding: FragmentEditProfileBinding
    private val viewModel: AuthorisationViewModel by viewModels()
    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase
    private lateinit var storage: FirebaseStorage
    private lateinit var imageUser: Uri
    private lateinit var dialog: AlertDialog.Builder

    var imageURL: String? = null
    var uri: Uri? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEditProfileBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        storage = FirebaseStorage.getInstance()


        dialog = AlertDialog.Builder(requireContext())
            .setMessage(R.string.please_wait)
            .setCancelable(false)

        binding.buttonChangePhoto.setOnClickListener {
            val intent = Intent()
            intent.action = Intent.ACTION_GET_CONTENT
            intent.type = "image/*"
            startActivityForResult(intent, 1)
        }

        binding.buttonSaveChange.setOnClickListener {
            uploadData()
        }


        val activityResultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == RESULT_OK) {
                val data = result.data
                uri = data!!.data
                binding.buttonChangePhoto.setImageURI(uri)
            } else {
                Toast.makeText(requireContext(), R.string.image_not_found, Toast.LENGTH_SHORT)
                    .show()
            }
        }

        binding.buttonChangePhoto.setOnClickListener {
            val photoPicker = Intent(Intent.ACTION_GET_CONTENT)
            photoPicker.type = "image/*"
            activityResultLauncher.launch(photoPicker)
        }
        binding.buttonSaveChange.setOnClickListener {
            saveData()

        }

        binding.emailText.text = viewModel.getEmail()

        binding.buttonBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.buttonName.setOnClickListener {
            showChangeParametersUserName()
        }

        binding.buttonAge.setOnClickListener {
            showChangeParametersUserAge()
        }

        binding.buttonCountry.setOnClickListener {
            showChangeParametersUserCountry()
        }

        binding.buttonPhone.setOnClickListener {
            showChangeParametersUserPhone()
        }

    }

    private fun saveData() {
        val storageRef = FirebaseStorage.getInstance().reference.child("UserImage")
            .child(uri!!.lastPathSegment!!)

        val builder = android.app.AlertDialog.Builder(requireContext())
        builder.setCancelable(true)
        builder.setView(R.layout.loading_alert_dialog)
        val dialog = builder.create()
        dialog.show()

        storageRef.putFile(uri!!).addOnSuccessListener { task ->
            val uriTask = task.storage.downloadUrl
            while (!uriTask.isComplete);
            val uriImage = uriTask.result
            imageURL = uriImage.toString()
            uploadData()
            dialog.dismiss()
        }.addOnFailureListener {
            dialog.dismiss()
        }
    }

    private fun uploadData() {
        val uid = auth.currentUser?.uid
        val name = binding.name.text.toString()
        val age = binding.age.text.toString()
        val email = viewModel.getEmail()
        val country = binding.country.text.toString()
        val phone = binding.phone.text.toString()

        val user = User(uid, name, age, email, country, phone, imageURL)

        FirebaseDatabase.getInstance().getReference(PATH_USER).child(auth.uid!!).setValue(user)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(requireContext(), R.string.saved, Toast.LENGTH_SHORT).show()
                }
            }.addOnFailureListener {
                Toast.makeText(requireContext(), R.string.error_save, Toast.LENGTH_SHORT).show()
            }
    }

    private fun showChangeParametersUserName() {
        val dialogBinding = DialogEnterInfoBinding.inflate(layoutInflater)
        dialogBinding.inputText.setText(R.string.enter_name)
        val dialog = AlertDialog.Builder(requireContext())
            .setTitle(R.string.name)
            .setView(dialogBinding.root)
            .setPositiveButton(R.string.confirm, null)
            .create()

        dialog.setOnShowListener {
            dialogBinding.inputText.requestFocus()
            showKeyboard(dialogBinding.inputText)

            dialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener {
                val enterText = dialogBinding.inputText.text.toString()
                if (enterText.isBlank()) {
                    dialogBinding.inputText.error = resources.getText(R.string.value_is_empty)
                    return@setOnClickListener
                }
                binding.name.text = enterText
                dialog.dismiss()
            }
        }
        dialog.setOnDismissListener { hideKeyboard(dialogBinding.inputText) }
        dialog.show()
    }

    private fun showChangeParametersUserAge() {
        val dialogBinding = DialogEnterInfoBinding.inflate(layoutInflater)
        dialogBinding.inputText.setText(R.string.enter_age)
        val dialog = AlertDialog.Builder(requireContext())
            .setTitle(R.string.age)
            .setView(dialogBinding.root)
            .setPositiveButton(R.string.confirm, null)
            .create()

        dialog.setOnShowListener {
            dialogBinding.inputText.requestFocus()
            showKeyboard(dialogBinding.inputText)

            dialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener {
                val enterText = dialogBinding.inputText.text.toString()
                if (enterText.isBlank()) {
                    dialogBinding.inputText.error = resources.getText(R.string.value_is_empty)
                    return@setOnClickListener
                }
                binding.age.text = enterText
                dialog.dismiss()
            }
        }
        dialog.setOnDismissListener { hideKeyboard(dialogBinding.inputText) }
        dialog.show()
    }

    private fun showChangeParametersUserCountry() {
        val dialogBinding = DialogEnterInfoBinding.inflate(layoutInflater)
        dialogBinding.inputText.setText(R.string.enter_country)
        val dialog = AlertDialog.Builder(requireContext())
            .setTitle(R.string.country)
            .setView(dialogBinding.root)
            .setPositiveButton(R.string.confirm, null)
            .create()

        dialog.setOnShowListener {
            dialogBinding.inputText.requestFocus()
            showKeyboard(dialogBinding.inputText)

            dialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener {
                val enterText = dialogBinding.inputText.text.toString()
                if (enterText.isBlank()) {
                    dialogBinding.inputText.error = resources.getText(R.string.value_is_empty)
                    return@setOnClickListener
                }
                binding.country.text = enterText
                dialog.dismiss()
            }
        }
        dialog.setOnDismissListener { hideKeyboard(dialogBinding.inputText) }
        dialog.show()
    }

    private fun showChangeParametersUserPhone() {
        val dialogBinding = DialogEnterInfoBinding.inflate(layoutInflater)
        dialogBinding.inputText.setText(R.string.enter_phone_number)
        val dialog = AlertDialog.Builder(requireContext())
            .setTitle(R.string.phone)
            .setView(dialogBinding.root)
            .setPositiveButton(R.string.confirm, null)
            .create()

        dialog.setOnShowListener {
            dialogBinding.inputText.requestFocus()
            showKeyboard(dialogBinding.inputText)

            dialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener {
                val enterText = dialogBinding.inputText.text.toString()
                if (enterText.isBlank()) {
                    dialogBinding.inputText.error = resources.getText(R.string.value_is_empty)
                    return@setOnClickListener
                }
                binding.phone.text = enterText
                dialog.dismiss()
            }
        }
        dialog.setOnDismissListener { hideKeyboard(dialogBinding.inputText) }
        dialog.show()
    }

    private fun showKeyboard(view: View) {
        view.post {
            getInputMethodManager(view).showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
        }
    }

    private fun hideKeyboard(view: View) {
        getInputMethodManager(view).hideSoftInputFromWindow(view.windowToken, 0)
    }

    private fun getInputMethodManager(view: View): InputMethodManager {
        val context = view.context
        return context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (data != null) {
            if (data.data != null
            ) {
                data.data!!.also { imageUser = it }
                binding.imageProfile.setImageURI(imageUser)
            }
        }
    }

}