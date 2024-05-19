package com.tpanh.jobfinder.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tpanh.jobfinder.model.Job
import com.tpanh.jobfinder.repository.JobRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MyJobsViewModel(
    private val jobRepository: JobRepository
): ViewModel() {
    private val _myJobs = MutableStateFlow<List<Job>>(emptyList())
    val myJobs = _myJobs.asStateFlow()

    init {
        loadMyJobs()
    }

    private fun loadMyJobs() {
        viewModelScope.launch {
            _myJobs.value = jobRepository.getJobByCurrentUser()
        }
    }
}
