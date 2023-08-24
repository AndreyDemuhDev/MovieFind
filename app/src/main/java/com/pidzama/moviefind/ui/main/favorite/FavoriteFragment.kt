package com.pidzama.moviefind.ui.main.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pidzama.moviefind.databinding.FragmentFavoriteBinding
import com.pidzama.moviefind.ui.main.favorite.adapter.FavoriteMovieAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment : Fragment() {

    private lateinit var binding: FragmentFavoriteBinding
    private val viewModel: FavoriteViewModel by viewModels()
    private lateinit var recyclerFavouriteMovie: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initFavouriteMovie()
    }

    private fun initFavouriteMovie() {
        viewModel.getListFavouriteHero()
        recyclerFavouriteMovie = binding.recyclerFavorite
        recyclerFavouriteMovie.run {
            if (adapter == null) {
                adapter = FavoriteMovieAdapter {
                    findNavController().navigate(
                        FavoriteFragmentDirections
                            .actionFavoriteFragmentToDetailsFragment(
                                it.name,
                                it.id,
                                it.rating.average.toLong(),
                                it.summary,
                                it.premiered,
                                arrayOf(it.genres.take(2).toString()),
                                it.officialSite,
                                it.url,
                                it.status
                            )
                    )
                }
                layoutManager = LinearLayoutManager(requireContext())
            }
            viewModel.listFavouriteMovie.observe(viewLifecycleOwner) { list ->
                (recyclerFavouriteMovie.adapter as FavoriteMovieAdapter).setListFavouriteMovie(list)
                if (list.isEmpty()) {
                    binding.textNoFiles.visibility = View.VISIBLE
                    binding.emptyListImage.visibility = View.VISIBLE
                } else {
                    binding.textNoFiles.visibility = View.GONE
                    binding.emptyListImage.visibility = View.GONE
                }
            }
        }
    }
}