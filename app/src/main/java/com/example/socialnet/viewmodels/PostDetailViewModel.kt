package com.example.socialnet.viewmodels

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

class PostDetailViewModel(
    private val jsonPlaceholderRepository: JsonPlaceholderRepository,
    private val postRepository: PostRepository,
    private val commentRepository: CommentRepository,
    private val userRepository: UserRepository,
    private val postId: String
) : ViewModel() {
    //TODO: support offline mode (try-catch)
    //private val savedPost = postRepository.getPostById(postId)
    //private val savedComments = commentRepository.getCommentsByPostId(postId)
    //private val savedUser = userRepository.getUserByPostId(post.value.userId)

    private val _post = MutableLiveData<Post>()
    private val _comments = MutableLiveData<List<Comment>>()
    private val _user = MutableLiveData<User>()

    val post: LiveData<Post> = _post
    val comments: LiveData<List<Comment>> = _comments
    val user: LiveData<User> = _user

    init {
        val postMapper = getMapper(PostMapper::class.java)
        val commentMapper = getMapper(CommentMapper::class.java)
        val userMapper = getMapper(UserMapper::class.java)

        val postAsync = viewModelScope.async {
            jsonPlaceholderRepository.getPostById(postId)
        }
        viewModelScope.launch {
            _comments.value = jsonPlaceholderRepository
                .getCommentsByPostId(postId)
                .map { commentMapper.commentGetResponseToComment(it) }
        }
        viewModelScope.launch {
            _post.value = postMapper.postGetResponseToPost(postAsync.await())
            val result = jsonPlaceholderRepository
                .getUserById(postAsync.await().userId)
            _user.value = userMapper.userGetResponseToUser(result)
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