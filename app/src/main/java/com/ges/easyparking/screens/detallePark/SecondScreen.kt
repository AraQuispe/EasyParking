package com.ges.easyparking.screens.detallePark

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ges.easyparking.R
import com.ges.easyparking.navigation.AppScreens
import com.ges.easyparking.screens.home.map.*

var nameP: String = ""
var addressP: String = ""
var dateOP: String = ""
var dateCP: String = ""
var latP: String = ""
var lonP: String = ""

@Composable
fun SecondScreen(navController: NavController,
                 name: String?,
                 address: String?,
                 dateO: String?,
                 dateC: String?,
                 lat: String?,
                 lon: String?) {
    Scaffold (
        topBar = {
            TopAppBar(modifier = Modifier.height(30.dp)) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Arrow back",
                    modifier = Modifier.clickable {
                        navController.popBackStack()
                    }
                )
                Text(text = "Volver a lista")
            }
        }
            ){
        if (name != null) {
            nameP = name
        }
        if (address != null) {
            addressP = address
        }
        if (dateO != null) {
            dateOP = dateO
        }
        if (dateC != null) {
            dateCP = dateC
        }
        if (lat != null) {
            latP = lat
        }
        if (lon != null) {
            lonP = lon
        }
        Column (
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = nameP,
                fontSize = 26.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(20.dp)
            )
            Card(
                modifier = Modifier.size(width = 320.dp, height = 550.dp),
                shape = RoundedCornerShape(8.dp),
                elevation = 10.dp,

                ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        modifier = Modifier.height(250.dp),
                        painter = painterResource(id = R.drawable.logoo),
                        contentDescription = "Detalle",
                        )

                    Text(

                        text = "Plazas Disponibles: 3",
                        fontSize = 22.sp,
                        color = Color.Black,
                    )
                    Text(
                        modifier = Modifier.padding(10.dp),
                        text = "Precio: S/ 5.00",
                        fontSize = 16.sp,
                        color = Color.Black,
                    )
                    Text(
                        text = addressP,
                        fontSize = 16.sp,
                        color = Color.Black,
                    )
                    Text(
                        text = "Horario: $dateO - $dateC",
                        fontSize = 16.sp,
                        color = Color.Black,
                    )
                    Text(
                        text = "Número: 924371494",
                        fontSize = 16.sp,
                        color = Color.Black,
                    )
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Spacer(Modifier.size(8.dp))
                        Button(onClick = {navController.navigate(route = AppScreens.LoginScreen.route )}, shape = RoundedCornerShape(50),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = Color(0xFFFFAB00),
                                contentColor = Color(0xFFFAFAF7)
                            )) {
                            Text("RESERVAR")
                        }
                        Spacer(Modifier.size(8.dp))
                        Button(onClick = {
                            navController.navigate(route = AppScreens.HomeScreen.route)
                        }, shape = RoundedCornerShape(50),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = Color(0xFF7E7E7D),
                                contentColor = Color(0xFFFAFAF7)
                            )
                        ) {
                            Text("Volver al Mapa")
                        }

                    }
                }}

        }


    }
}