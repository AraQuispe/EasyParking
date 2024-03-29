package com.ges.easyparking

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.ges.easyparking.components.Drawer
import com.ges.easyparking.components.TopBar
import com.ges.easyparking.navigation.AppNavigation
import com.ges.easyparking.navigation.AppScreens
import com.ges.easyparking.ui.theme.EasyParkingTheme
import dagger.hilt.android.AndroidEntryPoint
import com.ges.easyparking.dataStore.UserManager


lateinit var userManager: UserManager

@ExperimentalMaterialApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EasyParkingTheme {
                Surface(
                    color = MaterialTheme.colors.background
                ) {
                    val context = LocalContext.current
                    userManager = UserManager(context)
                    MainHomeScreen()
                }
            }
        }
    }
}

@Composable
fun MainHomeScreen() {
    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState(
        drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    )
    val scope = rememberCoroutineScope()

    val navigationItems = listOf(
        AppScreens.HomeScreen,
        AppScreens.FirstScreen
    )
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { TopBar(scope, scaffoldState, navController ) },
        drawerContent = { Drawer(scope, scaffoldState, navController, items = navigationItems) },
        drawerGesturesEnabled = false
    ) {
        AppNavigation(navController)
    }
}


@Preview(showSystemUi = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewComponent() {
    EasyParkingTheme {
        MainHomeScreen()
    }
}