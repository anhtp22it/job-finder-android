package com.tpanh.jobfinder.viewmodel

import androidx.lifecycle.ViewModel
import com.tpanh.jobfinder.model.JobApply
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MyApplicationViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(JobApply())
    val uiState = _uiState.asStateFlow()
}