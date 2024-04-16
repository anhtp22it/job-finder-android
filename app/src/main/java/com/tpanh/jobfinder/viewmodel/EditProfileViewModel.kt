package com.tpanh.jobfinder.viewmodel

import androidx.lifecycle.ViewModel
import com.tpanh.jobfinder.model.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class EditProfileViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(User())
    val uiState: StateFlow<User> = _uiState.asStateFlow()

    fun updateFullName(fullName: String) {
        _uiState.update {currentState ->
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
}