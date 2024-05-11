package com.tpanh.jobfinder.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tpanh.jobfinder.model.Job
import com.tpanh.jobfinder.repository.JobRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class JobDescriptionViewModel(
    private val jobRepository: JobRepository
): ViewModel() {

    private val _job = MutableStateFlow(Job())
    val job = _job.asStateFlow()

    fun getJobById(jobId: String) {
        viewModelScope.launch {
            val result = jobRepository.getJobById(jobId)
            _job.value = result
        }
    }
}
