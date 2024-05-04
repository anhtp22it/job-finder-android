package com.tpanh.jobfinder.viewmodel

import androidx.lifecycle.ViewModel
import com.tpanh.jobfinder.model.Job
import com.tpanh.jobfinder.model.JobType
import com.tpanh.jobfinder.model.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class SaveJobViewModel: ViewModel() {
    private val _uiState = MutableStateFlow<List<Job>>(listOf())
    val uiState = _uiState.asStateFlow()

    init {
        updateSaveJobs()
    }

    private fun updateSaveJobs() {
        _uiState.value = listOf(
            Job(
                "1",
                "Android Developer",
                "Google",
                "San Francisco",
                "CA",
                "USA",
                JobType.FULL_TIME
            ),
            Job("2", "iOS Developer", "Apple", "San Francisco", "CA", "USA", JobType.FULL_TIME),
            Job("3", "Web Developer", "Facebook", "San Francisco", "CA", "USA", JobType.FULL_TIME),
            Job(
                "4",
                "Backend Developer",
                "Amazon",
                "San Francisco",
                "CA",
                "USA",
                JobType.FULL_TIME
            ),
            Job(
                "5",
                "Frontend Developer",
                "Netflix",
                "San Francisco",
                "CA",
                "USA",
                JobType.FULL_TIME
            ),
            Job(
                "6",
                "DevOps Engineer",
                "Microsoft",
                "San Francisco",
                "CA",
                "USA",
                JobType.FULL_TIME
            ),
            Job("7", "Data Engineer", "Twitter", "San Francisco", "CA", "USA", JobType.FULL_TIME),
            Job("8", "Data Scientist", "LinkedIn", "San Francisco", "CA", "USA", JobType.FULL_TIME),
            Job(
                "9",
                "Machine Learning Engineer",
                "Uber",
                "San Francisco",
                "CA",
                "USA",
                JobType.FULL_TIME
            ),
        )
    }
}