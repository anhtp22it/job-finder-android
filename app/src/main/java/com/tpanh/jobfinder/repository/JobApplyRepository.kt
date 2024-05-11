package com.tpanh.jobfinder.repository

import com.tpanh.jobfinder.model.JobApply

interface JobApplyRepository {
    suspend fun applyJob(apply: JobApply)
}