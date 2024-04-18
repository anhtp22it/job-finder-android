package com.tpanh.jobfinder.model

data class User(
    val id: Int,
    val fullName: String = "",
    val gender: Int = 1,
    val email: String = "",
    val phoneNumber: String = "",
    val location: String = "",
    val dateOfBirth: Long = System.currentTimeMillis(),
    val avatar: String = "",
    val resume: String = ""
)