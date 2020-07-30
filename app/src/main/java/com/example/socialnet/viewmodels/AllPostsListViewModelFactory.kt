package com.example.socialnet.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.socialnet.data.JsonPlaceholderRepository


class AllPostsListViewModelFactory(
    private val repository: JsonPlaceholderRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AllPostsListViewModel(repository) as T
    }
}