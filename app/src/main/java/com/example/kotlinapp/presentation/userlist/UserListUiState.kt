package com.example.kotlinapp.presentation.userlist

import com.example.kotlinapp.domain.model.User

sealed interface UserListUiState {
    data object Loading : UserListUiState
    data class Success(val users: List<User>) : UserListUiState
    data class Error(val message: String) : UserListUiState
}
