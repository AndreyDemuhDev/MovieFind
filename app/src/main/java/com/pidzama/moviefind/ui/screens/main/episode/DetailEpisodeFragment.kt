package com.pidzama.moviefind.ui.screens.main.episode

import android.app.AlertDialog
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

@AndroidEntryPoint
class DetailEpisodeFragment : Fragment() {

    private lateinit var binding: FragmentDetailEpisodeBinding
    private val viewModelEpisode: EpisodeViewModel by viewModels()
    private val args: DetailEpisodeFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
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
            val chosenIntent = Intent.createChooser(intent, resources.getText(R.string.choose_app))
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
                binding.descriptionEpisode.text =
                    Html.fromHtml(it.summary, Html.FROM_HTML_MODE_COMPACT)
                binding.nameEpisode.text = it.name
            }
        }
        viewModelEpisode.getOneEpisode(args.idEpisode)
    }

    private fun createAlertDialog() {
        AlertDialog.Builder(requireContext())
            .setCancelable(false)
            .setTitle(R.string.sorry)
            .setMessage(R.string.video_not_available)
            .setNegativeButton(R.string.OK) { _, _ ->
                Toast.makeText(requireContext(), R.string.thanks, Toast.LENGTH_SHORT)
                    .show()
            }
            .create()
            .show()
    }

}