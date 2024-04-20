package com.tpanh.jobfinder.model

data class Application(
    var id: String = "",
    var user: String = "",
    var job: String = "",
    var status: ApplicationStatus = ApplicationStatus.PENDING,
    var resume: String = "",
    var createdAt: Long = System.currentTimeMillis()
)
