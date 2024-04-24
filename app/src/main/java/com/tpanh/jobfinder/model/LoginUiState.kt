package com.tpanh.jobfinder.model

data class LoginUiState(
    val email: String = "",
    val password: String = "",

    val loginError: Boolean = false
)