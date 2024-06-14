package com.tpanh.jobfinder.repository

import com.tpanh.jobfinder.model.Job

interface JobRepository {
    suspend fun getAllJob(): List<Job>
    suspend fun getJobByCurrentUser(): List<Job>
    suspend fun getJobById(id: String): Job
    suspend fun getJobByCategory(categoryId: String): List<Job>
    suspend fun searchJob(title: String): List<Job>
    suspend fun postJob(job: Job): Job
    suspend fun countJobByCategory(categoryId: String): Int
}