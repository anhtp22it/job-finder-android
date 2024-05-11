package com.tpanh.jobfinder.repository

import com.tpanh.jobfinder.model.User

interface UserRepository {
    suspend fun getCurrentUser(): User
}