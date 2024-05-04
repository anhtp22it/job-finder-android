package com.tpanh.jobfinder.viewmodel

import androidx.lifecycle.ViewModel
import com.tpanh.jobfinder.model.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ViewProfileViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(User(location = "Quang Nam", fullName = "Tran Phuoc Anh"))
    val uiState = _uiState.asStateFlow()
}