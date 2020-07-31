package com.example.socialnet.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.socialnet.data.CommentRepository
import com.example.socialnet.data.JsonPlaceholderRepository
import com.example.socialnet.data.PostRepository
import com.example.socialnet.data.UserRepository

class PostDetailViewModelFactory(
    private val jsonPlaceholderRepository: JsonPlaceholderRepository,
    private val postRepository: PostRepository,
    private val commentRepository: CommentRepository,
    private val userRepository: UserRepository,
    private val postId: String
): ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PostDetailViewModel(
            jsonPlaceholderRepository,
            postRepository,
            commentRepository,
            userRepository,
            postId
        ) as T
    }
}