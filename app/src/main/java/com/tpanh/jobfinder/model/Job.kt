package com.tpanh.jobfinder.model

data class Job (
    var id: String = "",
    var title: String = "",
    var description: String = "",
    var location: String = "",
    var company: String = "",
    var image: String = "",
    var type: JobType = JobType.FULL_TIME,
    var requirements: Set<String> = setOf(),
    var category: Category? = null,
    var workplace: Workplace? = null,
    var subCategory: String = "",
    var salary: Int = 0,
    var createdAt: Long = System.currentTimeMillis(),
    var applies: List<User> = listOf()
)