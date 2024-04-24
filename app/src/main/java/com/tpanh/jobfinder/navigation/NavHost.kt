package com.tpanh.jobfinder.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.tpanh.jobfinder.screens.SignInDestination
import com.tpanh.jobfinder.screens.SignInScreen
import com.tpanh.jobfinder.screens.SignUpDestination
import com.tpanh.jobfinder.screens.SignUpScreen

@Composable
fun ShopNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = SignInDestination.route,
        modifier = modifier
    ) {
        composable(route = SignInDestination.route) {
            SignInScreen(
                navigateToSignUp = {
                    navController.navigate(SignUpDestination.route)
                },
                navigateToHome = {
//                    navController.navigate(HomeScreenDestination.route)
                }
            )
        }

        composable(route = SignUpDestination.route) {
            SignUpScreen(navigateToSignIn = {
                navController.navigate(SignInDestination.route)
            })
        }
//        composable(route = HomeScreenDestination.route) {
//            HomeScreen()
//        }
    }
}