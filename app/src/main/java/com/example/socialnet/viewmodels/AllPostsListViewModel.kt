package com.example.socialnet.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.socialnet.data.JsonPlaceholderRepository
import com.example.socialnet.data.PostGetResponse

class AllPostsListViewModel internal constructor(
    private val repository: JsonPlaceholderRepository
) : ViewModel() {

    var posts: LiveData<List<PostGetResponse>> = repository.getPosts()
}