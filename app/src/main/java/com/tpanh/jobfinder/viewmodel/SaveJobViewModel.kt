package com.tpanh.jobfinder.viewmodel

import androidx.lifecycle.ViewModel
import com.tpanh.jobfinder.model.Job
import com.tpanh.jobfinder.model.JobType
import com.tpanh.jobfinder.model.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class SaveJobViewModel: ViewModel() {
    private val _uiState = MutableStateFlow<List<Job>>(listOf())
    val uiState = _uiState.asStateFlow()
}