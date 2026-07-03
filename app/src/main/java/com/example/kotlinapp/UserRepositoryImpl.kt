package com.example.kotlinapp

import com.example.kotlinapp.detail.ConnectivityChecker
import com.example.kotlinapp.detail.NoConnectivityException
import com.example.kotlinapp.User
import com.example.kotlinapp.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val api: UserApiService,
    private val connectivityChecker: ConnectivityChecker
) : UserRepository {

    override suspend fun getUsers(): Result<List<User>> = withContext(Dispatchers.IO) {
        if (!connectivityChecker.isConnected()) {
            return@withContext Result.failure(NoConnectivityException())
        }
        runCatching { api.getUsers().map { it.toDomain() } }
    }

    override suspend fun getUserById(id: Int): Result<User> = withContext(Dispatchers.IO) {
        if (!connectivityChecker.isConnected()) {
            return@withContext Result.failure(NoConnectivityException())
        }
        runCatching { api.getUserById(id).toDomain() }
    }
}