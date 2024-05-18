package com.tpanh.jobfinder.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tpanh.jobfinder.model.Category
import com.tpanh.jobfinder.model.Job
import com.tpanh.jobfinder.model.JobFilter
import com.tpanh.jobfinder.model.User
import com.tpanh.jobfinder.repository.CategoryRepository
import com.tpanh.jobfinder.repository.JobRepository
import com.tpanh.jobfinder.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SearchJobViewModel(
    private val jobRepository: JobRepository,
    private val userRepository: UserRepository
): ViewModel() {
    private val _jobs = MutableStateFlow<List<Job>>(emptyList())
    val jobs = _jobs.asStateFlow()

    private val _user = MutableStateFlow(User())
    val user = _user.asStateFlow()

    var query by mutableStateOf("")

    init {
        searchJob()
        getUser()
    }

    fun saveJob(job: Job) {
        viewModelScope.launch {
            userRepository.saveJob(job.id)
        }
    }

    fun unSaveJob(job: Job) {
        viewModelScope.launch {
            userRepository.removeSavedJob(job.id)
        }
    }

    fun isSaved(job: Job): Boolean {
        return user.value.saveJobs.contains(job.id)
    }

     fun searchJob() {
        viewModelScope.launch {
            val jobs = jobRepository.searchJob(query)
            _jobs.value = jobs
        }
    }

    private fun getUser() {
        viewModelScope.launch {
            val user = userRepository.getCurrentUser()
            _user.value = user
        }
    }
}