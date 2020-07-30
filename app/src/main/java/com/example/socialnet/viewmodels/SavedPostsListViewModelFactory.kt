package com.example.socialnet.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.socialnet.data.PostRepository

class SavedPostsListViewModelFactory(
    private val repository: PostRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SavedPostsListViewModel(repository) as T
    }
}