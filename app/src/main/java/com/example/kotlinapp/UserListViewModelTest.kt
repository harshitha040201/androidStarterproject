package com.example.kotlinapp

import app.cash.turbine.test
import com.example.kotlinapp.User
import com.example.kotlinapp.GetUsersUseCase
import com.example.kotlinapp.UserListUiState
import com.example.kotlinapp.UserListViewModel
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class UserListViewModelTest {

    private val testDispatcher = StandardTestDispatcher()
    private lateinit var getUsersUseCase: GetUsersUseCase

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        getUsersUseCase = mock()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `uiState emits Loading then Success when use case succeeds`() = runTest {
        val users = listOf(User(id = 1, userId = 1, title = "t", body = "b"))
        whenever(getUsersUseCase()).thenReturn(Result.success(users))

        val viewModel = UserListViewModel(getUsersUseCase)

        viewModel.uiState.test {
            assertThat(awaitItem()).isEqualTo(UserListUiState.Loading)
            assertThat(awaitItem()).isEqualTo(UserListUiState.Success(users))
        }
    }

    @Test
    fun `uiState emits Error when use case fails`() = runTest {
        whenever(getUsersUseCase()).thenReturn(Result.failure(RuntimeException("no network")))

        val viewModel = UserListViewModel(getUsersUseCase)

        viewModel.uiState.test {
            assertThat(awaitItem()).isEqualTo(UserListUiState.Loading)
            assertThat(awaitItem()).isEqualTo(UserListUiState.Error("no network"))
        }
    }
}
