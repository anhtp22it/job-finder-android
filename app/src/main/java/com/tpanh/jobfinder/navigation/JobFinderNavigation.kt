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
    val currentScreen = JobFinderScreen.fromRoute(
        backStackEntry?.destination?.route ?: JobFinderScreen.Home.route
    ) ?: JobFinderScreen.Home

    NavHost(navController = navController, startDestination = JobFinderScreen.OnBoarding.route) {
        composable(JobFinderScreen.OnBoarding.route) {
            OnBoarding(
                navigateToLoginScreen = { navController.navigate(JobFinderScreen.Login.route) }
            )
        }

        composable(route = JobFinderScreen.Login.route) {
            Login(
                navigateToSignUp = { navController.navigate(JobFinderScreen.SignUp.route) },
                navigateToForgotPassword = { navController.navigate(JobFinderScreen.ForgotPassword.route) },
                navigateToHome = { navController.navigate(JobFinderScreen.Home.route) }
            )
        }

        composable(route = JobFinderScreen.SignUp.route) {
            SignUp(
                navigateToLogin = { navController.navigate(JobFinderScreen.Login.route) },
                navigateToForgotPassword = { navController.navigate(JobFinderScreen.ForgotPassword.route) },
            )
        }

        composable(JobFinderScreen.ForgotPassword.route) {
            ForgotPassword(
                navigateToLogin = { navController.navigate(JobFinderScreen.Login.route) },
                navigateToVerifyCation = { navController.navigate(JobFinderScreen.Verify.route) }
            )
        }

        composable(JobFinderScreen.Success.route) {
            Success(
                navigateToLogin = { navController.navigate(JobFinderScreen.Login.route) },
                navigateToHome = { navController.navigate(JobFinderScreen.Home.route) }
            )
        }

        composable(JobFinderScreen.ChangePassword.route) {
            ChangePassword(
                onBack = { navController.navigateUp() },
                navigateToLogin = { navController.navigate(JobFinderScreen.Login.route) }
            )
        }

        composable(JobFinderScreen.Verify.route) {
            Verify(
                navigateToLogin = { navController.navigate(JobFinderScreen.Login.route) }
            )
        }

        composable(JobFinderScreen.Home.route) {
            Home(
                navigateToHome = { navController.navigate(JobFinderScreen.Home.route) },
                navigateToSaveJob = { navController.navigate(JobFinderScreen.Home.route) },
                navigateToProfile = { navController.navigate(JobFinderScreen.EditProfile.route) },
                navigateToPostJob = { navController.navigate(JobFinderScreen.PostJob.route) },
                navigateToSearch = { navController.navigate(JobFinderScreen.SearchJob.route) },
                navigateToJobDesc = { jobId -> navController.navigate("${JobFinderScreen.JobDescription.route}/${jobId}") },
                currentScreen = currentScreen
            )
        }

        composable(JobFinderScreen.SearchJob.route) {
            SearchJob(
                navigateToHome = { navController.navigate(JobFinderScreen.Home.route) },
                navigateToSaveJob = { navController.navigate(JobFinderScreen.Home.route) },
                navigateToProfile = { navController.navigate(JobFinderScreen.EditProfile.route) },
                navigateToPostJob = { navController.navigate(JobFinderScreen.PostJob.route) },
                navigateToSearch = { navController.navigate(JobFinderScreen.SearchJob.route) },
                currentScreen = currentScreen,
                navigateToUploadCv = { navController.navigate(JobFinderScreen.UploadCv.route) },
                navigateBack = { navController.navigateUp() }
            )
        }

        composable("${JobFinderScreen.JobDescription.route}/{jobId}") { backStackEntry ->
            val jobId = backStackEntry.arguments?.getString("jobId")
            JobDescription(
                navigateBack = { navController.navigateUp() },
                navigateToUploadCv = { navController.navigate(JobFinderScreen.UploadCv.route) },
                jobId = jobId ?: ""
            )
        }

        composable(JobFinderScreen.UploadCv.route) {
            UploadCv(
                navigateBack = { navController.navigateUp() },
                navigateToSearchJob = { navController.navigate(JobFinderScreen.SearchJob.route) },
                navigateToHome = { navController.navigate(JobFinderScreen.Home.route) }
            )
        }

        composable(JobFinderScreen.Specialization.route) {
            Specialization(
                navigateBack = { navController.navigateUp() },
                navigateToFilter = { navController.navigate(JobFinderScreen.Filter.route) }
            )
        }

        composable(JobFinderScreen.Filter.route) {
            Filter(
                navigateBack = { navController.navigateUp() }
            )
        }

        composable(JobFinderScreen.PostJob.route) {
            AddAJob(
                navigateToHome = { navController.navigate(JobFinderScreen.Home.route) },
                navigateToJob = { jobId -> navController.navigate(JobFinderScreen.JobDescription.route + "/${jobId}") }
            )
        }

        composable(JobFinderScreen.SaveJob.route) {
            SaveJob(
                currentScreen = currentScreen,
                navigateToHome = { navController.navigate(JobFinderScreen.Home.route) },
                navigateToSaveJob = { },
                navigateToProfile = { navController.navigate(JobFinderScreen.ViewProfile.route) },
                navigateToPostJob = { navController.navigate(JobFinderScreen.PostJob.route) },
                navigateToSearch = { navController.navigate(JobFinderScreen.SearchJob.route) }
            )
        }

        composable(JobFinderScreen.MyApplications.route) {
            ListMyApplication(
                navigateBack = { navController.navigateUp() },
            )
        }

        composable(JobFinderScreen.MyApplication.route) {
            MyApplication(
                navigateBack = { navController.navigateUp() },
                navigateToSearchJob = { navController.navigate(JobFinderScreen.SearchJob.route) }
            )
        }

        composable(JobFinderScreen.ViewProfile.route) {
            ViewProfile(
                navigateToHome = { navController.navigate(JobFinderScreen.Home.route) },
                navigateToSaveJob = { navController.navigate(JobFinderScreen.Home.route) },
                navigateToProfile = { navController.navigate(JobFinderScreen.EditProfile.route) },
                navigateToPostJob = { navController.navigate(JobFinderScreen.PostJob.route) },
                navigateToSearch = { navController.navigate(JobFinderScreen.SearchJob.route) },
                currentScreen = currentScreen,
                navigateToAboutMe = { navController.navigate(JobFinderScreen.AboutMe.route) },
                navigateToWorkExperience = { navController.navigate(JobFinderScreen.AddWorkExperience.route) },
                navigateToEducation = { navController.navigate(JobFinderScreen.AddEducation.route) },
                navigateToSkill = { navController.navigate(JobFinderScreen.AddSkill.route) },
                navigateToLanguage = { navController.navigate(JobFinderScreen.Language.route) },
                navigateToEditProfile = { navController.navigate(JobFinderScreen.EditProfile.route) },
            )
        }

        composable(JobFinderScreen.AboutMe.route) {
            AboutMe(
                navigateBack = { navController.navigateUp() },
                navigateToViewProfile = { navController.navigate(JobFinderScreen.ViewProfile.route) }
            )
        }

        composable(JobFinderScreen.AddEducation.route) {
            AddEducation(
                navigateBack = { navController.navigateUp() },
                navigateToViewProfile = { navController.navigate(JobFinderScreen.ViewProfile.route) }
            )
        }

        composable(JobFinderScreen.AddWorkExperience.route) {
            AddWorkExperience(
                navigateBack = { navController.navigateUp() },
                navigateToViewProfile = { navController.navigate(JobFinderScreen.ViewProfile.route) }
            )
        }

        composable(JobFinderScreen.AddSkill.route) {
            AddSkill(
                onBackClick = { navController.navigateUp() },
                navigateToViewProfile = { navController.navigate(JobFinderScreen.ViewProfile.route) }
            )
        }

        composable(JobFinderScreen.Language.route) {
            Language(
                navigateToAddLanguageScreen = { navController.navigate(JobFinderScreen.AddLanguage.route) },
                navigateBack = { navController.navigateUp() },
                naviagetToViewProfile = { navController.navigate(JobFinderScreen.ViewProfile.route) }
            )
        }

        composable(JobFinderScreen.AddLanguage.route) {
            AddLanguage(
                onBackClick = { navController.navigateUp() },
                navigateToLanguage = { navController.navigate(JobFinderScreen.Language.route) }
            )
        }

        composable(JobFinderScreen.AddResume.route) {
            AddResume(
                onBackClick = { navController.navigateUp() },
                navigateToViewProfile = { navController.navigate(JobFinderScreen.ViewProfile.route) }
            )
        }

        composable(JobFinderScreen.EditProfile.route) {
            EditProfile(
                navigateToSetting = { },
                navigateToViewProfile = { navController.navigate(JobFinderScreen.ViewProfile.route) },
            )
        }
    }
}