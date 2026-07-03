package com.example.kotlinapp.domain.usecase

import com.example.kotlinapp.domain.model.User
import com.example.kotlinapp.domain.repository.UserRepository
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class GetUsersUseCaseTest {

    private lateinit var repository: UserRepository
    private lateinit var useCase: GetUsersUseCase

    @Before
    fun setUp() {
        repository = mock()
        useCase = GetUsersUseCase(repository)
    }

    @Test
    fun `invoke delegates to repository and returns its result`() = runTest {
        val users = listOf(User(id = 1, userId = 1, title = "t", body = "b"))
        whenever(repository.getUsers()).thenReturn(Result.success(users))

        val result = useCase()

        assertThat(result.isSuccess).isTrue()
        assertThat(result.getOrThrow()).isEqualTo(users)
    }

    @Test
    fun `invoke propagates failure from repository`() = runTest {
        val exception = RuntimeException("boom")
        whenever(repository.getUsers()).thenReturn(Result.failure(exception))

        val result = useCase()

        assertThat(result.isFailure).isTrue()
        assertThat(result.exceptionOrNull()).isEqualTo(exception)
    }
}
