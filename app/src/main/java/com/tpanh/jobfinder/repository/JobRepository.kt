package com.tpanh.jobfinder.repository

import com.tpanh.jobfinder.model.Job

interface JobRepository {
    fun getAllJob(): List<Job>
    fun getJobByUserId(userId: String): List<Job>
    fun getJobById(id: String): Job
    fun getJobByCategory(categoryId: String): List<Job>
    fun searchJob(title: String): List<Job>
    fun postJob(job: Job)
}