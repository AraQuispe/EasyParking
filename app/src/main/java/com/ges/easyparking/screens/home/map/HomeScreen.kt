
package com.ges.easyparking.screens.home.map

import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.app.ActivityCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.ges.easyparking.clases.CarPark
import com.ges.easyparking.components.BottomBarFilter
import com.ges.easyparking.screens.listaParks.FirstScreenState
import com.ges.easyparking.screens.listaParks.MyComponent
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.rememberCameraPositionState
import java.lang.IllegalStateException

@Composable
fun HomeScreen(
    navController: NavController,
    state: HomeScreenState,
    isRefreshing: Boolean,
    refreshData: ()->Unit
){


    Scaffold {
        BottomBarFilter()
        MyGoogleMaps(state)
    }
}

@Composable
fun MyGoogleMaps(state: HomeScreenState) {
    val marker = LatLng(-16.3991588, -71.5314951)
    val cameraPosition = rememberCameraPositionState{
        position = CameraPosition.fromLatLngZoom(marker, 20f)
    }

    Box(modifier = Modifier.fillMaxHeight(0.91f), contentAlignment = Alignment.Center){
        GoogleMap(cameraPositionState = cameraPosition){
            state.carparks.forEach {
                    item ->
                val name = item.name.toString();
                val address = item.address.toString();
                if (item.lat != null && item.lon != null) {
                    val lat = java.lang.Double.valueOf(item.lat)
                    val long = java.lang.Double.valueOf(item.lon)
                    var pos = LatLng(lat, long);
                    Marker(position=pos, title = name, snippet = address)
                }
            }
        }
    }
}