package com.tpanh.jobfinder.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.tpanh.jobfinder.model.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SignUpViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(User(email = "", password = ""))
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

    fun updateRememberMe(rememberMe: Boolean) {
        this.rememberMe = rememberMe
    }

    fun togglePasswordVisibility() {
        passwordHidden = !passwordHidden
    }

    fun register(email: String, password: String) {
        Log.d("SignUpViewModel", "register: $email, $password")
    }
}