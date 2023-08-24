package com.pidzama.moviefind.ui.main.episode

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Html
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.pidzama.moviefind.R
import com.pidzama.moviefind.databinding.FragmentDetailEpisodeBinding
import com.pidzama.moviefind.utils.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailEpisodeFragment : Fragment() {

    private lateinit var binding: FragmentDetailEpisodeBinding
    private val viewModelEpisode: EpisodeViewModel by viewModels()
    private val args: DetailEpisodeFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailEpisodeBinding.inflate(
            layoutInflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.buttonShare.setOnClickListener {
            val sendLink = args.urlEpisode
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_TEXT, sendLink)
            val chosenIntent = Intent.createChooser(intent, resources.getText(R.string.choose_app))
            startActivity(chosenIntent)
        }

        binding.buttonPlay.setOnClickListener {
            showAlertDialog()
        }
        binding.buttonDownload.setOnClickListener {
            showAlertDialog()
        }
        viewModelEpisode.run {
            currentOneEpisode.observe(viewLifecycleOwner) {
                Glide.with(requireContext())
                    .load(it.image?.medium ?: R.drawable.ic_no_image)
                    .into(binding.imageEpisode)
                binding.numberEpisodeText.text = it.number.toString()
                binding.numberSeasonText.text = it.season.toString()
                binding.rating.text = it.rating.average.toString()
                binding.descriptionEpisode.text =
                    Html.fromHtml(
                        it.summary ?: resources.getText(R.string.noDescription).toString(),
                        Html.FROM_HTML_MODE_COMPACT
                    )
                binding.nameEpisode.text = it.name
            }
        }
        viewModelEpisode.getOneEpisode(args.idEpisode)
    }

    private fun showAlertDialog() {
        val dialog = Dialog(requireContext())
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.info_alert_dialog)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val btnOK: Button = dialog.findViewById(R.id.button_ok)

        btnOK.setOnClickListener {
            dialog.dismiss()
            requireContext().showToast(R.string.thanks)
        }
        dialog.show()
    }
}