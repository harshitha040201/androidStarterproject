package com.example.kotlinapp

import javax.inject.Inject

class GetUserByIdUseCase @Inject constructor(
    private val repository: UserRepository
) {
    suspend operator fun invoke(id: Int): Result<User> = repository.getUserById(id)
}