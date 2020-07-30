package com.example.socialnet.data

class PostRepository private constructor(
    private val postDao: PostDao
) {
    fun getAllPosts() = postDao.getAllPosts()

    fun getPostById(postId: String) = postDao.getPostById(postId)

    companion object {

        // For Singleton instantiation
        @Volatile private var instance: PostRepository? = null

        fun getInstance(postDao: PostDao) =
            instance ?: synchronized(this) {
                instance ?: PostRepository(postDao).also { instance = it }
            }
    }
}