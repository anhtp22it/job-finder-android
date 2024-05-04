package com.tpanh.jobfinder.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.tpanh.jobfinder.model.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class SearchViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(Job())
    val uiState = _uiState.asStateFlow()

    var search by mutableStateOf("")
        private set

    fun onSearchChange(search: String) {
        this.search = search
    }
}