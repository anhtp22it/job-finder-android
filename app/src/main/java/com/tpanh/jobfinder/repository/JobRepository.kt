package com.tpanh.jobfinder.repository

import com.tpanh.jobfinder.model.Job
import com.tpanh.jobfinder.model.JobFilter
import kotlinx.coroutines.flow.Flow

interface JobRepository {
    suspend fun getAllJob(): List<Job>
    suspend fun getJobByUserId(userId: String): List<Job>
    suspend fun getJobById(id: String): Job
    suspend fun getJobByCategory(categoryId: String): List<Job>
    suspend fun searchJob(title: String): List<Job>
    suspend fun postJob(job: Job)
    suspend fun countJobByCategory(categoryId: String): Int
}