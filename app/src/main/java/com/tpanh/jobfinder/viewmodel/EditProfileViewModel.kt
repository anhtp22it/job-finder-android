package com.tpanh.jobfinder.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tpanh.jobfinder.model.User
import com.tpanh.jobfinder.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class EditProfileViewModel(
    private val userRepository: UserRepository
): ViewModel() {
    private val _uiState = MutableStateFlow(User(avatar = "https://img.freepik.com/premium-photo/people-generating-images-using-artificial-intelligence-laptop_23-2150794312.jpg?w=1480"))
    val uiState: StateFlow<User> = _uiState.asStateFlow()

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

    fun updateAvatar(avatar: String) {
        _uiState.update { currentState ->
            currentState.copy(avatar = avatar)
        }
    }

    fun updateUser() {
        viewModelScope.launch {
            userRepository.updateUser(_uiState.value)
        }
    }


}