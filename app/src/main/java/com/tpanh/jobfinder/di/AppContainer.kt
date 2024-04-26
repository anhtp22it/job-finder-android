package com.tpanh.jobfinder.di

import com.tpanh.jobfinder.repository.AuthRepository

interface AppContainer {
    val authRepository: AuthRepository
}