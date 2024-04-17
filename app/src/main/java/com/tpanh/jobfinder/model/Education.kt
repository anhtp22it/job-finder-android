package com.tpanh.jobfinder.model

data class Education(
    var levelOfEducation: String = "",
    var institutionName: String = "",
    var fieldOfStudy: String = "",
    var description: String = "",
    var isCurrentlyStudying: Boolean = false,
    var startDate: Long = System.currentTimeMillis(),
    var endDate: Long = System.currentTimeMillis(),
)
