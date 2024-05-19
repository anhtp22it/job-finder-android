package com.tpanh.jobfinder.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tpanh.jobfinder.model.User
import com.tpanh.jobfinder.model.WorkExperience
import com.tpanh.jobfinder.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class WorkExperienceViewModel(
    private val userRepository: UserRepository
): ViewModel() {
    private val _uiState = MutableStateFlow(WorkExperience())
    val uiState = _uiState.asStateFlow()

    var isStartDatePickerVisible by mutableStateOf(false)

    var isEndDatePickerVisible by  mutableStateOf(false)

    init {
        getCurrentUser()
    }

    fun updateJobTitle(jobTitle: String) {
        _uiState.update {
            it.copy(jobTitle = jobTitle)
        }
    }

    fun updateCompany(company: String) {
        _uiState.update {
            it.copy(company = company)
        }
    }

    fun updateStartDay(startDay: Long) {
        _uiState.update {
            it.copy(startDay = startDay)
        }
    }

    fun updateEndDay(endDay: Long) {
        _uiState.update {
            it.copy(endDay = endDay)
        }
    }

    fun updateDescription(description: String) {
        _uiState.update {
            it.copy(description = description)
        }
    }

    fun updateIsCurrentWorking(isCurrentWorking: Boolean) {
        _uiState.update {
            it.copy(isCurrentWorking = isCurrentWorking)
        }
    }

    fun saveWorkExperience(navigateToViewProfile: () -> Unit) {
        viewModelScope.launch {
            userRepository.updateWorkExperience(_uiState.value)
        }
        navigateToViewProfile()
    }

    private fun getCurrentUser() {
        viewModelScope.launch {
            _uiState.value = userRepository.getCurrentUser().experience
        }
    }
}