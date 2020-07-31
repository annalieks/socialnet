package com.example.socialnet.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CommentDao {

    @Query("SELECT * FROM comments WHERE postId = :postId")
    suspend fun getCommentsByPostId(postId: String): List<Comment>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertComments(comments: List<Comment>): Void
}