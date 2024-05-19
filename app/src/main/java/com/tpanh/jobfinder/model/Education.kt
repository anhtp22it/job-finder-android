package com.tpanh.jobfinder.model

data class Education(
    val levelOfEducation: String = "",
    val institutionName: String = "",
    val fieldOfStudy: String = "",
    val description: String = "",
    val currentlyStudying: Boolean = false,
    val startDate: Long = System.currentTimeMillis(),
    val endDate: Long = System.currentTimeMillis(),
)
