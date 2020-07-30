package com.example.socialnet.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query

@Dao
interface CommentDao {

    @Query("SELECT * FROM comments WHERE postId = :postId")
    fun getCommentsByPostId(postId: String): LiveData<List<Comment>>
}