package com.ges.easyparking.components

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.ges.easyparking.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun TopBar(
    scope: CoroutineScope,
    scaffoldState: ScaffoldState
) {
    TopAppBar(
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
                // TODO: Display SnackBar
            }) {
                Icon(imageVector = Icons.Default.Person, contentDescription = "Person Icon")
            }

        }
    )
}