package com.tpanh.jobfinder.di

import android.content.Context
import com.tpanh.jobfinder.repository.AuthRepository
import com.tpanh.jobfinder.repository.impl.AuthRepositoryImpl

class DefaultAppContainer(private val context: Context): AppContainer {
    override val authRepository: AuthRepository by lazy {
        AuthRepositoryImpl(FirebaseModule.auth())
    }
}