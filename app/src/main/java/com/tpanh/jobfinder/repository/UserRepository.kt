package com.tpanh.jobfinder.repository


import com.tpanh.jobfinder.model.Education
import com.tpanh.jobfinder.model.Job
import com.tpanh.jobfinder.model.User
import com.tpanh.jobfinder.model.WorkExperience

interface UserRepository {
    suspend fun getCurrentUser(): User
    suspend fun updateUser(user: User)
    suspend fun addEducation (userId: String, education: Education)
    suspend fun addWorkExperience (userId: String, workExperience: WorkExperience)
    suspend fun getSavedJobs(): List<String>
    suspend fun getUserById(userId: String): User
    suspend fun saveJob(jobId: String)
    suspend fun removeSavedJob(jobId: String)
    suspend fun updateResume (userId: String, resume: String)
    suspend fun updateAboutMe (userId: String, aboutme: String)
    suspend fun addSkill (userId: String, skill: String)
    suspend fun addLanguage (userId: String, language: String)
}