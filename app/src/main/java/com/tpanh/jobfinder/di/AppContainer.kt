package com.tpanh.jobfinder.di

import com.tpanh.jobfinder.repository.AuthRepository
import com.tpanh.jobfinder.repository.CategoryRepository
import com.tpanh.jobfinder.repository.ImageRepository
import com.tpanh.jobfinder.repository.JobRepository
import com.tpanh.jobfinder.repository.UserRepository

interface AppContainer {
    val authRepository: AuthRepository
    val jobRepository: JobRepository
    val categoryRepository: CategoryRepository
    val imageRepository: ImageRepository
    val userRepository: UserRepository
}