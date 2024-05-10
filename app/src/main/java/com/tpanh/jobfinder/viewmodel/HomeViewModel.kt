package com.tpanh.jobfinder.viewmodel

import androidx.lifecycle.ViewModel
import com.tpanh.jobfinder.model.Job
import com.tpanh.jobfinder.repository.JobRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeViewModel(
    private val jobRepository: JobRepository
): ViewModel() {

    private val _jobs = MutableStateFlow(emptyList<Job>())
    val jobs = _jobs.asStateFlow()

    private fun getJobs() {
        jobRepository.getAllJob().let {
            _jobs.value = it
        }
    }
}