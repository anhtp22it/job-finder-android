package com.tpanh.jobfinder.viewmodel

import androidx.lifecycle.ViewModel
import com.tpanh.jobfinder.model.Education
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AddEducationViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(Education("1"))
    val uiState: StateFlow<Education> = _uiState.asStateFlow()

    fun updateLevelOfEducation(levelOfEducation: String) {
        _uiState.update { currentState ->
            currentState.copy(levelOfEducation = levelOfEducation)
        }
    }

    fun updateInstitutionName(institutionName: String) {
        _uiState.update { currentState ->
            currentState.copy(institutionName = institutionName)
        }
    }

    fun updateFieldOfStudy(fieldOfStudy: String) {
        _uiState.update { currentState ->
            currentState.copy(fieldOfStudy = fieldOfStudy)
        }
    }

    fun updateDescription(description: String) {
        _uiState.update { currentState ->
            currentState.copy(description = description)
        }
    }

    fun updateIsCurrentlyStudying(isCurrentlyStudying: Boolean) {
        _uiState.update { currentState ->
            currentState.copy(isCurrentlyStudying = isCurrentlyStudying)
        }
    }

    fun updateStartDate(startDate: Long) {
        _uiState.update { currentState ->
            currentState.copy(startDate = startDate)
        }
    }

    fun updateEndDate(endDate: Long) {
        _uiState.update { currentState ->
            currentState.copy(endDate = endDate)
        }
    }
}