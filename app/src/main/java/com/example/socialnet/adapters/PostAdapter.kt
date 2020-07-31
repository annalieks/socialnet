package com.example.socialnet.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.socialnet.HomeViewPagerFragmentDirections
import com.example.socialnet.data.Post
import com.example.socialnet.databinding.ListItemPostBinding

/**
 * Adapter for the [RecyclerView] in [PostsListFragment].
 */
class PostAdapter : ListAdapter<Post, RecyclerView.ViewHolder>(PostDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return PostViewHolder(ListItemPostBinding.inflate(
            LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val post = getItem(position)
        (holder as PostViewHolder).bind(post)
    }

    class PostViewHolder(
        private val binding: ListItemPostBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.setClickListener {
                binding.post?.let { post ->
                    navigateToPost(post, it)
                }
            }
        }

        private fun navigateToPost(
            post: Post,
            view: View
        ) {
            val direction =
                HomeViewPagerFragmentDirections.actionViewPagerFragmentToPostDetailFragment(
                    post.postId
                )
            view.findNavController().navigate(direction)
        }

        fun bind(item: Post) {
            binding.apply {
                post = item
                executePendingBindings()
            }
        }
    }
}

private class PostDiffCallback : DiffUtil.ItemCallback<Post>() {

    override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem.postId == newItem.postId
    }

    override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem == newItem
    }
}