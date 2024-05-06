package com.tpanh.jobfinder.di

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.tpanh.jobfinder.JobFinderApplication
import com.tpanh.jobfinder.viewmodel.ForgotPasswordViewModel
import com.tpanh.jobfinder.viewmodel.LoginViewModel
import com.tpanh.jobfinder.viewmodel.SignUpViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as JobFinderApplication)
            SignUpViewModel(
                authRepository = application.container.authRepository
            )
        }

        initializer {
            val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as JobFinderApplication)
            LoginViewModel(
                authRepository = application.container.authRepository
            )
        }

        initializer {
            val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as JobFinderApplication)
            ForgotPasswordViewModel(
                authRepository = application.container.authRepository
            )
        }
    }
}

fun CreationExtras.shopManagementApplication(): JobFinderApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as JobFinderApplication)