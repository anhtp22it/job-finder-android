package com.tpanh.jobfinder.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tpanh.jobfinder.model.User
import com.tpanh.jobfinder.repository.AuthRepository
import com.tpanh.jobfinder.utils.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SignUpViewModel(
    private val authRepository: AuthRepository
): ViewModel() {
    private val _uiState = MutableStateFlow(User(fullName = "", email = "", password = ""))
    val uiState = _uiState.asStateFlow()

    var rememberMe by mutableStateOf(false)
        private set

    var passwordHidden by mutableStateOf(true)
        private set

    fun updateEmail(email: String) {
        _uiState.update { currentState ->
            currentState.copy(email = email)
        }
    }

    fun updatePassword(password: String) {
        _uiState.update { currentState ->
            currentState.copy(password = password)
        }
    }

    fun updateFullName(fullName: String) {
        _uiState.update { currentState ->
            currentState.copy(fullName = fullName)
        }
    }

    fun updateRememberMe(rememberMe: Boolean) {
        this.rememberMe = rememberMe
    }

    fun togglePasswordVisibility() {
        passwordHidden = !passwordHidden
    }

    fun register(email: String, password: String, navigateToLogin: () -> Unit) {
        viewModelScope.launch {
            authRepository.registerUser(email = email, password = password).collectLatest {
                result ->
                when(result) {
                    is Resource.Loading -> {
                        Log.d("SignUpViewModel", "Loading")
                    }
                    is Resource.Success -> {
                        navigateToLogin()
                    }
                    is Resource.Error -> {
                        Log.d("SignUpViewModel", "Error: ${result.message}")
                    }
                }
            }
        }
    }
}