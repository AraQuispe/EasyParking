package com.ges.easyparking.screens.home.map

import com.ges.easyparking.clases.CarPark

data class HomeScreenState(
    val isLoading: Boolean = false,
    val carparks: List<CarPark> = emptyList(),
    val error: String = ""
)