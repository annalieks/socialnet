package com.example.socialnet.data

class PostRepository private constructor(
    private val postDao: PostDao
) {
    fun getAllPosts() = postDao.getAllPosts()
}