package com.example.socialnet.data

import com.google.gson.annotations.SerializedName

data class CommentGetResponse(
    @field:SerializedName("postId") val postId: String,
    @field:SerializedName("id") val id: String,
    @field:SerializedName("name") val name: String,
    @field:SerializedName("email") val email: String,
    @field:SerializedName("body") val body: String
)