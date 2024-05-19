package com.tpanh.jobfinder.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tpanh.jobfinder.model.Job
import com.tpanh.jobfinder.model.JobType
import com.tpanh.jobfinder.model.User
import com.tpanh.jobfinder.repository.JobRepository
import com.tpanh.jobfinder.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SaveJobViewModel(
    private val userRepository: UserRepository,
    private val jobRepository: JobRepository
): ViewModel() {
    private val _jobList = MutableStateFlow<List<Job>>(listOf())
    val jobList = _jobList.asStateFlow()

    var openDialog by mutableStateOf(false)
    var selectedJob by mutableStateOf<Job?>(null)

    init {
        getSavedJobs()
    }

    fun removeJobSaved(jobId: String) {
        viewModelScope.launch {
            userRepository.removeSavedJob(jobId)
            getSavedJobs()
        }
    }

    private fun getSavedJobs() {
        viewModelScope.launch {
            val savedJobs = userRepository.getSavedJobs()
            val jobs = savedJobs.map { jobId -> jobRepository.getJobById(jobId) }
            _jobList.value = jobs
        }
    }
}