package com.tpanh.jobfinder.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.tpanh.jobfinder.screens.AddEducation
import com.tpanh.jobfinder.screens.AddLanguage
import com.tpanh.jobfinder.screens.AddResume
import com.tpanh.jobfinder.screens.AddSkill
import com.tpanh.jobfinder.screens.EditProfile
import com.tpanh.jobfinder.screens.LanguageScreen
import com.tpanh.jobfinder.screens.Login
import com.tpanh.jobfinder.screens.OnBoarding
import com.tpanh.jobfinder.screens.SignUp

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
                navigateToLoginScreen = {
                    navController.navigate(JobFinderScreen.Login.name)
                }
            )
        }

        composable(route = JobFinderScreen.Login.name) {
            Login(
                navigateToSignUp = {
                    navController.navigate(JobFinderScreen.SignUp.name)
                },
                navigateToForgotPassword = {

                }
            )
        }

        composable(route = JobFinderScreen.SignUp.name) {
            SignUp(
                navigateToLogin = {
                    navController.navigate(JobFinderScreen.Login.name)
                }
            )
        }

        composable(JobFinderScreen.AddEducation.name) {
            AddEducation()
        }

        composable(JobFinderScreen.AddSkill.name) {
            AddSkill()
        }

        composable(JobFinderScreen.AddResume.name) {
            AddResume()
        }

        composable(JobFinderScreen.EditProfile.name) {
            EditProfile(
                navigateToSetting = { }
            )
        }

        composable(JobFinderScreen.Language.name) {
            LanguageScreen(
                navigateToAddLanguageScreen = {
                    navController.navigate(JobFinderScreen.AddLanguage.name)
                }
            )
        }

        composable(JobFinderScreen.AddLanguage.name) {
            AddLanguage(
                onBackClick = { navController.navigateUp() }
            )
        }
    }
}