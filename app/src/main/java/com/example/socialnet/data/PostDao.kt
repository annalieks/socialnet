package com.example.socialnet.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface PostDao {

    @Query("SELECT * FROM posts")
    fun getAllPosts(): LiveData<List<Post>>

    @Query("SELECT 1 FROM posts WHERE id = :postId LIMIT 1")
    fun getPostById(postId: String): LiveData<Post>

}