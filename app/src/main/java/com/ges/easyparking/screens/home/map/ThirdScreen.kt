package com.ges.easyparking.screens.home.map

import android.os.Bundle
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleObserver
import androidx.navigation.NavController
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import java.lang.IllegalStateException

var stringA: String = ""
var stringB: String = ""
var stringC: String = ""
var stringD: String = ""
var stringE: String = ""
var stringF: String = ""

@Composable
fun ThirdScreen(
    navController: NavController,
    text: String?,
    string: String?,
    string1: String?,
    string2: String?,
    string3: String?,
    string4: String?
) {
    Scaffold(
        topBar = {
            TopAppBar(modifier = Modifier.height(30.dp)) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Arrow back",
                    modifier = Modifier.clickable {
                        navController.popBackStack()
                    }
                )
                Text(text = "Volver")
            }
        }
    ) {
        ThirdBodyContent(navController)

        if (text != null) {
            stringA = text
        }
        if (string != null) {
            stringB = string
        }
        if (string1 != null) {
            stringC = string1
        }
        if (string2 != null) {
            stringD = string2
        }
        if (string3 != null) {
            stringE = string3
        }
        if (string4 != null) {
            stringF = string4
        }

    }
}


@Composable
fun ThirdBodyContent(
    navController: NavController
) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        MyMap(){}
    }

}

@Composable
fun MyMap(onReady:(GoogleMap) -> Unit){
    val context = LocalContext.current
    val mapView = remember{ MapView(context) }
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    lifecycle.addObserver(RememberMapLifecyCle(map = mapView))
    AndroidView(
        factory = {
            mapView.apply{
                mapView.getMapAsync{googleMap ->
                    val zoomLevel = 15f
                    val lat: Double = java.lang.Double.valueOf(stringE)
                    val lon: Double = java.lang.Double.valueOf(stringF)
                    val latlon = LatLng(lat, lon)
                    Toast.makeText(
                        context,
                        "$stringA",
                        Toast.LENGTH_SHORT
                    ).show()
                    googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latlon, zoomLevel))
                    googleMap.addMarker(MarkerOptions()
                        .position(latlon)
                        .title(stringA.toString())
                        .snippet("$stringB \nHora apertura: $stringC \nHora cierre: $stringD")
                    )
                    googleMap.setInfoWindowAdapter(CustomInfoWindowForGoogleMap(context))
                }
            }
        }
    )
}

@Composable
fun RememberMapLifecyCle(map: MapView): LifecycleObserver{
    return remember{
        LifecycleEventObserver{source, event->
            when(event){
                Lifecycle.Event.ON_CREATE -> map.onCreate(Bundle())
                Lifecycle.Event.ON_START -> map.onStart()
                Lifecycle.Event.ON_RESUME -> map.onResume()
                Lifecycle.Event.ON_PAUSE -> map.onPause()
                Lifecycle.Event.ON_STOP -> map.onStop()
                Lifecycle.Event.ON_DESTROY -> map.onDestroy()
                Lifecycle.Event.ON_ANY -> throw IllegalStateException()
            }
        }
    }
}












