package com.tpanh.jobfinder.model

data class JobFilter(
    var title: String = "",
    var categoryId: String = "",
    var subCategory: String = "",
    var location: String = "",
    var minSalary: Int = 0,
    var maxSalary: Int = 0,
    var jobType: JobType = JobType.FULL_TIME,
)
