package com.ges.easyparking.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.ges.easyparking.screens.login.LoginScreen
import com.ges.easyparking.screens.listaParks.FirstScreen
import com.ges.easyparking.screens.home.map.HomeScreen
import com.ges.easyparking.screens.detallePark.SecondScreen
import com.ges.easyparking.screens.home.map.HomeScreenViewModel
import com.ges.easyparking.screens.home.map.ThirdScreen
import com.ges.easyparking.screens.listaParks.FirstScreenViewModel
import com.ges.easyparking.screens.registration.RegistrationScreen
import com.ges.easyparking.screens.login.LoginScreenViewModel
import com.ges.easyparking.screens.registration.RegistrationScreenViewModel

@Composable
fun AppNavigation(navController: NavHostController){
    NavHost(navController = navController, startDestination = AppScreens.HomeScreen.route){
        composable(
            route = AppScreens.LoginScreen.route
            ){
            val viewModel: LoginScreenViewModel = hiltViewModel()
            val state = viewModel.state.value
            val isRefreshing = viewModel.isRefreshing.collectAsState()
            LoginScreen(navController, state, isRefreshing = isRefreshing.value, refreshData = viewModel::getUserList)
        }
        composable(
            route = AppScreens.RegistrationScreen.route
        ){
            val viewModel: RegistrationScreenViewModel = hiltViewModel()
            val state = viewModel.state.value
            val isRefreshing = viewModel.isRefreshing.collectAsState()
            RegistrationScreen(navController, state, isRefreshing = isRefreshing.value, refreshData = viewModel::getUserList, register = viewModel::addNewUser)
        }
        composable(
            route = AppScreens.HomeScreen.route
            ){
            val viewModel: HomeScreenViewModel = hiltViewModel()
            val state = viewModel.state.value
            val isRefreshing = viewModel.isRefreshing.collectAsState()
            HomeScreen(navController, state, isRefreshing = isRefreshing.value, refreshData = viewModel::getParkCarList)
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
        composable(route = AppScreens.SecondScreen.route + "/{name}" + "/{address}" + "/{dateO}" + "/{dateC}" + "/{lat}" + "/{lon}",
            arguments = listOf(navArgument(name = "name"){
                type = NavType.StringType
            }
            )){
            SecondScreen(navController,
                it.arguments?.getString("name"),
                it.arguments?.getString("address"),
                it.arguments?.getString("dateO"),
                it.arguments?.getString("dateC"),
                it.arguments?.getString("lat"),
                it.arguments?.getString("lon") )
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