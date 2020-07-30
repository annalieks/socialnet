package com.example.socialnet.data

import androidx.room.Embedded
import androidx.room.Relation

data class PostAndComments(
    @Embedded val post: Post,
    @Relation(
        parentColumn = "id",
        entityColumn = "postId"
    )
    val comments: List<Comment> = emptyList()
)