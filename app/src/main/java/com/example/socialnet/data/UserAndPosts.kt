package com.example.socialnet.data

import androidx.room.Embedded
import androidx.room.Relation

data class UserAndPosts(
    @Embedded
    var user: User,

    @Relation(
        parentColumn = "id",
        entityColumn = "userId"
    )
    val posts: List<Post> = emptyList()
)