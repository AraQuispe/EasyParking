package com.ges.easyparking.screens.listaParks

import com.ges.easyparking.clases.CarPark

data class FirstScreenState (
    val isLoading: Boolean = false,
    val carparks: List<CarPark> = emptyList(),
    val error: String = ""
)