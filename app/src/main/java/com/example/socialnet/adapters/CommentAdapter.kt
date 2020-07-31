package com.example.socialnet.adapters

import com.example.socialnet.data.Comment
import com.example.socialnet.databinding.ListItemCommentBinding
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

/**
 * Adapter for the [RecyclerView] in [PostDetailFragment].
 */
class CommentAdapter : ListAdapter<Comment, RecyclerView.ViewHolder>(CommentDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CommentViewHolder(
            ListItemCommentBinding.inflate(
            LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val comment = getItem(position)
        (holder as CommentViewHolder).bind(comment)
    }

    class CommentViewHolder(
        private val binding: ListItemCommentBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Comment) {
            binding.apply {
                comment = item
                executePendingBindings()
            }
        }
    }
}

private class CommentDiffCallback : DiffUtil.ItemCallback<Comment>() {

    override fun areItemsTheSame(oldItem: Comment, newItem: Comment): Boolean {
        return oldItem.commentId == newItem.commentId
    }

    override fun areContentsTheSame(oldItem: Comment, newItem: Comment): Boolean {
        return oldItem == newItem
    }
}