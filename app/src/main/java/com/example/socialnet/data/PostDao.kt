package com.example.socialnet.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface PostDao {

    @Query("SELECT * FROM posts")
    fun getAllPosts(): LiveData<List<Post>>

    @Query("SELECT * FROM posts WHERE id = :postId LIMIT 1")
    fun getPostById(postId: String): LiveData<Post>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(post: Post): Long

}