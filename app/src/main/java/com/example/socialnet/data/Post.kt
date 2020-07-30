package com.example.socialnet.data

import androidx.room.*

@Entity(tableName = "posts",
    foreignKeys = [
        ForeignKey(entity = User::class, parentColumns = ["id"], childColumns = ["userId"])
    ],
    indices = [Index("userId")])
data class Post(
    @PrimaryKey @ColumnInfo(name = "id") val postId: String,
    val userId: String,
    val title: String,
    val body: String
)
