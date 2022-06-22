
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
    val marker1 = LatLng(-16.4154919, -71.5615347)
    val marker2 = LatLng(-16.417815, -71.5605144)
    val marker3 = LatLng(-16.4145078, -71.5597471)

    val cameraPosition = rememberCameraPositionState{
        position = CameraPosition.fromLatLngZoom(marker, 10f)
    }
    Box(modifier = Modifier.fillMaxHeight(0.91f), contentAlignment = Alignment.Center){
        GoogleMap(cameraPositionState = cameraPosition){
            Marker(
                position=marker,
                title = "Park Center",
                snippet = "Pierola 201"
            )
            Marker(
                position=marker1,
                title = "Terrapuerto Parqueo Exterior",
                snippet = "Arequipa 0401"
            )
            Marker(
                position=marker2,
                title = "S.A PARKING",
                snippet = "C. San Juan de Dios 611"
            )
            Marker(
                position=marker3,
                title = "Estacionamientos Parque Lambramani",
                snippet = " Estación Lambramani, Arequipa 04002"
            )
        }
    }

}

