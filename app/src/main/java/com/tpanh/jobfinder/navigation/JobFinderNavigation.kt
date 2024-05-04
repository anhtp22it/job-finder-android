package com.tpanh.jobfinder.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.tpanh.jobfinder.screens.AboutMe
import com.tpanh.jobfinder.screens.AddAJob
import com.tpanh.jobfinder.screens.AddEducation
import com.tpanh.jobfinder.screens.AddLanguage
import com.tpanh.jobfinder.screens.AddResume
import com.tpanh.jobfinder.screens.AddSkill
import com.tpanh.jobfinder.screens.AddWorkExperience
import com.tpanh.jobfinder.screens.ChangePassword
import com.tpanh.jobfinder.screens.EditProfile
import com.tpanh.jobfinder.screens.Filter
import com.tpanh.jobfinder.screens.ForgotPassword
import com.tpanh.jobfinder.screens.Home
import com.tpanh.jobfinder.screens.JobDescription
import com.tpanh.jobfinder.screens.Language
import com.tpanh.jobfinder.screens.ListMyApplication
import com.tpanh.jobfinder.screens.Login
import com.tpanh.jobfinder.screens.MyApplication
import com.tpanh.jobfinder.screens.OnBoarding
import com.tpanh.jobfinder.screens.SaveJob
import com.tpanh.jobfinder.screens.SearchJob
import com.tpanh.jobfinder.screens.SignUp
import com.tpanh.jobfinder.screens.Specialization
import com.tpanh.jobfinder.screens.Success
import com.tpanh.jobfinder.screens.UploadCv
import com.tpanh.jobfinder.screens.Verify
import com.tpanh.jobfinder.screens.ViewProfile

