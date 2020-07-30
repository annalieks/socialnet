package com.example.socialnet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.socialnet.adapters.MY_POSTS_PAGE_INDEX
import com.example.socialnet.adapters.POSTS_LIST_PAGE_INDEX
import com.example.socialnet.adapters.SocialNetPagerAdapter
import com.example.socialnet.databinding.FragmentViewPagerBinding
import com.google.android.material.tabs.TabLayoutMediator

class HomeViewPagerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentViewPagerBinding.inflate(inflater, container, false)
        val tabLayout = binding.tabs
        val viewPager = binding.viewPager

        viewPager.adapter = SocialNetPagerAdapter(this)

        // Set the icon and text for each tab
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.setIcon(getTabIcon(position))
            tab.text = getTabTitle(position)
        }.attach()

        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)

        return binding.root
    }

    private fun getTabIcon(position: Int): Int {
        return when (position) {
            MY_POSTS_PAGE_INDEX -> R.drawable.saved_posts_tab_selector
            POSTS_LIST_PAGE_INDEX -> R.drawable.posts_list_tab_selector
            else -> throw IndexOutOfBoundsException()
        }
    }

    private fun getTabTitle(position: Int): String? {
        return when (position) {
            MY_POSTS_PAGE_INDEX -> getString(R.string.saved_posts_title)
            POSTS_LIST_PAGE_INDEX -> getString(R.string.posts_list_title)
            else -> null
        }
    }
}