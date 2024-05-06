package com.tpanh.jobfinder.viewmodel

import android.util.Log
import android.widget.Toast
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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

class LoginViewModel(
    private val authRepository: AuthRepository
): ViewModel() {
    private val _uiState = MutableStateFlow(User(email = "", password = ""))
    val uiState = _uiState.asStateFlow()

    var error by mutableStateOf("")
        private set

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

    fun updateRememberMe(rememberMe: Boolean) {
        this.rememberMe = rememberMe
    }

    fun togglePasswordVisibility() {
        passwordHidden = !passwordHidden
    }

    fun resetError() {
        error = ""
    }

    fun login(email: String, password: String, navigateToHome: () -> Unit) {
        viewModelScope.launch {
            authRepository.loginUser(email = email, password = password).collectLatest {
                result ->
                when(result) {
                    is Resource.Loading -> {
                        Log.d("LoginViewModel", "Loading")
                    }
                    is Resource.Success -> {
                        navigateToHome()
                    }
                    is Resource.Error -> {
                        error = result.message.toString()
                    }
                }
            }
        }
    }
}