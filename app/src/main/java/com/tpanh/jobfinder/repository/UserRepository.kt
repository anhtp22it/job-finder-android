package com.tpanh.jobfinder.repository

import com.tpanh.jobfinder.model.Job
import com.tpanh.jobfinder.model.User

interface UserRepository {
    suspend fun getCurrentUser(): User
    suspend fun getSavedJobs(): List<String>
    suspend fun saveJob(jobId: String)
    suspend fun removeSavedJob(jobId: String)
}