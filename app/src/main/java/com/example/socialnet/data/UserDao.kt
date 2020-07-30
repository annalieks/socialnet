package com.example.socialnet.data

import androidx.room.Dao
import androidx.room.Query

@Dao
interface UserDao {

    @Query("SELECT 1 FROM users WHERE id = :userId LIMIT 1")
    fun getUserById(userId: String)
}