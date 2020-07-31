package com.example.socialnet.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.socialnet.data.*
import com.example.socialnet.mappers.CommentMapper
import com.example.socialnet.mappers.PostMapper
import com.example.socialnet.mappers.UserMapper
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import org.mapstruct.factory.Mappers.getMapper

private const val TAG: String = "POST_DETAILS"

class PostDetailViewModel(
    private val jsonPlaceholderRepository: JsonPlaceholderRepository,
    private val postRepository: PostRepository,
    private val commentRepository: CommentRepository,
    private val userRepository: UserRepository,
    private val postId: String
) : ViewModel() {

    private val _post = MutableLiveData<Post>()
    private val _comments = MutableLiveData<List<Comment>>()
    private val _user = MutableLiveData<User>()

    val post: LiveData<Post> = _post
    val comments: LiveData<List<Comment>> = _comments
    val user: LiveData<User> = _user

    init {
        viewModelScope.launch {
            try {
                _post.value = postRepository.getPostById(postId)
                _comments.value = commentRepository.getCommentsByPostId(postId)
                _user.value = userRepository.getUserById(_post.value!!.userId)
            } catch (t: Throwable) {
                fetchPost()
            }
        }
    }

    fun fetchPost() {
        fetchPostDetails()
        fetchComments()
    }

    fun fetchComments() {
        val commentMapper = getMapper(CommentMapper::class.java)
        viewModelScope.launch {
            try {
                _comments.value = jsonPlaceholderRepository
                    .getCommentsByPostId(postId)
                    .map { commentMapper.commentGetResponseToComment(it) }
            } catch (t: Throwable) {
                Log.w(TAG, "Could not fetch comments")
            }
        }
    }

    fun fetchPostDetails() {
        val postMapper = getMapper(PostMapper::class.java)
        val userMapper = getMapper(UserMapper::class.java)

        val postAsync = viewModelScope.async {
            jsonPlaceholderRepository.getPostById(postId)
        }

        viewModelScope.launch {
            try {
                val receivedPost = postAsync.await()
                _post.value = postMapper.postGetResponseToPost(receivedPost)

                val result = jsonPlaceholderRepository
                    .getUserById(receivedPost.userId)
                _user.value = userMapper.userGetResponseToUser(result)

            } catch (t: Throwable) {
                Log.w(TAG, "Could not fetch post details")
            }
        }
    }

    fun savePostDetails() {
        viewModelScope.launch {
            val postToSave = post.value
            postToSave?.let {
                postRepository.savePost(postToSave)
            }
        }
        viewModelScope.launch {
            val commentsToSave = comments.value
            commentsToSave?.let {
                commentRepository.saveComments(commentsToSave)
            }
        }
        viewModelScope.launch {
            val userToSave = user.value
            userToSave?.let {
                userRepository.saveUser(userToSave)
            }
        }
    }
}