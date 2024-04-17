package com.tpanh.jobfinder.model

data class User(
    var fullName: String = "",
    var gender: Int = 1,
    var email: String = "",
    var phoneNumber: String = "",
    var location: String = "",
    var dateOfBirth: Long = System.currentTimeMillis(),
    var avatar: String = "",
    var resume: String = ""
)