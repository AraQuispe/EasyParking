package com.ges.easyparking.screens.listaParks

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ges.easyparking.R
import com.ges.easyparking.clases.CarPark
import com.ges.easyparking.navigation.AppScreens

@Composable
fun FirstScreen(
    navController: NavController,
    text: String?,
    id: String?,
    state: FirstScreenState,
    isRefreshing: Boolean,
    refreshData: ()->Unit
) {
    Scaffold( ) {
        SecondBodyContent(navController, text, id, state, isRefreshing, refreshData)
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SecondBodyContent(
    navController: NavController,
    text: String?,
    id: String?,
    state: FirstScreenState,
    isRefreshing: Boolean,
    refreshData: () -> Unit
){
    Box(modifier = Modifier
        .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        text?.let{ Text("Parqueos Cercanos",
            fontSize = 24.sp,
            color = MaterialTheme.colors.primary,
            style = MaterialTheme.typography.subtitle1,
            textAlign = TextAlign.Center,
            maxLines = 2,
        )}
    }
    LazyColumn(
        contentPadding = PaddingValues(2.dp)
    ) {
        items(
            items = state.carparks
        ){ item->
            if (item != null) {
                if(id.equals(item.regionId))
                    MyComponent(item, navController)
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MyComponent(_message: CarPark, navController: NavController){
    Column (modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp)){
    }
    Row(modifier = Modifier
        .padding(10.dp))
    {
        MyTexts(_message, navController = navController)
        BottonMapa(_message = _message, navController = navController)
    }
}

@Composable
fun BottonMapa(_message: CarPark, navController: NavController){
    Box(modifier = Modifier
        .height(50.dp)
        .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        IconButton(
            onClick = {
                navController.navigate(route = AppScreens.ThirdScreen.route
                        + "/${_message.name}"
                        + "/${_message.address}"
                        + "/${_message.dateO}"
                        + "/${_message.dateC}"
                        + "/${_message.lat}"
                        + "/${_message.lon}")
            }
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_arrow_direction_gps_location),
                contentDescription = "Ir mapa"
            )
        }
    }
}

@Composable
fun MyTexts(_message: CarPark, navController: NavController){
    var expanded by remember{ mutableStateOf(false) }
    Column(
        modifier = Modifier
            .padding(start = 7.dp)
            .width(300.dp)
            .clickable {
                navController.navigate(route = AppScreens.SecondScreen.route
                        + "/${_message.name}"
                        + "/${_message.address}"
                        + "/${_message.dateO}"
                        + "/${_message.dateC}"
                        + "/${_message.lat}"
                        + "/${_message.lon}")
            }) {
        _message.name?.let {
            MyText(
                it,
                MaterialTheme.colors.primary,
                MaterialTheme.typography.subtitle1
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        _message.address?.let {
            MyText(
                it,
                MaterialTheme.colors.onBackground,
                MaterialTheme.typography.subtitle2,
                if (expanded) Int.MAX_VALUE else 1
            )
        }
    }
}

@Composable
fun MyText(_text: String, _color: Color, _style: TextStyle, _lines: Int = Int.MAX_VALUE){
    Text(_text, color = _color, style = _style, maxLines = _lines)
}
