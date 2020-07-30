package com.example.socialnet.data

class CommentRepository private constructor(
    private val commentDao: CommentDao
) {

    fun getCommentsByPostId(postId: String) = commentDao.getCommentsByPostId(postId)

    companion object {

        // For Singleton instantiation
        @Volatile private var instance: CommentRepository? = null

        fun getInstance(commentDao: CommentDao) =
            instance ?: synchronized(this) {
                instance ?: CommentRepository(commentDao).also { instance = it }
            }
    }
}