package com.pidzama.moviefind.ui.screens.intro

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.pidzama.moviefind.R
import com.pidzama.moviefind.ui.screens.intro.PageView.Companion.getPageFragmentInstance

class ViewPageAdapter (fragment: Fragment) : FragmentStateAdapter(fragment) {

    private val listViewPager = arrayListOf(
        getPageFragmentInstance(R.drawable.view_pager_like, "We provide a service with an extensive database of modern films, series and TV shows"),
        getPageFragmentInstance(R.drawable.add_favorite_view_pager, "Save your favorite shows to go back to watching (but you can not watch, sorry)"),
        getPageFragmentInstance(R.drawable.share_view_page, "Share your favorite shows with your friends and your least favorite shows with your enemies"),
        getPageFragmentInstance(R.drawable.enjoy_view_pager,"Enjoy the process and the app"),
    )

    override fun getItemCount(): Int = 4

    override fun createFragment(position: Int): Fragment {
        return listViewPager[position]
    }
}