@Composable
fun JobFinderNavigation(
    navController: NavHostController = rememberNavController()
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = JobFinderScreen.valueOf(
        backStackEntry?.destination?.route ?: JobFinderScreen.Home.name
    )

    NavHost(navController = navController, startDestination = JobFinderScreen.OnBoarding.name) {
        composable(JobFinderScreen.OnBoarding.name) {
            OnBoarding(
                navigateToLoginScreen = { navController.navigate(JobFinderScreen.Login.name) }
            )
        }

        composable(route = JobFinderScreen.Login.name) {
            Login(
                navigateToSignUp = { navController.navigate(JobFinderScreen.SignUp.name) },
                navigateToForgotPassword = { navController.navigate(JobFinderScreen.ForgotPassword.name) },
                navigateToHome = { navController.navigate(JobFinderScreen.Home.name) }
            )
        }

        composable(route = JobFinderScreen.SignUp.name) {
            SignUp(
                navigateToLogin = { navController.navigate(JobFinderScreen.Login.name) },
                navigateToForgotPassword = { navController.navigate(JobFinderScreen.ForgotPassword.name) },
            )
        }

        composable(JobFinderScreen.ForgotPassword.name) {
            ForgotPassword(
                navigateToLogin = { navController.navigate(JobFinderScreen.Login.name) },
                navigateToVerifyCation = { navController.navigate(JobFinderScreen.Verify.name) }
            )
        }

        composable(JobFinderScreen.Success.name) {
            Success(
                navigateToLogin = { navController.navigate(JobFinderScreen.Login.name) },
                navigateToHome = { navController.navigate(JobFinderScreen.Home.name) }
            )
        }

        composable(JobFinderScreen.ChangePassword.name) {
            ChangePassword(
                onBack = { navController.navigateUp() },
                navigateToLogin = { navController.navigate(JobFinderScreen.Login.name) }
            )
        }

        composable(JobFinderScreen.Verify.name) {
            Verify(
                navigateToLogin = { navController.navigate(JobFinderScreen.Login.name) }
            )
        }

        composable(JobFinderScreen.Home.name) {
            Home(
                navigateToHome = { navController.navigate(JobFinderScreen.Home.name) },
                navigateToSaveJob = { navController.navigate(JobFinderScreen.Home.name) },
                navigateToProfile = { navController.navigate(JobFinderScreen.EditProfile.name) },
                navigateToPostJob = { navController.navigate(JobFinderScreen.PostJob.name) },
                navigateToSearch = { navController.navigate(JobFinderScreen.SearchJob.name) },
                currentScreen = currentScreen
            )
        }

        composable(JobFinderScreen.SearchJob.name) {
            SearchJob()
        }

        composable(JobFinderScreen.JobDescription.name + "/{jobId}") {
            JobDescription(
                navigateToUploadCv = { navController.navigate(JobFinderScreen.UploadCv.name) }
            )
        }

        composable(JobFinderScreen.UploadCv.name) {
            UploadCv(
                navigateBack = { navController.navigateUp() },
                navigateToSearchJob = { navController.navigate(JobFinderScreen.SearchJob.name) }
            )
        }

        composable(JobFinderScreen.Specialization.name) {
            Specialization(
                navigateBack = { navController.navigateUp() },
                navigateToFilter = { navController.navigate(JobFinderScreen.Filter.name) }
            )
        }

        composable(JobFinderScreen.Filter.name) {
            Filter(
                navigateBack = { navController.navigateUp() }
            )
        }

        composable(JobFinderScreen.PostJob.name) {
            AddAJob(
                navigateToHome = { navController.navigate(JobFinderScreen.Home.name) }
            )
        }

        composable(JobFinderScreen.SaveJob.name) {
            SaveJob(
                currentScreen = currentScreen,
                navigateToHome = { navController.navigate(JobFinderScreen.Home.name) },
                navigateToSaveJob = { },
                navigateToProfile = { navController.navigate(JobFinderScreen.ViewProfile.name) },
                navigateToPostJob = { navController.navigate(JobFinderScreen.PostJob.name) },
                navigateToSearch = { navController.navigate(JobFinderScreen.SearchJob.name) }
            )
        }

        composable(JobFinderScreen.MyApplications.name) {
            ListMyApplication()
        }

        composable(JobFinderScreen.MyApplication.name) {
            MyApplication(
                navigateBack = { navController.navigateUp() },
                navigateToSearchJob = { navController.navigate(JobFinderScreen.SearchJob.name) }
            )
        }

        composable(JobFinderScreen.ViewProfile.name) {
            ViewProfile(
                navigateToHome = { navController.navigate(JobFinderScreen.Home.name) },
                navigateToSaveJob = { navController.navigate(JobFinderScreen.Home.name) },
                navigateToProfile = { navController.navigate(JobFinderScreen.EditProfile.name) },
                navigateToPostJob = { navController.navigate(JobFinderScreen.PostJob.name) },
                navigateToSearch = { navController.navigate(JobFinderScreen.SearchJob.name) },
                currentScreen = currentScreen,
                navigateToAboutMe = { navController.navigate(JobFinderScreen.AboutMe.name) },
                navigateToWorkExperience = { navController.navigate(JobFinderScreen.AddWorkExperience.name) },
                navigateToEducation = { navController.navigate(JobFinderScreen.AddEducation.name) },
                navigateToSkill = { navController.navigate(JobFinderScreen.AddSkill.name) },
                navigateToLanguage = { navController.navigate(JobFinderScreen.Language.name) },
                navigateToEditProfile = { navController.navigate(JobFinderScreen.EditProfile.name) },
            )
        }

        composable(JobFinderScreen.AboutMe.name) {
            AboutMe(
                navigateBack = { navController.navigateUp() },
                navigateToViewProfile = { navController.navigate(JobFinderScreen.ViewProfile.name) }
            )
        }

        composable(JobFinderScreen.AddEducation.name) {
            AddEducation(
                navigateBack = { navController.navigateUp() },
                navigateToViewProfile = { navController.navigate(JobFinderScreen.ViewProfile.name) }
            )
        }

        composable(JobFinderScreen.AddWorkExperience.name) {
            AddWorkExperience(
                navigateBack = { navController.navigateUp() },
                navigateToViewProfile = { navController.navigate(JobFinderScreen.ViewProfile.name) }
            )
        }

        composable(JobFinderScreen.AddSkill.name) {
            AddSkill(
                onBackClick = { navController.navigateUp() },
                navigateToViewProfile = { navController.navigate(JobFinderScreen.ViewProfile.name) }
            )
        }

        composable(JobFinderScreen.Language.name) {
            Language(
                navigateToAddLanguageScreen = { navController.navigate(JobFinderScreen.AddLanguage.name) },
                navigateBack = { navController.navigateUp() },
                naviagetToViewProfile = { navController.navigate(JobFinderScreen.ViewProfile.name) }
            )
        }

        composable(JobFinderScreen.AddLanguage.name) {
            AddLanguage(
                onBackClick = { navController.navigateUp() },
                navigateToLanguage = { navController.navigate(JobFinderScreen.Language.name) }
            )
        }

        composable(JobFinderScreen.AddResume.name) {
            AddResume(
                onBackClick = { navController.navigateUp() },
                navigateToViewProfile = { navController.navigate(JobFinderScreen.ViewProfile.name) }
            )
        }

        composable(JobFinderScreen.EditProfile.name) {
            EditProfile(
                navigateToSetting = { },
                navigateToViewProfile = { navController.navigate(JobFinderScreen.ViewProfile.name) },
            )
        }
    }
}