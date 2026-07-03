package com.example.kotlinapp.detail

import com.example.kotlinapp.User
import com.example.kotlinapp.UserRepository
import javax.inject.Inject

class GetUserByIdUseCase @Inject constructor(
    private val repository: UserRepository
) {
    suspend operator fun invoke(id: Int): Result<User> = repository.getUserById(id)
}