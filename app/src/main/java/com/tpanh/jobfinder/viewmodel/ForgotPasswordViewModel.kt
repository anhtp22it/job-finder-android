package com.tpanh.jobfinder.viewmodel

import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.tpanh.jobfinder.model.User
import com.tpanh.jobfinder.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ForgotPasswordViewModel(
    private val authRepository: AuthRepository
): ViewModel() {
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

    fun sendEmailResetPassword(
        navigateToVerifyCation: () -> Unit,
        email: String
    ) {
        authRepository.sendEmailResetPassword(email)
        navigateToVerifyCation()
    }

    fun openYourEmail() {

    }

    fun changePassword(
        oldPassword: String,
        newPassword: String,
        confirmPassword: String,
        navigateToLogin: () -> Unit
    ) {
        if (newPassword != confirmPassword) {
            Toast.makeText(null, "New password and confirm password must be the same", Toast.LENGTH_SHORT).show()
            return
        } else {
            authRepository.changePassword(oldPassword, newPassword)
            navigateToLogin()
        }
    }
}