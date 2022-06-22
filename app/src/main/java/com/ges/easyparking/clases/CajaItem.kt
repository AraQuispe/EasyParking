package com.ges.easyparking.clases


data class MyItem(
    val id: String ?=null,
    val name: String ?=null,
    val thumb: String ?=null
){
    constructor(): this("","","")

}