package com.example.kotlinapp.data.remote

import com.example.kotlinapp.data.remote.dto.UserDto
import retrofit2.http.GET
import retrofit2.http.Path

interface UserApiService {
    @GET("posts")
    suspend fun getUsers(): List<UserDto>

    @GET("posts/{id}")
    suspend fun getUserById(@Path("id") id: Int): UserDto
}
