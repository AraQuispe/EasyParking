package com.ges.easyparking.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.ges.easyparking.screens.FirstScreen
import com.ges.easyparking.screens.HomeScreen
import com.ges.easyparking.screens.SecondScreen

@Composable
fun AppNavigation(navController: NavHostController){
    NavHost(navController = navController, startDestination = AppScreens.HomeScreen.route){
        composable(route = AppScreens.HomeScreen.route){
            HomeScreen(navController)
        }
        composable(route = AppScreens.FirstScreen.route){
            FirstScreen(navController)
        }
        composable(route = AppScreens.SecondScreen.route){
            SecondScreen(navController)
        }
    }
}