package com.ges.easyparking.screens.registration

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ges.easyparking.clases.User
import com.ges.easyparking.navigation.AppScreens
import com.ges.easyparking.repositories.Result
import com.ges.easyparking.repositories.UserRepository
import com.ges.easyparking.screens.login.LoginScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.util.*
import javax.inject.Inject

@HiltViewModel
class RegistrationScreenViewModel
@Inject
constructor(
    private val userRepository: UserRepository,
): ViewModel(){
    private val _state: MutableState<RegistrationScreenState> = mutableStateOf(RegistrationScreenState())
    val state: State<RegistrationScreenState> = _state

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> = _isRefreshing

    init{
        getUserList()
    }

    fun getUserList(){
        userRepository.getUserList().onEach { result->
            when(result){
                is Result.Error -> {
                    _state.value = RegistrationScreenState(error = result.message ?: "Error inesperado")
                }
                is Result.Loading -> {
                    _state.value = RegistrationScreenState(isLoading = true)
                }
                is Result.Success -> {
                    _state.value = RegistrationScreenState(users = result.data ?: emptyList())
                }
            }
        }.launchIn(viewModelScope)
    }

    fun addNewUser(name: String, email: String, password: String) {
        val user = User(
            id = UUID.randomUUID().toString(),
            name = name,
            email = email,
            password = password,
            status = "a"
        )
        userRepository.addNewUser(user)
    }

}