package com.example.socialnet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.viewpager2.widget.ViewPager2
import com.example.socialnet.adapters.POSTS_LIST_PAGE_INDEX
import com.example.socialnet.adapters.PostAdapter
import com.example.socialnet.adapters.SAVED_POSTS_PAGE_INDEX
import com.example.socialnet.databinding.FragmentSavedPostsBinding
import com.example.socialnet.utilities.InjectorUtils
import com.example.socialnet.viewmodels.SavedPostsListViewModel

class SavedPostsFragment : Fragment() {

    private lateinit var binding: FragmentSavedPostsBinding

    private val viewModel: SavedPostsListViewModel by viewModels {
        InjectorUtils.provideSavedPostsListViewModelFactory(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSavedPostsBinding.inflate(inflater, container, false)
        val adapter = PostAdapter()
        binding.savedPostsList.adapter = adapter

        binding.addPost.setOnClickListener {
            navigateToAllPostsListPage()
        }

        subscribeUi(adapter, binding)
        return binding.root
    }

    private fun subscribeUi(adapter: PostAdapter, binding: FragmentSavedPostsBinding) {
        viewModel.posts.observe(viewLifecycleOwner) { result ->
            binding.hasPosts = !result.isNullOrEmpty()
            adapter.submitList(result)
        }
    }

    private fun navigateToAllPostsListPage() {
        requireActivity().findViewById<ViewPager2>(R.id.view_pager).currentItem =
            POSTS_LIST_PAGE_INDEX
    }

}