package com.ges.easyparking.clases

data class User(
    val id: String,
    val name: String,
    val email: String,
    val password: String,
    val status: String
){
    constructor(): this("", "", "", "", "")
}
