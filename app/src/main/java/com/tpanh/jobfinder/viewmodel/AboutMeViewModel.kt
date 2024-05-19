package com.tpanh.jobfinder.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tpanh.jobfinder.model.User
import com.tpanh.jobfinder.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AboutMeViewModel(
    private val userRepository: UserRepository
): ViewModel() {

    private val _uiState = MutableStateFlow(User())
    val uiState = _uiState.asStateFlow()

    init {
        getCurrentUser()
    }

    fun onAboutMeChange(aboutMe: String) {
        _uiState.update {
            it.copy(about = aboutMe)
        }
    }

    fun updateAboutMe(navigateToViewProfile: () -> Unit) {
        viewModelScope.launch {
            userRepository.updateUser(_uiState.value)
        }
        navigateToViewProfile()
    }

    private fun getCurrentUser() {
        viewModelScope.launch {
            _uiState.value = userRepository.getCurrentUser()
        }
    }

}