package com.example.socialnet.data

import com.example.socialnet.api.JsonPlaceholderService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class JsonPlaceholderRepository(private val service: JsonPlaceholderService) {

    fun getPosts(): List<PostGetResponse> {
        return service.getPosts()
    }
}