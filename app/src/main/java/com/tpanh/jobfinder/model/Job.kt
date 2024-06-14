package com.tpanh.jobfinder.model

data class Job (
    var id: String = "",
    var title: String = "",
    var description: String = "",
    var location: String = "",
    var company: String = "",
    var image: String = "",
    var type: JobType = JobType.FULL_TIME,
    var requirements: List<String> = listOf(),
    var categoryId: String = "",
    var workplace: Workplace = Workplace.ON_SITE,
    var subCategory: String = "",
    var salary: String = "",
    var createdAt: Long = System.currentTimeMillis(),
    var userId: String = ""
)