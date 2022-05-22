package com.ges.easyparking.screens

import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.navigation.NavController
import com.ges.easyparking.components.BottomBarFilter

@Composable
fun HomeScreen(navController: NavController){
    Scaffold {
        BottomBarFilter()

    }
}

