package com.example.socialnet.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.socialnet.data.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class PostDetailViewModel(
    private val jsonPlaceholderRepository: JsonPlaceholderRepository,
    private val postRepository: PostRepository,
    private val commentRepository: CommentRepository,
    private val userRepository: UserRepository,
    private val postId: String
) : ViewModel() {
    //TODO: support offline mode
    //private val savedPost = postRepository.getPostById(postId)
    //private val savedComments = commentRepository.getCommentsByPostId(postId)
    //private val savedUser = userRepository.getUserByPostId(post.value.userId)

    fun getPost(postId: String): Flow<Post> {
        return jsonPlaceholderRepository.getPostById(postId)
    }

    fun getComments(postId: String): Flow<Comment> {
        return jsonPlaceholderRepository.getCommentsByPostId(postId)
    }

    fun getUser(userId: String): Flow<User> {
        return jsonPlaceholderRepository.getUserById(userId)
    }
}