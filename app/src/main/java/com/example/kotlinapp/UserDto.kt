package com.example.kotlinapp

import com.google.gson.annotations.SerializedName

data class UserDto(
    @SerializedName("userId") val userId: Int,
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("body") val body: String
)

fun UserDto.toDomain(): User = User(
    id = id,
    userId = userId,
    title = title,
    body = body
)
