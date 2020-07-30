package com.example.socialnet.data

import com.google.gson.annotations.SerializedName

data class UserGetResponse(
    @field:SerializedName("id") val id: String,
    @field:SerializedName("name") val name: String,
    @field:SerializedName("email") val email: String
)