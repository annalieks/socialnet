package com.example.socialnet.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "comments",
    foreignKeys = [
    androidx.room.ForeignKey(
        entity = Post::class,
        parentColumns = ["id"],
        childColumns = ["postId"]
    )
    ],
    indices = [Index("postId")]
)
data class Comment(
    @PrimaryKey @ColumnInfo(name = "id") val commentId: String,
    val postId: String,
    val name: String,
    val email: String,
    val body: String
)