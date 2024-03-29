package com.pidzama.moviefind.ui.main.search

import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.pidzama.moviefind.data.model.movies.Movie
import com.pidzama.moviefind.data.model.search.SearchItem
import com.pidzama.moviefind.data.model.search.Show
import com.pidzama.moviefind.databinding.FragmentSearchBinding
import com.pidzama.moviefind.ui.main.search.adapter.SearchAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private val viewModelSearch: SearchViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModelSearch.listSearchMovie.observe(viewLifecycleOwner) {
            setListSearchMovie(it)
        }
        binding.buttonSearch.setOnClickListener {
            searchMovie()
        }
        binding.searchField.setOnKeyListener { _, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                searchMovie()
                return@setOnKeyListener true
            }
            return@setOnKeyListener false
        }
    }

    private fun searchMovie() {
        binding.apply {
            val query = searchField.text.toString()
            if (query.isEmpty()) return
            showLoading(true)
            viewModelSearch.setSearchMovie(query)
        }
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            binding.progress.visibility = View.VISIBLE
        } else {
            binding.progress.visibility = View.GONE
        }
    }

    private fun setListSearchMovie(searchMovie: ArrayList<SearchItem>) {
        binding.searchRecycler.run {
            if (adapter == null) {
                adapter = SearchAdapter {
                    findNavController().navigate(
                        SearchFragmentDirections
                            .actionSearchFragmentToDetailsFragment(
                                it.show.name,
                                it.show.id,
                                it.show.rating.average.toLong(),
                                it.show.summary,
                                it.show.premiered,
                                arrayOf(it.show.genres.take(2).toString()),
                                it.show.officialSite,
                                it.show.url,
                                it.show.status
                            )
                    )
                }
                layoutManager = GridLayoutManager(requireContext(), 2)
            }
            (adapter as? SearchAdapter)?.setList(searchMovie)
        }
        viewModelSearch.getSearchMovie()
        showLoading(false)
    }
}
