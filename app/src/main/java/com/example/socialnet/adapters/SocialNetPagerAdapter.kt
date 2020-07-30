package com.example.socialnet.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.socialnet.AllPostsListFragment
import com.example.socialnet.SavedPostsFragment
import java.lang.IndexOutOfBoundsException

const val SAVED_POSTS_PAGE_INDEX = 0
const val POSTS_LIST_PAGE_INDEX = 1

class SocialNetPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    private val tabFragmentsCreators: Map<Int, () -> Fragment> = mapOf(
        SAVED_POSTS_PAGE_INDEX to { SavedPostsFragment() },
        POSTS_LIST_PAGE_INDEX to { AllPostsListFragment() }
    )

    override fun getItemCount() = tabFragmentsCreators.size

    override fun createFragment(position: Int): Fragment {
        return tabFragmentsCreators[position]?.invoke() ?: throw IndexOutOfBoundsException()
    }
}