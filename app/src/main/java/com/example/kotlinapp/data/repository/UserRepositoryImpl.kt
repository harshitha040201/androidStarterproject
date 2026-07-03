package com.example.kotlinapp.data.repository

import com.example.kotlinapp.data.remote.UserApiService
import com.example.kotlinapp.data.remote.dto.toDomain
import com.example.kotlinapp.domain.connectivity.ConnectivityChecker
import com.example.kotlinapp.domain.model.User
import com.example.kotlinapp.domain.repository.UserRepository
import com.example.kotlinapp.domain.exception.NoConnectivityException
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
