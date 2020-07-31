package com.example.socialnet.data

import androidx.room.*
import com.github.pozo.KotlinBuilder

@KotlinBuilder
@Entity(tableName = "posts")
data class Post(
    @PrimaryKey @ColumnInfo(name = "id") val postId: String,
    val userId: String,
    val title: String,
    val body: String
)
