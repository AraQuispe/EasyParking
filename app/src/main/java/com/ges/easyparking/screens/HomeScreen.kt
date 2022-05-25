package com.ges.easyparking.screens

import android.icu.text.Transliterator
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker

@Composable
fun HomeScreen(navController: NavController){
    Scaffold {
        MyMap()
        //BottomBarFilter()
    }
}

@Composable
fun MyMap() {
    val marker = LatLng(-16.411989090301443, -71.51048341048909)
    GoogleMap(modifier = Modifier.fillMaxSize(), properties = ) {
        Marker(position = marker)

    }

}
