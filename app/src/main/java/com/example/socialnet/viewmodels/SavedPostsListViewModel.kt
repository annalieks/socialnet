package com.example.socialnet.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.socialnet.data.Post
import com.example.socialnet.data.PostRepository

// all saved to db posts
class SavedPostsListViewModel internal constructor(
    postRepository: PostRepository
) : ViewModel() {
    val posts: LiveData<List<Post>> = postRepository.getAllPosts()
}