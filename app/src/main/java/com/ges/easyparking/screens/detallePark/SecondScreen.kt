package com.ges.easyparking.screens.detallePark

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
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

@Composable
fun SecondScreen(navController: NavController) {
    Scaffold {
        Column (
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "PARQUEOS AQP",
                fontSize = 30.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(40.dp)
            )
            Card(
                modifier = Modifier.size(width = 300.dp, height = 580.dp),
                shape = RoundedCornerShape(8.dp),
                elevation = 10.dp,

                ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.logoo),
                        contentDescription = "Detalle",

                        )


                    Text(

                        text = "Plazas Disponinbles: 3",
                        fontSize = 25.sp,
                        color = Color.Black,
                    )
                    Text(
                        modifier = Modifier.padding(10.dp),
                        text = "Precio: S/ 5.00",
                        fontSize = 18.sp,
                        color = Color.Black,
                    )
                    Text(
                        text = "Dirección:Calle San Juan de Dios 315",
                        fontSize = 18.sp,
                        color = Color.Black,
                    )
                    Text(
                        text = "Horario: 08:00 - 20:00",
                        fontSize = 18.sp,
                        color = Color.Black,
                    )
                    Text(
                        text = "Número: 924371494",
                        fontSize = 18.sp,
                        color = Color.Black,
                    )
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Spacer(Modifier.size(8.dp))
                        Button(onClick = { }, shape = RoundedCornerShape(50),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = Color(0xFFFFAB00),
                                contentColor = Color(0xFFFAFAF7)
                            )) {
                            Text("RESERVAR")
                        }
                        Spacer(Modifier.size(8.dp))
                        Button(onClick = { }, shape = RoundedCornerShape(50),
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