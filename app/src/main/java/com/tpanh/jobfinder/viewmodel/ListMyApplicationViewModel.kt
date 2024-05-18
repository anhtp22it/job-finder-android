package com.tpanh.jobfinder.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tpanh.jobfinder.model.Job
import com.tpanh.jobfinder.model.JobApply
import com.tpanh.jobfinder.model.User
import com.tpanh.jobfinder.repository.JobApplyRepository
import com.tpanh.jobfinder.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ListMyApplicationViewModel(
    private val jobApplyRepository: JobApplyRepository,
    private val userRepository: UserRepository
): ViewModel() {
    private val _myApplies = MutableStateFlow<List<JobApply>>(listOf())
    val myApplies = _myApplies.asStateFlow()

    private val _user = MutableStateFlow(User())
    val user = _user.asStateFlow()

    init {
        myApplies()
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

    private fun myApplies() {
        viewModelScope.launch {
            val applies = jobApplyRepository.getMyApplies()
            _myApplies.value = applies
        }
    }

    private fun getUser() {
        viewModelScope.launch {
            val user = userRepository.getCurrentUser()
            _user.value = user
        }
    }
}