package com.pidzama.moviefind.ui.screens.main.cast

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
import com.pidzama.moviefind.R
import com.pidzama.moviefind.data.model.movies.Movie
import com.pidzama.moviefind.databinding.FragmentCastBinding
import com.pidzama.moviefind.ui.screens.main.search.SearchAdapter
import com.pidzama.moviefind.ui.screens.main.search.SearchFragmentDirections
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CastFragment : Fragment() {

    private lateinit var binding: FragmentCastBinding
    private val viewModel: CastViewModel by viewModels()
    private val args: CastFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCastBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonBack.setOnClickListener {
            findNavController().popBackStack()
        }


        viewModel.run {
            currentPerson.observe(viewLifecycleOwner) {
                Glide.with(requireContext())
                    .load(it.image?.medium)
                    .into(binding.imagePerson)
                binding.namePerson.text = it.name

                if (it.birthday == null) {
                    binding.birthdayPerson.text = resources.getText(R.string.Unknown)
                } else {
                    binding.birthdayPerson.text = it.birthday
                }
                if (it.country == null) {
                    binding.countryPerson.text = resources.getText(R.string.Unknown)
                } else {
                    binding.countryPerson.text = it.country.name
                }
            }
        }
        viewModel.getInfoPerson(args.idPerson)


    }

}