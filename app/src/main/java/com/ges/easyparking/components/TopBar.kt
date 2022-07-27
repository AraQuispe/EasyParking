package com.ges.easyparking.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.ges.easyparking.R
import com.ges.easyparking.navigation.AppScreens
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import com.ges.easyparking.userManager

@Composable
fun TopBar(
    scope: CoroutineScope,
    scaffoldState: ScaffoldState,
    navController: NavController
) {
    val stateActual = userManager.displayTopFlow.collectAsState(initial = 0).value
    var stateDisplay: Boolean = stateActual == true

    AnimatedVisibility(
        visible = stateDisplay,
        content = { TopAppBar(
            title = { Text(LocalContext.current.getString(R.string.app_name)) },
            navigationIcon = {
                IconButton(onClick = {
                    scope.launch {
                        scaffoldState.drawerState.open()
                    }
                }) {
                    Icon(imageVector = Icons.Filled.Menu, contentDescription = "Menu Icon")
                }
            },
            actions = {
                IconButton(onClick = {
                    scope.launch {
                        userManager.storeDisplayTopBar(false)
                    }
                    navController.navigate(AppScreens.LoginScreen.route)
                }) {
                    Icon(imageVector = Icons.Default.Person, contentDescription = "Person Icon")
                }
            },

            )})

}