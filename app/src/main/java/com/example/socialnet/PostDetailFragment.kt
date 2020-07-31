package com.example.socialnet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.widget.NestedScrollView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.socialnet.adapters.CommentAdapter
import com.example.socialnet.adapters.PostAdapter
import com.example.socialnet.data.Post
import com.example.socialnet.databinding.FragmentPostDetailBinding
import com.example.socialnet.databinding.FragmentSavedPostsBinding
import com.example.socialnet.utilities.InjectorUtils
import com.example.socialnet.viewmodels.PostDetailViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_post_detail.*


/**
 * A fragment representing a single Post detail screen.
 */
class PostDetailFragment : Fragment() {

    private val args: PostDetailFragmentArgs by navArgs()

    private val postDetailViewModel: PostDetailViewModel by viewModels {
        InjectorUtils.providePostDetailViewModelFactory(requireActivity(), args.postId)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentPostDetailBinding>(
            inflater, R.layout.fragment_post_detail, container, false
        ).apply {
            viewModel = postDetailViewModel
            lifecycleOwner = viewLifecycleOwner
            /*callback = object : Callback {
                override fun add(post: Post?) {
                    Snackbar.make(root, R.string.saved_post, Snackbar.LENGTH_LONG)
                        .show()

                }
            }*/

            toolbar.setNavigationOnClickListener { view ->
                run {
                    postDetailViewModel.savePostDetails()
                    view.findNavController().navigateUp()
                }
            }
        }

        val commentsAdapter = CommentAdapter()
        binding.commentsList.adapter = commentsAdapter

        subscribeUi(commentsAdapter)

        return binding.root
    }

    private fun subscribeUi(adapter: CommentAdapter) {
        postDetailViewModel.comments.observe(viewLifecycleOwner) { adapter.submitList(it) }
    }

    interface Callback {
        fun add(post: Post?)
    }
}