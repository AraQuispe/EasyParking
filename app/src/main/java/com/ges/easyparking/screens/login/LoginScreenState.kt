package com.ges.easyparking.screens.login

import com.ges.easyparking.clases.User

data class LoginScreenState(
    val isLoading: Boolean = false,
    val users: List<User> = emptyList(),
    val error: String = ""
)
