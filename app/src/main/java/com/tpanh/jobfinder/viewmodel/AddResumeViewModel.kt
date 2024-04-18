package com.tpanh.jobfinder.viewmodel

import androidx.lifecycle.ViewModel
import com.tpanh.jobfinder.model.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AddResumeViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(User(1, resume = ""))
    val uiState = _uiState.asStateFlow()

    fun updateResume(resume: String) {
        _uiState.update {currentState ->
            currentState.copy(resume = resume)
        }
    }
}