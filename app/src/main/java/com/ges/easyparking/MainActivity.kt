package com.ges.easyparking

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.ges.easyparking.components.Drawer
import com.ges.easyparking.components.TopBar
import com.ges.easyparking.navigation.AppNavigation
import com.ges.easyparking.navigation.AppScreens
import com.ges.easyparking.ui.theme.EasyParkingTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EasyParkingTheme {
                Surface(
                    color = MaterialTheme.colors.background
                ) {
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
        AppScreens.FirstScreen,
        AppScreens.SecondScreen
    )
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { TopBar(scope, scaffoldState ) },
        drawerContent = { Drawer(scope, scaffoldState, navController, items = navigationItems) },
        drawerGesturesEnabled = true
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