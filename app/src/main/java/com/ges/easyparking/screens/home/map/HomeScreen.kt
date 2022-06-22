
package com.ges.easyparking.screens.home.map

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.ges.easyparking.components.BottomBarFilter
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun HomeScreen(navController: NavController){
    Scaffold {
        BottomBarFilter()
        MyGoogleMaps()
    }
}

@Composable
fun MyGoogleMaps(){
    val marker = LatLng(-16.3991588, -71.5314951)
    val cameraPosition = rememberCameraPositionState{
        position = CameraPosition.fromLatLngZoom(marker, 10f)
    }
    Box(modifier = Modifier.fillMaxHeight(0.91f), contentAlignment = Alignment.Center){
        GoogleMap(cameraPositionState = cameraPosition){
            Marker(
                position=marker,
                title = "Bagua",
                snippet = "Mark"
            )
        }
    }

}

