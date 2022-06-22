package com.ges.easyparking.clases

import com.google.type.LatLng

data class CarPark (
    val id: String ?=null,
    val regionId: String ?=null,
    val name: String ?=null,
    val address: String ?=null,
    val dateO: String ?=null,
    val dateC: String ?=null,
    val lat: String ?=null,
    val lon: String ?=null
)