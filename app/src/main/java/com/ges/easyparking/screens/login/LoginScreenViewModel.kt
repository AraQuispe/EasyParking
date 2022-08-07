package com.ges.easyparking.screens.login

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ges.easyparking.repositories.Result
import com.ges.easyparking.repositories.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel
@Inject
constructor(
    private val userRepository: UserRepository,
): ViewModel(){
    private val _state: MutableState<LoginScreenState> = mutableStateOf(LoginScreenState())
    val state: State<LoginScreenState> = _state

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> = _isRefreshing

    init{
        getUserList()
    }

    fun getUserList(){
        userRepository.getUserList().onEach { result->
            when(result){
                is Result.Error -> {
                    _state.value = LoginScreenState(error = result.message ?: "Error inesperado")
                }
                is Result.Loading -> {
                    _state.value = LoginScreenState(isLoading = true)
                }
                is Result.Success -> {
                    _state.value = LoginScreenState(users = result.data ?: emptyList())
                }
            }
        }.launchIn(viewModelScope)
    }

}