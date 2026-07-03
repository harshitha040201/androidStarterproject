package com.example.kotlinapp

import retrofit2.http.GET
import retrofit2.http.Path

interface UserApiService {
    @GET("posts")
    suspend fun getUsers(): List<UserDto>

    @GET("posts/{id}")
    suspend fun getUserById(@Path("id") id: Int): UserDto
}