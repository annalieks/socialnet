package com.example.socialnet.data

import com.example.socialnet.api.JsonPlaceholderService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class JsonPlaceholderRepository(private val service: JsonPlaceholderService) {

    fun getPosts(): Flow<Post> {
        return flow {
            service.getPosts()
        }
    }

    fun getPostById(postId: String) : Flow<Post> {
        return flow {
            service.getPostById(postId)
        }
    }

    fun getCommentsByPostId(postId: String): Flow<Comment> {
        return flow {
            service.getPostComments(postId)
        }
    }

    fun getUserById(userId: String): Flow<User> {
        return flow {
            service.getUserById(userId)
        }
    }
}