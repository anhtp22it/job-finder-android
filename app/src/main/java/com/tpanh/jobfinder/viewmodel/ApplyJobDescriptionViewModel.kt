package com.tpanh.jobfinder.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tpanh.jobfinder.model.JobApply
import com.tpanh.jobfinder.model.User
import com.tpanh.jobfinder.repository.JobApplyRepository
import com.tpanh.jobfinder.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ApplyJobDescriptionViewModel(
    private val jobApplyRepository: JobApplyRepository,
    private val userRepository: UserRepository
): ViewModel() {
    private val _candidates = MutableStateFlow<List<JobApply>>(emptyList())
    val candidates = _candidates.asStateFlow()

    private val _users = MutableStateFlow<Set<User>>(emptySet())
    val users = _users.asStateFlow()

    var currentStatus by mutableStateOf("ALL")

    fun getCandidates(jobId: String) {
        viewModelScope.launch {
            val candidates = jobApplyRepository.getApplyByJobId(jobId)
            _candidates.value = candidates
            candidates.forEach {
                val user = userRepository.getUserById(it.userId)
                _users.value = _users.value + user
            }
        }
    }

    fun acceptApplication(applyId: String, jobId: String) {
        viewModelScope.launch {
            jobApplyRepository.acceptApplication(applyId)
            getCandidates(jobId)
        }
    }

    fun rejectApplication(applyId: String, jobId: String) {
        viewModelScope.launch {
            jobApplyRepository.rejectApplication(applyId)
            getCandidates(jobId)
        }
    }
}
