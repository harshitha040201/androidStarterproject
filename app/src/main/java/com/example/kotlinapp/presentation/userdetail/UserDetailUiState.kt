package com.example.kotlinapp.presentation.userdetail

import com.example.kotlinapp.domain.model.User

sealed interface UserDetailUiState {
    data object Loading : UserDetailUiState
    data class Success(val user: User) : UserDetailUiState
    data class Error(val message: String) : UserDetailUiState
}
