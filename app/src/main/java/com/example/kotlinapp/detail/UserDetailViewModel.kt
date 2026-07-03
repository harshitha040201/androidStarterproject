package com.example.kotlinapp.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlinapp.detail.GetUserByIdUseCase
import com.example.kotlinapp.Screen
import com.example.kotlinapp.detail.UserDetailUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserDetailViewModel @Inject constructor(
    private val getUserByIdUseCase: GetUserByIdUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val userId: Int = checkNotNull(savedStateHandle[Screen.UserDetail.ARG_USER_ID])

    private val _uiState = MutableStateFlow<UserDetailUiState>(UserDetailUiState.Loading)
    val uiState: StateFlow<UserDetailUiState> = _uiState.asStateFlow()

    init {
        loadUser()
    }

    fun loadUser() {
        viewModelScope.launch {
            _uiState.value = UserDetailUiState.Loading
            getUserByIdUseCase(userId)
                .onSuccess { user -> _uiState.value = UserDetailUiState.Success(user) }
                .onFailure { error ->
                    _uiState.value = UserDetailUiState.Error(error.message ?: "Something went wrong")
                }
        }
    }
}