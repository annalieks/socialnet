package com.example.socialnet.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey @ColumnInfo(name = "id") val userId: String,
    val name: String,
    val email: String
)