package com.tpanh.jobfinder.di

import androidx.compose.runtime.key
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.tpanh.jobfinder.JobFinderApplication
import com.tpanh.jobfinder.viewmodel.ForgotPasswordViewModel
import com.tpanh.jobfinder.viewmodel.HomeViewModel
import com.tpanh.jobfinder.viewmodel.JobDescriptionViewModel
import com.tpanh.jobfinder.viewmodel.LoginViewModel
import com.tpanh.jobfinder.viewmodel.PostJobViewModel
import com.tpanh.jobfinder.viewmodel.SaveJobViewModel
import com.tpanh.jobfinder.viewmodel.SearchJobViewModel
import com.tpanh.jobfinder.viewmodel.SignUpViewModel
import com.tpanh.jobfinder.viewmodel.SpecializationViewModel
import com.tpanh.jobfinder.viewmodel.UploadCvViewModel

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

        initializer {
            val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as JobFinderApplication)
            PostJobViewModel(
                jobRepository = application.container.jobRepository,
                categoryRepository = application.container.categoryRepository,
                imageRepository = application.container.imageRepository
            )
        }

        initializer {
            val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as JobFinderApplication)
            HomeViewModel(
                jobRepository = application.container.jobRepository,
                userRepository = application.container.userRepository
            )
        }

        initializer {
            val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as JobFinderApplication)
            JobDescriptionViewModel(
                jobRepository = application.container.jobRepository
            )
        }

        initializer {
            val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as JobFinderApplication)
            UploadCvViewModel(
                jobApplyRepository = application.container.jobApplyRepository,
                imageRepository = application.container.imageRepository,
                jobRepository = application.container.jobRepository
            )
        }

        initializer {
            val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as JobFinderApplication)
            SaveJobViewModel(
                userRepository = application.container.userRepository,
                jobRepository = application.container.jobRepository
            )
        }

        initializer {
            val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as JobFinderApplication)
            SearchJobViewModel(
                jobRepository = application.container.jobRepository,
                userRepository = application.container.userRepository
            )
        }

        initializer {
            val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as JobFinderApplication)
            SpecializationViewModel(
                categoryRepository = application.container.categoryRepository
            )
        }
    }
}

//fun CreationExtras.jobFinderApplication(): JobFinderApplication =
//    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as JobFinderApplication)