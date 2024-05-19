package com.tpanh.jobfinder.model

data class User(
    val id: String? = "",
    val fullName: String = "",
    val gender: Int = 1,
    val email: String = "",
    val phoneNumber: String = "",
    val location: String = "",
    val dateOfBirth: Long = System.currentTimeMillis(),
    val avatar: String = "",
    val resume: String = "",
    var password: String = "",
    var about: String = "",
    var createdAt: Long = System.currentTimeMillis(),
    var emailVerifyToken: String = "",
    var forgotPasswordToken: String = "",
    var verify: UserVerifyStatus = UserVerifyStatus.NOT_VERIFY,
    var saveJobs: List<String> = emptyList(),
    var education: Education = Education(),
    var skills: List<String> = emptyList(),
    var languages: List<Language> = emptyList(),
    var experience: WorkExperience = WorkExperience(),
)