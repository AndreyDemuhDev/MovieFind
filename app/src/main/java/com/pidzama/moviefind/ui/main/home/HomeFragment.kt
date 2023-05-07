package com.pidzama.moviefind.ui.main.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.recyclerview.widget.GridLayoutManager
import com.pidzama.moviefind.R
import com.pidzama.moviefind.data.model.movies.Movie
import com.pidzama.moviefind.databinding.FragmentHomeBinding
import com.pidzama.moviefind.ui.main.home.adapters.MoviesAdapter
import com.pidzama.moviefind.ui.auth.login.AuthorisationViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()
    private val authViewModel: AuthorisationViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            viewModel.flowHero.collectLatest { pagingData ->
                initMovieAdapter(pagingData)
            }
        }
        (binding.recyclerMovie.adapter as? MoviesAdapter)?.addLoadStateListener { state ->
            binding.progress.isVisible = state.refresh == LoadState.Loading
        }
        binding.emailUser.text = authViewModel.getEmail()
        binding.imageUser.setImageResource(R.drawable.users_ic)
    }

    private suspend fun initMovieAdapter(list: PagingData<Movie>) {
        binding.recyclerMovie.run {
            if (adapter == null) {
                adapter = MoviesAdapter {
                    findNavController().navigate(
                        HomeFragmentDirections.actionHomeFragmentToDetailsFragment(
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
                layoutManager = GridLayoutManager(requireContext(), 2)
            }
            (adapter as? MoviesAdapter)?.submitData(list)
        }
        viewModel.getAllMovies()
    }
}