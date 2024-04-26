package com.tpanh.jobfinder.viewmodel

import androidx.lifecycle.ViewModel
import com.tpanh.jobfinder.model.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ForgotPasswordViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(User(email = ""))
    val uiState = _uiState.asStateFlow()

    fun onEmailChanged(email: String) {
        _uiState.update {
            it.copy(email = email)
        }
    }

    fun sendEmailResetPassword() {

    }

    fun openYourEmail() {

    }
}