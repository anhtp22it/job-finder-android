package com.tpanh.jobfinder.di

import android.content.Context
import com.tpanh.jobfinder.repository.AuthRepository
import com.tpanh.jobfinder.repository.CategoryRepository
import com.tpanh.jobfinder.repository.ImageRepository
import com.tpanh.jobfinder.repository.JobRepository
import com.tpanh.jobfinder.repository.UserRepository
import com.tpanh.jobfinder.repository.impl.AuthRepositoryImpl
import com.tpanh.jobfinder.repository.impl.CategoryRepositoryImpl
import com.tpanh.jobfinder.repository.impl.ImageRepositoryImpl
import com.tpanh.jobfinder.repository.impl.JobRepositoryImpl
import com.tpanh.jobfinder.repository.impl.UserRepositoryImpl

class DefaultAppContainer(private val context: Context): AppContainer {
    override val authRepository: AuthRepository by lazy {
        AuthRepositoryImpl(FirebaseModule.auth(), FirebaseModule.fireStore())
    }

    override val jobRepository: JobRepository by lazy {
        JobRepositoryImpl(FirebaseModule.fireStore(), FirebaseModule.auth())
    }

    override val categoryRepository: CategoryRepository by lazy {
        CategoryRepositoryImpl(FirebaseModule.fireStore())
    }
    override val imageRepository: ImageRepository by lazy {
        ImageRepositoryImpl(FirebaseModule.storage())
    }
    override val userRepository: UserRepository by lazy {
        UserRepositoryImpl(FirebaseModule.fireStore(), FirebaseModule.auth())
    }



}