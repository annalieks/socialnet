package com.example.socialnet.api

import com.example.socialnet.data.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface JsonPlaceholderService {

    @GET("posts")
    suspend fun getPosts() : List<PostGetResponse>

    @GET("posts/{postId}")
    suspend fun getPostById(
        @Path("postId") postId: String
    ) : PostGetResponse

    @GET("users/{userId}")
    suspend fun getUserById(
        @Path("userId") userId: String
    ) : UserGetResponse

    @GET("comments")
    suspend fun getPostComments(
        @Query("postId") postId: String
    ) : List<CommentGetResponse>

    companion object {
        private const val BASE_URL = "https://api.unsplash.com/"

        fun create(): JsonPlaceholderService {
            val logger = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(JsonPlaceholderService::class.java)
        }
    }

}