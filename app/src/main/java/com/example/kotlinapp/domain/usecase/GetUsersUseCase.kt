package com.example.kotlinapp.domain.usecase

import com.example.kotlinapp.domain.model.User
import com.example.kotlinapp.domain.repository.UserRepository
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(
    private val repository: UserRepository
) {
    suspend operator fun invoke(): Result<List<User>> = repository.getUsers()
}
