package com.tpanh.jobfinder.viewmodel

import androidx.lifecycle.ViewModel
import com.tpanh.jobfinder.model.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AboutMeViewModel: ViewModel() {

    private val _uiState = MutableStateFlow(User(about = ""))
    val uiState = _uiState.asStateFlow()

    fun updateAboutMe(aboutMe: String) {
        _uiState.update {
            it.copy(about = aboutMe)
        }
    }

}