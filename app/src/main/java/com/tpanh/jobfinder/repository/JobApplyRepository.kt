package com.tpanh.jobfinder.repository

import com.tpanh.jobfinder.model.JobApply

interface JobApplyRepository {
    suspend fun applyJob(apply: JobApply)
    suspend fun getMyApplies(): List<JobApply>
    suspend fun getApplyById(id: String): JobApply
    suspend fun getApplyByJobId(jobId: String): List<JobApply>
    suspend fun acceptApplication(applyId: String)
    suspend fun rejectApplication(applyId: String)
}