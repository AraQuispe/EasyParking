package com.ges.easyparking.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NamedNavArgument

sealed class AppScreens(
    val route:String,
    val title: String,
    val icon: ImageVector,
    val arguments: List<NamedNavArgument>
    ){
    object LoginScreen: AppScreens("login_screen", "Inicio Sesión", Icons.Filled.Home, emptyList())
    object HomeScreen: AppScreens("home_screen", "Ver mapa", Icons.Filled.Home, emptyList())
    object FirstScreen: AppScreens("first_screen", "Ver lista", Icons.Filled.List, emptyList())
    object SecondScreen: AppScreens("second_screen","Detalle", Icons.Filled.Build, emptyList())
    object ThirdScreen: AppScreens("third_screen","Parqueo en el mapa", Icons.Filled.List, emptyList())

}