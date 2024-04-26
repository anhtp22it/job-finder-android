package com.tpanh.jobfinder.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.tpanh.jobfinder.model.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ForgotPasswordViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(User(email = ""))
    val uiState = _uiState.asStateFlow()

    var oldPassword by mutableStateOf("")
        private set
    var newPassword by mutableStateOf("")
        private set
    var confirmPassword by mutableStateOf("")
        private set
    var oldPasswordHidden by mutableStateOf(true)
        private set
    var newPasswordHidden by mutableStateOf(true)
        private set
    var confirmPasswordHidden by mutableStateOf(true)
        private set

    fun onOldPasswordChanged(oldPassword: String) {
        this.oldPassword = oldPassword
    }

    fun onNewPasswordChanged(newPassword: String) {
        this.newPassword = newPassword
    }

    fun onConfirmPasswordChanged(confirmPassword: String) {
        this.confirmPassword = confirmPassword
    }

    fun toggleOldPasswordVisibility() {
        oldPasswordHidden = !oldPasswordHidden
    }

    fun toggleNewPasswordVisibility() {
        newPasswordHidden = !newPasswordHidden
    }

    fun toggleConfirmPasswordVisibility() {
        confirmPasswordHidden = !confirmPasswordHidden
    }

    fun onEmailChanged(email: String) {
        _uiState.update {
            it.copy(email = email)
        }
    }

    fun sendEmailResetPassword() {

    }

    fun openYourEmail() {

    }

    fun changePassword() {

    }
}