package com.example.socialnet.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.socialnet.data.JsonPlaceholderRepository
import com.example.socialnet.data.PostRepository


class AllPostsListViewModelFactory(
    private val repository: JsonPlaceholderRepository,
    private val postRepository: PostRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AllPostsListViewModel(repository, postRepository) as T
    }
}