package com.tpanh.jobfinder.viewmodel

import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tpanh.jobfinder.model.User

import com.tpanh.jobfinder.repository.UserRepository

import com.tpanh.jobfinder.repository.AuthRepository
import com.tpanh.jobfinder.repository.ImageRepository

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class EditProfileViewModel(
    private val userRepository: UserRepository,
    private val authRepository: AuthRepository,
    private val imageRepository: ImageRepository
): ViewModel() {
    private val _uiState = MutableStateFlow(User())
    val uiState: StateFlow<User> = _uiState.asStateFlow()

    var isDateOfBirthPicker by mutableStateOf(false)

    var selectedImageUri by mutableStateOf<Uri?>(null)

    init {
        getCurrentUser()
    }

    fun updateFullName(fullName: String) {
        _uiState.update { currentState ->
            currentState.copy(fullName = fullName)
        }
    }

    fun updateGender(gender: Int) {
        _uiState.update { currentState ->
            currentState.copy(gender = gender)
        }
    }

    fun updateEmail(email: String) {
        _uiState.update { currentState ->
            currentState.copy(email = email)
        }
    }

    fun updatePhoneNumber(phoneNumber: String) {
        _uiState.update { currentState ->
            currentState.copy(phoneNumber = phoneNumber)
        }
    }

    fun updateLocation(location: String) {
        _uiState.update { currentState ->
            currentState.copy(location = location)
        }
    }

    fun updateDateOfBirth(dateOfBirth: Long) {
        _uiState.update { currentState ->
            currentState.copy(dateOfBirth = dateOfBirth)
        }
    }

    fun onImageSelected(uri: Uri) {
        selectedImageUri = uri
    }

    fun logout(navigateToLogin: () -> Unit) {
        authRepository.logout()
        navigateToLogin()
    }

    fun updateUser() {
        viewModelScope.launch {
            selectedImageUri?.let { uri ->
                val imageUrl = imageRepository.uploadImage(uri, "users")
                Log.d("EditProfileViewModel", "updateUser: $imageUrl")
                _uiState.update {
                    it.copy(avatar = imageUrl)
                }
            }
            userRepository.updateUser(_uiState.value)
        }
        getCurrentUser()
    }

    private fun getCurrentUser() {
        viewModelScope.launch {
            userRepository.getCurrentUser().let { user ->
                _uiState.value = user
            }
        }
    }
}