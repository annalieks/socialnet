package com.example.socialnet.utilities

import android.content.Context
import com.example.socialnet.api.JsonPlaceholderService
import com.example.socialnet.data.*
import com.example.socialnet.viewmodels.AllPostsListViewModelFactory
import com.example.socialnet.viewmodels.PostDetailViewModelFactory
import com.example.socialnet.viewmodels.SavedPostsListViewModelFactory

object InjectorUtils {

    private fun getPostRepository(context: Context): PostRepository {
        return PostRepository.getInstance(
            AppDatabase.getInstance(context.applicationContext).postDao())
    }

    private fun getCommentRepository(context: Context): CommentRepository {
        return CommentRepository.getInstance(
            AppDatabase.getInstance(context.applicationContext).commentDao())
    }

    private fun getUserRepository(context: Context): UserRepository {
        return UserRepository.getInstance(
            AppDatabase.getInstance(context.applicationContext).userDao())
    }

    fun provideAllPostsListViewModelFactory(
        context: Context
    ): AllPostsListViewModelFactory {
        val repository = JsonPlaceholderRepository(JsonPlaceholderService.create())
        return AllPostsListViewModelFactory(repository, getPostRepository(context))
    }

    fun provideSavedPostsListViewModelFactory(
        context: Context
    ): SavedPostsListViewModelFactory {
        return SavedPostsListViewModelFactory(getPostRepository(context))
    }

    fun providePostDetailViewModelFactory(
        context: Context,
        postId: String
    ): PostDetailViewModelFactory {
        val jsonPlaceholderRepository = JsonPlaceholderRepository(JsonPlaceholderService.create())
        return PostDetailViewModelFactory(
            jsonPlaceholderRepository,
            getPostRepository(context),
            getCommentRepository(context),
            getUserRepository(context),
            postId
        )
    }
}