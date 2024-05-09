package com.tpanh.jobfinder.repository

import com.google.firebase.auth.AuthResult
import com.tpanh.jobfinder.utils.Resource
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun loginUser(email: String, password: String): Flow<Resource<AuthResult>>
    fun registerUser(fullName: String, email: String, password: String): Flow<Resource<AuthResult>>
    fun changePassword(oldPassword: String, newPassword: String): Flow<Resource<AuthResult>>
    fun sendEmailResetPassword(email: String): Flow<Resource<AuthResult>>
}