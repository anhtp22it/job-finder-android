package com.tpanh.jobfinder.di

import androidx.compose.runtime.key
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.tpanh.jobfinder.JobFinderApplication
import com.tpanh.jobfinder.viewmodel.AboutMeViewModel
import com.tpanh.jobfinder.viewmodel.AddEducationViewModel
import com.tpanh.jobfinder.viewmodel.AddSkillViewModel
import com.tpanh.jobfinder.viewmodel.ApplyJobDescriptionViewModel
import com.tpanh.jobfinder.viewmodel.EditProfileViewModel
import com.tpanh.jobfinder.viewmodel.ForgotPasswordViewModel
import com.tpanh.jobfinder.viewmodel.HomeViewModel
import com.tpanh.jobfinder.viewmodel.JobDescriptionViewModel
import com.tpanh.jobfinder.viewmodel.LanguageViewModel
import com.tpanh.jobfinder.viewmodel.ListMyApplicationViewModel
import com.tpanh.jobfinder.viewmodel.LoginViewModel
import com.tpanh.jobfinder.viewmodel.MyApplicationViewModel
import com.tpanh.jobfinder.viewmodel.MyJobsViewModel
import com.tpanh.jobfinder.viewmodel.PostJobViewModel
import com.tpanh.jobfinder.viewmodel.SaveJobViewModel
import com.tpanh.jobfinder.viewmodel.SearchJobViewModel
import com.tpanh.jobfinder.viewmodel.SignUpViewModel
import com.tpanh.jobfinder.viewmodel.SpecializationViewModel
import com.tpanh.jobfinder.viewmodel.UploadCvViewModel
import com.tpanh.jobfinder.viewmodel.ViewProfileViewModel
import com.tpanh.jobfinder.viewmodel.WorkExperienceViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {

        initializer {
            val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as JobFinderApplication)
            LoginViewModel(
                authRepository = application.container.authRepository
            )
        }

        initializer {
            val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as JobFinderApplication)
            SignUpViewModel(
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
            SearchJobViewModel(
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
            SpecializationViewModel(
                categoryRepository = application.container.categoryRepository
            )
        }

        initializer {
            val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as JobFinderApplication)
            EditProfileViewModel(
                authRepository = application.container.authRepository,
                userRepository = application.container.userRepository,
                imageRepository = application.container.imageRepository
            )
        }

        initializer {
            val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as JobFinderApplication)
            ListMyApplicationViewModel(
                jobApplyRepository = application.container.jobApplyRepository,
                userRepository = application.container.userRepository
            )
        }

        initializer {
            val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as JobFinderApplication)
            MyApplicationViewModel(
                jobApplyRepository = application.container.jobApplyRepository,
                imageRepository = application.container.imageRepository
            )
        }

        initializer {
            val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as JobFinderApplication)
            MyJobsViewModel(
                jobRepository = application.container.jobRepository
            )
        }

        initializer {
            val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as JobFinderApplication)
            ApplyJobDescriptionViewModel(
                jobApplyRepository = application.container.jobApplyRepository,
                userRepository = application.container.userRepository
            )
        }

        initializer {
            val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as JobFinderApplication)
            ViewProfileViewModel(
                userRepository = application.container.userRepository
            )
        }

        initializer {
            val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as JobFinderApplication)
            AboutMeViewModel(
                userRepository = application.container.userRepository
            )
        }

        initializer {
            val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as JobFinderApplication)
            WorkExperienceViewModel(
                userRepository = application.container.userRepository
            )
        }

        initializer {
            val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as JobFinderApplication)
            AddEducationViewModel(
                userRepository = application.container.userRepository
            )
        }

        initializer {
            val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as JobFinderApplication)
            AddSkillViewModel(
                userRepository = application.container.userRepository
            )
        }

        initializer {
            val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as JobFinderApplication)
            LanguageViewModel(
                userRepository = application.container.userRepository
            )
        }
    }
}

//fun CreationExtras.jobFinderApplication(): JobFinderApplication =
//    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as JobFinderApplication)