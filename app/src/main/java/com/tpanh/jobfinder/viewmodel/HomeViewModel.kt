package com.tpanh.jobfinder.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tpanh.jobfinder.model.Job
import com.tpanh.jobfinder.model.User
import com.tpanh.jobfinder.repository.JobRepository
import com.tpanh.jobfinder.repository.UserRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val jobRepository: JobRepository,
    private val userRepository: UserRepository
): ViewModel() {

    private val _jobs = MutableStateFlow(emptyList<Job>())
    val jobs = _jobs.asStateFlow()

    private val _user = MutableStateFlow(User())
    val user = _user.asStateFlow()

    init {
        getJobs()
        getUser()
    }

    private fun getJobs() {
        viewModelScope.launch {
            val jobs = jobRepository.getAllJob()
            _jobs.value = jobs
        }
    }

    fun saveJob(job: Job) {
        viewModelScope.launch {
            jobRepository.saveJob(job)
        }
    }

    fun unSaveJob(job: Job) {
        viewModelScope.launch {
            jobRepository.unSaveJob(job)
        }
    }

    fun isSaved(job: Job): Boolean {
        return user.value.saveJobs.contains(job.id)
    }

    private fun getUser() {
        viewModelScope.launch {
            val user = userRepository.getCurrentUser()
            _user.value = user
        }
    }
}