package com.ges.easyparking.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.ges.easyparking.screens.listaParks.FirstScreen
import com.ges.easyparking.screens.home.map.HomeScreen
import com.ges.easyparking.screens.detallePark.SecondScreen
import com.ges.easyparking.screens.home.map.ThirdScreen
import com.ges.easyparking.screens.listaParks.FirstScreenViewModel

@Composable
fun AppNavigation(navController: NavHostController){
    NavHost(navController = navController, startDestination = AppScreens.HomeScreen.route){
        composable(route = AppScreens.HomeScreen.route){
            HomeScreen(navController)
        }
        composable(
            route = AppScreens.FirstScreen.route
            ){
            val viewModel: FirstScreenViewModel = hiltViewModel()
            val state = viewModel.state.value
            val isRefreshing = viewModel.isRefreshing.collectAsState()
            val textA = "Arequipa"
            val id = "r04" //Moficación

            FirstScreen(navController, textA, id, state, isRefreshing = isRefreshing.value, refreshData = viewModel::getParkCarList)
        }
        composable(route = AppScreens.SecondScreen.route){
            SecondScreen(navController)
        }
        composable(route = AppScreens.ThirdScreen.route + "/{name}" + "/{address}" + "/{dateO}" + "/{dateC}" + "/{lat}" + "/{lon}",
            arguments = listOf(navArgument(name = "name"){
                type = NavType.StringType
            }
            )){
            ThirdScreen(navController,
                it.arguments?.getString("name"),
                it.arguments?.getString("address"),
                it.arguments?.getString("dateO"),
                it.arguments?.getString("dateC"),
                it.arguments?.getString("lat"),
                it.arguments?.getString("lon"))
        }
    }
}