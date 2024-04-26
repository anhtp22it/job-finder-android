package com.tpanh.jobfinder.repository

import com.google.firebase.auth.AuthResult
import com.tpanh.jobfinder.utils.Resource
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun loginUser(email: String, password: String): Flow<Resource<AuthResult>>

    fun registerUser(email: String, password: String): Flow<Resource<AuthResult>>
}