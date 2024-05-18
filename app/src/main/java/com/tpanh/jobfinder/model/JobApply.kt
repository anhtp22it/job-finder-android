package com.tpanh.jobfinder.model

data class JobApply(
    var id: String = "",
    val job: Job = Job(),
    var userId: String = "",
    val cv: String = "",
    val description: String = "",
    val status: ApplicationStatus = ApplicationStatus.PENDING,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis(),
)
