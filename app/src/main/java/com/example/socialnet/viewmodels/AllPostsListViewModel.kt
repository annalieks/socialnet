package com.example.socialnet.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.socialnet.data.JsonPlaceholderRepository
import com.example.socialnet.data.Post
import com.example.socialnet.data.PostRepository
import com.example.socialnet.mappers.PostMapper
import kotlinx.coroutines.launch
import org.mapstruct.factory.Mappers.getMapper

class AllPostsListViewModel internal constructor(
    private val jsonPlaceholderRepository: JsonPlaceholderRepository,
    private val postRepository: PostRepository
) : ViewModel() {

    private val _posts = MutableLiveData<List<Post>>()
    val posts: LiveData<List<Post>> = _posts

    init {
        viewModelScope.launch {
            try {
                val result = jsonPlaceholderRepository.getPosts()
                val converter = getMapper(PostMapper::class.java)
                _posts.value = result
                    .map { converter.postGetResponseToPost(it) }
            } catch(t: Throwable) {
                _posts.value = postRepository.getAllPostsAsync()
            }
        }

    }

}