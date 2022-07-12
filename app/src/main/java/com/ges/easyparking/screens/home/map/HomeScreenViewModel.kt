package com.ges.easyparking.screens.home.map

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ges.easyparking.repositories.CarParkRepository
import com.ges.easyparking.repositories.Result
import com.ges.easyparking.screens.listaParks.FirstScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel
@Inject
constructor(
    private val carParkRepository: CarParkRepository,
): ViewModel(){
    private val _state: MutableState<HomeScreenState> = mutableStateOf(HomeScreenState())
    val state: State<HomeScreenState> = _state

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> = _isRefreshing

    init{
        getParkCarList()
    }

    fun getParkCarList(){
        carParkRepository.getParkCarList().onEach { result->
            when(result){
                is Result.Error -> {
                    _state.value = HomeScreenState(error = result.message ?: "Error inesperado")
                }
                is Result.Loading -> {
                    _state.value = HomeScreenState(isLoading = true)
                }
                is Result.Success -> {
                    _state.value = HomeScreenState(carparks = result.data ?: emptyList())
                }
            }
        }.launchIn(viewModelScope)
    }

}