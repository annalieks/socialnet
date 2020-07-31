package com.example.socialnet.data

import com.example.socialnet.api.JsonPlaceholderService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class JsonPlaceholderRepository(private val service: JsonPlaceholderService) {

    suspend fun getPosts(): List<PostGetResponse> {
        return service.getPosts()
    }

    suspend fun getPostById(postId: String) : PostGetResponse {
        return service.getPostById(postId)
    }

    suspend fun getCommentsByPostId(postId: String): List<CommentGetResponse> {
        return service.getPostComments(postId)
    }

    suspend fun getUserById(userId: String): UserGetResponse {
        return service.getUserById(userId)
    }

}