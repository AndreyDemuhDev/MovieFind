package com.pidzama.moviefind.ui.intro

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.pidzama.moviefind.databinding.PageViewBinding

private const val IMAGE_EXTRA = "image extra"
private const val TITLE_EXTRA = "title extra"
private const val DESCRIPTION_EXTRA = "description extra"

class PageView : Fragment() {

    private lateinit var binding: PageViewBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = PageViewBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getInt(IMAGE_EXTRA)?.let { binding.imageViewPage.setImageResource(it) }
        binding.titleViewPage.text = arguments?.getString(TITLE_EXTRA)
        binding.descriptionViewPage.text = arguments?.getString(DESCRIPTION_EXTRA)
    }

    companion object {
        fun getPageFragmentInstance(id: Int, title: String, description: String): PageView {
            return PageView().apply {
                arguments = bundleOf(
                    IMAGE_EXTRA to id,
                    TITLE_EXTRA to title,
                    DESCRIPTION_EXTRA to description
                )
            }
        }
    }
}