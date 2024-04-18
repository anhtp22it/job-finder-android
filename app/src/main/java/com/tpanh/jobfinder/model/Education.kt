package com.tpanh.jobfinder.model

data class Education(
    val id: Int,
    val levelOfEducation: String = "",
    val institutionName: String = "",
    val fieldOfStudy: String = "",
    val description: String = "",
    val isCurrentlyStudying: Boolean = false,
    val startDate: Long = System.currentTimeMillis(),
    val endDate: Long = System.currentTimeMillis(),
)
