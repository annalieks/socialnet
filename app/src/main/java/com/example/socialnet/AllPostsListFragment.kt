package com.example.socialnet

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.observe
import com.example.socialnet.adapters.PostAdapter
import com.example.socialnet.data.Post
import com.example.socialnet.databinding.FragmentAllPostsListBinding
import com.example.socialnet.utilities.InjectorUtils
import com.example.socialnet.viewmodels.AllPostsListViewModel
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch

class AllPostsListFragment : Fragment() {

    private val viewModel: AllPostsListViewModel by viewModels {
        InjectorUtils.provideAllPostsListViewModelFactory(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentAllPostsListBinding.inflate(inflater, container, false)
        context ?: return binding.root

        val adapter = PostAdapter()
        binding.postsList.adapter = adapter
        subscribeUi(adapter)

        return binding.root
    }

    private fun subscribeUi(adapter: PostAdapter) {
        viewModel.posts.observe(viewLifecycleOwner) { posts ->
            adapter.submitList(posts)
        }
    }
}