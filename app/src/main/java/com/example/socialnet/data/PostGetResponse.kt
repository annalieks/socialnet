package com.example.socialnet.data

import com.google.gson.annotations.SerializedName

data class PostGetResponse(
    @field:SerializedName("userId") val userId: String,
    @field:SerializedName("id") val id: String,
    @field:SerializedName("title") val title: String,
    @field:SerializedName("body") val body: String
)