package com.tpanh.jobfinder.viewmodel

import android.net.Uri
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tpanh.jobfinder.model.Job
import com.tpanh.jobfinder.model.JobApply
import com.tpanh.jobfinder.model.User
import com.tpanh.jobfinder.repository.ImageRepository
import com.tpanh.jobfinder.repository.JobApplyRepository
import com.tpanh.jobfinder.repository.JobRepository
import com.tpanh.jobfinder.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class UploadCvViewModel(
    private val jobApplyRepository: JobApplyRepository,
    private val jobRepository: JobRepository,
    private val imageRepository: ImageRepository,
    private val userRepository: UserRepository
): ViewModel() {

    var pdfUri by mutableStateOf<Uri?>(null)

    var isSuccess by mutableStateOf(false)

    private val _job = MutableStateFlow(Job())
    val job = _job.asStateFlow()

    private val _applyJob = MutableStateFlow(JobApply())
    val applyJob = _applyJob.asStateFlow()

    private val _currentUser = MutableStateFlow(User())
    private val currentUser = _currentUser.asStateFlow()

    init {
        getCurrentUser()
    }

    fun getJob(jobId: String) {
        viewModelScope.launch {
            val job = jobRepository.getJobById(jobId)
            _job.value = job
        }
    }

    fun onCvSelected(uri: Uri) {
        pdfUri = uri
    }

    fun onDescriptionChanged(description: String) {
        _applyJob.update {
            it.copy(description = description)
        }
    }

    fun onEmailChanged(email: String) {
        _applyJob.update {
            it.copy(email = email)
        }
    }

    fun onPhoneChanged(phone: String) {
        _applyJob.update {
            it.copy(phoneNumber = phone)
        }
    }

    fun applyJob() {
        _applyJob.update {
            it.copy(job = job.value)
        }
        viewModelScope.launch {
            pdfUri?.let { uri ->
                val cvUrl = imageRepository.uploadImage(uri, "cvs")
                _applyJob.update {
                    it.copy(cv = cvUrl)
                }
            }
            jobApplyRepository.applyJob(applyJob.value)
            isSuccess = true
        }
    }

    fun getCurrentUser() {
        viewModelScope.launch {
            val user = userRepository.getCurrentUser()
            _currentUser.value = user
            _applyJob.update { it.copy(email = user.email, phoneNumber = user.phoneNumber)}
        }
    }
}