package com.tpanh.jobfinder.repository

import com.tpanh.jobfinder.model.Education
import com.tpanh.jobfinder.model.Language
import com.tpanh.jobfinder.model.User
import com.tpanh.jobfinder.model.WorkExperience

interface UserRepository {
    suspend fun getCurrentUser(): User
    suspend fun updateUser(user: User)
    suspend fun updateWorkExperience(workExperience: WorkExperience)
    suspend fun updateEducation(education: Education)
    suspend fun updateLanguages(languages: List<Language>)
    suspend fun getSavedJobs(): List<String>
    suspend fun getUserById(userId: String): User
    suspend fun saveJob(jobId: String)
    suspend fun removeSavedJob(jobId: String)
}