package com.ges.easyparking.screens.registration

import com.ges.easyparking.clases.User

data class RegistrationScreenState(
    val isLoading: Boolean = false,
    val users: List<User> = emptyList(),
    val error: String = ""
)
