package com.pidzama.moviefind.ui.screens.main.seasons

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.pidzama.moviefind.data.model.episodes.EpisodesItem
import com.pidzama.moviefind.databinding.FragmentDetailSeasonBinding
import com.pidzama.moviefind.ui.screens.main.episode.adapter.EpisodeAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailSeasonFragment : Fragment() {

    private lateinit var binding: FragmentDetailSeasonBinding
    private val viewModelSeason: SeasonViewModel by viewModels()
    private val args: DetailSeasonFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailSeasonBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonBack.setOnClickListener {
            findNavController().popBackStack()
        }

        viewModelSeason.run {
            currentOneSeasonsMovie.observe(viewLifecycleOwner) {
                Glide.with(requireContext())
                    .load(it.image?.medium)
                    .centerCrop()
                    .into(binding.imageSeason)
                binding.seasonNumber.text = it.number.toString()
                binding.numberEpisode.text = it.episodeOrder.toString()
            }
        }
        viewModelSeason.getOneSeason(args.idSeason)

        viewModelSeason.listAllEpisodes.observe(viewLifecycleOwner) {
            setListEpisodes(it)
        }
        viewModelSeason.getListAllEpisodes(args.idSeason)
    }

    private fun setListEpisodes(list: ArrayList<EpisodesItem>) {
        binding.recyclerEpisodes.run {
            if (adapter == null) {
                adapter = EpisodeAdapter {
                    findNavController().navigate(
                        DetailSeasonFragmentDirections.actionDetailSeasonFragmentToDetailEpisodeFragment(
                            it.id,
                            it.season,
                            it.number,
                            it.rating.average.toLong(),
                            it.summary,
                            it.url
                        )
                    )
                }
                layoutManager = LinearLayoutManager(requireContext())
            }
            (adapter as? EpisodeAdapter)?.submitList(list)
        }
    }
}