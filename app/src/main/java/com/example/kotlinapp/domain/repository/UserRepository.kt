package com.example.kotlinapp.domain.repository

import com.example.kotlinapp.domain.model.User

interface UserRepository {
    suspend fun getUsers(): Result<List<User>>
    suspend fun getUserById(id: Int): Result<User>
}
