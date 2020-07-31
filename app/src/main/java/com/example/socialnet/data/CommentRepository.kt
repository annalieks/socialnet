package com.example.socialnet.data

class CommentRepository private constructor(
    private val commentDao: CommentDao
) {

    suspend fun getCommentsByPostId(postId: String) = commentDao.getCommentsByPostId(postId)

    suspend fun saveComments(comments: List<Comment>) = commentDao.insertComments(comments)

    companion object {

        // For Singleton instantiation
        @Volatile private var instance: CommentRepository? = null

        fun getInstance(commentDao: CommentDao) =
            instance ?: synchronized(this) {
                instance ?: CommentRepository(commentDao).also { instance = it }
            }
    }
}