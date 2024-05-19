package com.tpanh.jobfinder.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tpanh.jobfinder.model.User
import com.tpanh.jobfinder.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ViewProfileViewModel(
    private val userRepository: UserRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(User())
    val uiState = _uiState.asStateFlow()

    fun getCurrentUser() {
        viewModelScope.launch {
            val result = userRepository.getCurrentUser()
            _uiState.value = result
        }
    }

}