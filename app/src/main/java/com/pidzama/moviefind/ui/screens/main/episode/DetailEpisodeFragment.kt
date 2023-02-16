package com.pidzama.moviefind.ui.screens.main.episode

import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.text.Html
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.pidzama.moviefind.R
import com.pidzama.moviefind.databinding.FragmentDetailEpisodeBinding
import com.pidzama.moviefind.ui.screens.main.episode.adapter.EpisodeViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel

@AndroidEntryPoint
class DetailEpisodeFragment : Fragment() {

    private lateinit var binding: FragmentDetailEpisodeBinding
    private val viewModelEpisode: EpisodeViewModel by viewModels()
    private val args: DetailEpisodeFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailEpisodeBinding.inflate(layoutInflater, container, false)
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
            val chosenIntent = Intent.createChooser(intent, "Выберите приложение")
            startActivity(chosenIntent)
        }

        binding.buttonPlay.setOnClickListener {
            createAlertDialog()
        }

        binding.buttonDownload.setOnClickListener {
            createAlertDialog()
        }

        viewModelEpisode.run {
            currentOneEpisode.observe(viewLifecycleOwner) {
                Glide.with(requireContext())
                    .load(it.image?.medium)
                    .into(binding.imageEpisode)
                binding.numberEpisodeText.text = it.number.toString()
                binding.numberSeasonText.text = it.season.toString()
                binding.rating.text = it.rating.average.toString()
                binding.descriptionEpisode.text = Html.fromHtml(it.summary, Html.FROM_HTML_MODE_COMPACT)
                binding.nameEpisode.text = it.name
            }
        }
        viewModelEpisode.getOneEpisode(args.idEpisode)
    }

    private fun createAlertDialog(){
        val alertDialog = Dialog(requireContext())
        alertDialog.setCancelable(true)
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Ooops!")
        builder.setMessage("Video not available at the moment, please try again later")
        builder.setNegativeButton("OK"){dialog, i ->
            Toast.makeText(requireContext(), "Thanks for understanding", Toast.LENGTH_SHORT).show()
        }
        builder.show()
    }

}