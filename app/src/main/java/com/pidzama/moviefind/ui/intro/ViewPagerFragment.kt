package com.pidzama.moviefind.ui.intro

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.pidzama.moviefind.R
import com.pidzama.moviefind.databinding.FragmentViewPagerBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ViewPagerFragment : Fragment() {

    private lateinit var binding: FragmentViewPagerBinding
    private val viewPagerViewModel: ViewPagerViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentViewPagerBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewPagerViewModel.isNoFirstStart()
        val dotsIndicator = binding.indicator
        val viewPager = binding.viewPager
        val adapter = ViewPageAdapter(this)
        viewPager.adapter = adapter
        dotsIndicator.attachTo(viewPager)

        binding.buttonStart.setOnClickListener {
            findNavController().navigate(R.id.signInFragment)
        }
    }

}