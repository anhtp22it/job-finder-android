package com.tpanh.jobfinder.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tpanh.jobfinder.sreens.AddEducation
import com.tpanh.jobfinder.sreens.HomeScreen
import com.tpanh.jobfinder.sreens.OnBoardingScreen

@Composable
fun JobFinderNavigation(
    navController: NavHostController = rememberNavController()
) {
    NavHost(navController = navController, startDestination = JobFinderScreen.Home.name) {
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
    }
}