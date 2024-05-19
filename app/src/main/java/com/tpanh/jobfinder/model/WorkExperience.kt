package com.tpanh.jobfinder.model

data class WorkExperience(
    val jobTitle: String = "",
    val company: String = "",
    val startDay: Long = System.currentTimeMillis(),
    val endDay: Long = System.currentTimeMillis(),
    val description: String = "",
    val isCurrentWorking: Boolean = false
)
