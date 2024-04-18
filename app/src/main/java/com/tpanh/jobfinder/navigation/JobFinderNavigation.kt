package com.tpanh.jobfinder.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.tpanh.jobfinder.sreens.AddEducation
import com.tpanh.jobfinder.sreens.AddLanguage
import com.tpanh.jobfinder.sreens.AddResume
import com.tpanh.jobfinder.sreens.AddSkill
import com.tpanh.jobfinder.sreens.EditProfile
import com.tpanh.jobfinder.sreens.HomeScreen
import com.tpanh.jobfinder.sreens.LanguageScreen
import com.tpanh.jobfinder.sreens.OnBoardingScreen

@Composable
fun JobFinderNavigation(
    navController: NavHostController = rememberNavController()
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = JobFinderScreen.valueOf(
        backStackEntry?.destination?.route ?: JobFinderScreen.Home.name
    )

    NavHost(navController = navController, startDestination = JobFinderScreen.Language.name) {
        composable(JobFinderScreen.Home.name) {
            HomeScreen(
                onNavigateToOnBoarding = {
                    navController.navigate(JobFinderScreen.OnBoarding.name)
                }
            )
        }

        composable(JobFinderScreen.OnBoarding.name) {
            OnBoardingScreen()
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