package com.example.socialnet.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.socialnet.data.JsonPlaceholderRepository
import com.example.socialnet.data.Post
import com.example.socialnet.data.PostGetResponse
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class AllPostsListViewModel internal constructor(
    private val repository: JsonPlaceholderRepository
) : ViewModel() {

    fun getPosts(): Flow<Post> {
        return repository.getPosts()
    }
}