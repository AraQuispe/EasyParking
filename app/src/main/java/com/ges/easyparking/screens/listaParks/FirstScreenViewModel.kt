package com.ges.easyparking.screens.listaParks

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ges.easyparking.repositories.CarParkRepository
import com.ges.easyparking.repositories.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class FirstScreenViewModel
@Inject
constructor(
    private val carParkRepository: CarParkRepository,
): ViewModel(){
    private val _state: MutableState<FirstScreenState> = mutableStateOf(FirstScreenState())
    val state: State<FirstScreenState> = _state

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> = _isRefreshing

    init{
        getParkCarList()
    }

    fun getParkCarList(){
        carParkRepository.getParkCarList().onEach { result->
            when(result){
                is Result.Error -> {
                    _state.value = FirstScreenState(error = result.message ?: "Error inesperado")
                }
                is Result.Loading -> {
                    _state.value = FirstScreenState(isLoading = true)
                }
                is Result.Success -> {
                    _state.value = FirstScreenState(carparks = result.data ?: emptyList())
                }
            }
        }.launchIn(viewModelScope)
    }

}