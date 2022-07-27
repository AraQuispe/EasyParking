
package com.ges.easyparking.screens.home.map

import android.Manifest
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ges.easyparking.clases.CarPark
import com.ges.easyparking.components.BottomBarFilter
import com.ges.easyparking.navigation.AppScreens
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    navController: NavController,
    state: HomeScreenState,
    isRefreshing: Boolean,
    refreshData: ()->Unit
){


    Scaffold {
        BottomBarFilter()
        MyGoogleMaps(state, navController)
    }
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun MyGoogleMaps(state: HomeScreenState, navController: NavController) {
    val marker = LatLng(-16.3991588, -71.5314951)
    val cameraPosition = rememberCameraPositionState{
        position = CameraPosition.fromLatLngZoom(marker, 20f)
    }
    val uiSettings = remember {
        MapUiSettings(myLocationButtonEnabled = true)
    }
    val properties by remember {
        mutableStateOf(MapProperties(isMyLocationEnabled = true))
    }
    val permissionState = rememberPermissionState(
        permission = Manifest.permission.ACCESS_FINE_LOCATION,
    )

    Button( modifier = Modifier.padding(20.dp, 0.dp, 0.dp ,0.dp), onClick = {permissionState.launchPermissionRequest() }) {
    }
    if (permissionState.hasPermission) {

        Box(modifier = Modifier.fillMaxHeight(0.91f), contentAlignment = Alignment.Center) {
            GoogleMap(
                cameraPositionState = cameraPosition, properties = properties, uiSettings = uiSettings
            ) {
                state.carparks.forEach { item ->
                    val name = item.name.toString();
                    val address = item.address.toString();
                    val dateO = item.dateO.toString();
                    val dateC = item.dateC.toString();

                    if (item.lat != null && item.lon != null) {
                        val lat = java.lang.Double.valueOf(item.lat)
                        val long = java.lang.Double.valueOf(item.lon)
                        var pos = LatLng(lat, long);
                        val coroutineScope = rememberCoroutineScope()
                        Marker(position = pos, title = name, snippet = address, onInfoWindowClick = {
                           coroutineScope.launch { navController.navigate(route = AppScreens.SecondScreen.route
                                   + "/${name}"
                                   + "/${address}"
                                   + "/${dateO}"
                                   + "/${dateC}"
                                   + "/${lat}"
                                   + "/${long}")
                           } }
                            )
                    }
                }
            }
        }
    }
}