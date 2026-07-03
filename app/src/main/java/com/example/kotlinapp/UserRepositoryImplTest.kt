package com.example.kotlinapp

import com.example.kotlinapp.UserRepositoryImpl
import com.example.kotlinapp.UserApiService
import com.example.kotlinapp.UserDto
import com.example.kotlinapp.detail.ConnectivityChecker
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class UserRepositoryImplTest {

    private lateinit var api: UserApiService
    private lateinit var connectivityChecker: ConnectivityChecker
    private lateinit var repository: UserRepositoryImpl

    private val sampleUsers = listOf(
        UserDto(userId = 1, id = 1, title = "title one", body = "body one"),
        UserDto(userId = 1, id = 2, title = "title two", body = "body two")
    )

    @Before
    fun setUp() {
        api = mock()
        connectivityChecker = mock()
        repository = UserRepositoryImpl(api, connectivityChecker)
    }

    @Test
    fun `getUsers returns mapped domain list on success`() = runTest {
        whenever(api.getUsers()).thenReturn(sampleUsers)

        val result = repository.getUsers()

        assertThat(result.isSuccess).isTrue()
        val users = result.getOrThrow()
        assertThat(users).hasSize(2)
        assertThat(users.first().title).isEqualTo("title one")
    }

    @Test
    fun `getUsers returns failure when api throws`() = runTest {
        whenever(api.getUsers()).thenThrow(RuntimeException("network error"))

        val result = repository.getUsers()

        assertThat(result.isFailure).isTrue()
        assertThat(result.exceptionOrNull()).hasMessageThat().contains("network error")
    }

    @Test
    fun `getUserById returns mapped domain user on success`() = runTest {
        whenever(api.getUserById(1)).thenReturn(sampleUsers[0])

        val result = repository.getUserById(1)

        assertThat(result.isSuccess).isTrue()
        assertThat(result.getOrThrow().id).isEqualTo(1)
    }
}
