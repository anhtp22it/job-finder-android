package com.tpanh.jobfinder.model

data class JobApply(
    val id: String = "",
    val jobId: String = "",
    val userId: String = "",
    val cv: String = "",
    val status: ApplicationStatus = ApplicationStatus.PENDING,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis(),
)
