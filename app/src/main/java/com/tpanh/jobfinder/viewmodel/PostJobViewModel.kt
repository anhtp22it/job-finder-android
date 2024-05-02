package com.tpanh.jobfinder.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.tpanh.jobfinder.model.Job
import com.tpanh.jobfinder.model.JobInfor
import com.tpanh.jobfinder.model.JobType
import com.tpanh.jobfinder.model.Workplace
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PostJobViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(Job(description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."))
    val uiState = _uiState.asStateFlow()

    var titleDialog by mutableStateOf(false)

    var workplaceDialog by mutableStateOf(false)

    var jobTypeDialog by mutableStateOf(false)

    var locationDialog by mutableStateOf(false)

    var descDialog by mutableStateOf(false)

    var companyDialog by mutableStateOf(false)

    fun updateTitle(title: String) {
        _uiState.update {
            it.copy(title = title)
        }
    }

    fun updateDescription(description: String) {
        _uiState.update {
            it.copy(description = description)
        }
    }

    fun updateLocation(location: String) {
        _uiState.update {
            it.copy(location = location)
        }
    }

    fun updateCompany(company: String) {
        _uiState.update {
            it.copy(company = company)
        }
    }

    fun updateWorkplace(workplace: Workplace) {
        _uiState.update {
            it.copy(workplace = workplace)
        }
    }

    fun updateJobType(jobType: JobType) {
        _uiState.update {
            it.copy(type = jobType)
        }
    }
}