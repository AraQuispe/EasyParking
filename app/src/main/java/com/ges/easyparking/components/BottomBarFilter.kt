package com.ges.easyparking.components

import android.graphics.drawable.Icon
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import com.ges.easyparking.R
import androidx.compose.ui.zIndex
import com.ges.easyparking.navigation.AppScreens
import com.ges.easyparking.screens.detallePark.nameP

@Composable
fun BottomBarFilter(){
    Scaffold(
        bottomBar = {
            BottomAppBar (
                backgroundColor = colorResource(id = R.color.yellow_filter)
            ) {
                ContentBar()
            }
        }
    ){

    }

}

@Composable
fun ContentBar(){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(6.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Text(
            text= "FILTROS",
            style = TextStyle(fontSize = 18.sp),
            color =  Color.Black
        )

        val openDialog = remember { mutableStateOf(false) }
        PopupWindowDialog(openDialog)
        IconButton(onClick = { openDialog.value = !openDialog.value }) {
            Icon(painter = painterResource(id = R.drawable.ic_filter_icon_d038imc3), contentDescription = "Person Icon")
        }

    }
}


@Composable
fun PopupWindowDialog(mutableValue: MutableState<Boolean>) {
    Column(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {
        Box{
            val popupWidth = 150.dp
            val popupHeight = 270.dp
            val cornerSize = 10.dp

            if (mutableValue.value) {
                Popup(
                    alignment = Alignment.TopStart,
                    properties = PopupProperties(),
                ) {
                    Box(
                        Modifier
                            .size(popupWidth, popupHeight)
                            .padding(0.dp, 0.dp, 0.dp, 100.dp)
                            .background(
                                colorResource(id = R.color.orange),
                                RoundedCornerShape(cornerSize)
                            )
                            .border(1.dp, color = Color.Black, RoundedCornerShape(cornerSize))
                    ) {
                        Column(
                            modifier = Modifier.padding(horizontal = 10.dp)
                        ) {
                            ItemDialog(painterResource(id = R.drawable.ic_ftmoney),"Precio", 1)
                            Divider(modifier = Modifier.border(1.dp, Color.White))
                            ItemDialog(painterResource(id = R.drawable.ic_distance),"Distancia", 2)
                            Divider(modifier = Modifier.border(1.dp, Color.White))
                            ItemDialog(painterResource(id = R.drawable.ic_build),"Negocio", 3)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ItemDialog(icon: Painter,text:String, type:Number){
    var dialogState by remember {
        mutableStateOf(false)
    }
    CustomFilterDialog(dialogState = dialogState, onDismissRequest = {dialogState = !it }, type)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .clip(RoundedCornerShape(12))
            .clickable { dialogState = true },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,
    ) {
        Icon(
            modifier = Modifier.size(24.dp),
            painter = icon,
            contentDescription = "Money",
            tint = Color.White
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text= text,
            style = TextStyle(fontSize = 16.sp),
            color =  Color.White
        )
    }
}

@Composable
fun CustomFilterDialog(
    dialogState: Boolean,
    onDismissRequest: (dialogState: Boolean) -> Unit,
    type:Number
){
    if(dialogState){
        AlertDialog(
            backgroundColor = Color.White,
            onDismissRequest = { onDismissRequest(dialogState) },
            title = null,
            text = null,
            buttons = {
                Column (
                    modifier = Modifier
                        .width(300.dp)
                        .padding(15.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    when (type) {
                        1 -> {
                            ContentDialogPrice(dialogState, onDismissRequest)
                        }
                        2 -> {
                            ContentDialogDistance(dialogState, onDismissRequest)
                        }
                        3 -> {
                            ContentDialogBusiness(dialogState, onDismissRequest)
                        }
                    }

                    Spacer(Modifier.size(10.dp))
                    Divider(color= Color.DarkGray, thickness = 0.8.dp, modifier = Modifier.padding(horizontal = 20.dp ))
                    Spacer(Modifier.size(10.dp))

                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Button(onClick = { onDismissRequest(dialogState)}, shape = RoundedCornerShape(50),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = Color(0xFFF80202),
                                contentColor = Color(0xFFFAFAF7)
                            )) {
                            Text("CANCELAR")
                        }
                    }
                }
            },
            properties = DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false),
            shape = RoundedCornerShape(8.dp)
        )

    }
}

@Composable
fun ContentDialogPrice( dialogState: Boolean, onDismissRequest: (dialogState: Boolean) -> Unit){
    Text(
        text = "FILTRO POR PRECIO",
        fontSize = 24.sp,
        color = Color.Black,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(20.dp, 15.dp)
    )

    Button(onClick = { onDismissRequest(dialogState)}, shape = RoundedCornerShape(50),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color(0xFFE73471),
            contentColor = Color(0xFFFAFAF7)
        )) {
        Text("S/ 0.0 - S/ 2.00")
    }
    Spacer(Modifier.size(8.dp))

    Button(onClick = { onDismissRequest(dialogState)}, shape = RoundedCornerShape(50),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color(0xFFF09714),
            contentColor = Color(0xFFFAFAF7)
        )) {
        Text("S/ 2.10 - S/ 6.00")
    }
    Spacer(Modifier.size(8.dp))

    Button(onClick = { onDismissRequest(dialogState)}, shape = RoundedCornerShape(50),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color(0xFF488B4A),
            contentColor = Color(0xFFFAFAF7)
        )) {
        Text("S/ 6.10 - S/ 10.00")
    }
    Spacer(Modifier.size(8.dp))

    Button(onClick = { onDismissRequest(dialogState)}, shape = RoundedCornerShape(50),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color(0xFF989B98),
            contentColor = Color(0xFFFAFAF7)
        )) {
        Text("Ninguno")
    }
}

@Composable
fun ContentDialogDistance( dialogState: Boolean, onDismissRequest: (dialogState: Boolean) -> Unit){
    Text(
        text = "FILTRO POR DISTANCIA",
        fontSize = 24.sp,
        color = Color.Black,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(20.dp, 15.dp)
    )

    Button(onClick = { onDismissRequest(dialogState)}, shape = RoundedCornerShape(50),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color(0xFFE73471),
            contentColor = Color(0xFFFAFAF7)
        )) {
        Text("1KM")
    }
    Spacer(Modifier.size(8.dp))

    Button(onClick = { onDismissRequest(dialogState)}, shape = RoundedCornerShape(50),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color(0xFFF09714),
            contentColor = Color(0xFFFAFAF7)
        )) {
        Text("5KM")
    }
    Spacer(Modifier.size(8.dp))

    Button(onClick = { onDismissRequest(dialogState)}, shape = RoundedCornerShape(50),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color(0xFF488B4A),
            contentColor = Color(0xFFFAFAF7)
        )) {
        Text("10KM")
    }
    Spacer(Modifier.size(8.dp))

    Button(onClick = { onDismissRequest(dialogState)}, shape = RoundedCornerShape(50),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color(0xFF989B98),
            contentColor = Color(0xFFFAFAF7)
        )) {
        Text("Ninguno")
    }
}


@Composable
fun ContentDialogBusiness (dialogState: Boolean, onDismissRequest: (dialogState: Boolean) -> Unit){
    Text(
        text = "FILTRO POR NEGOCIO",
        fontSize = 24.sp,
        color = Color.Black,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(20.dp, 15.dp)
    )

    Button(onClick = { onDismissRequest(dialogState)}, shape = RoundedCornerShape(50),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color(0xFF5167DF),
            contentColor = Color(0xFFFAFAF7)
        )) {
        Text("Playas")
    }
    Spacer(Modifier.size(8.dp))

    Button(onClick = { onDismissRequest(dialogState)}, shape = RoundedCornerShape(50),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color(0xFFF09714),
            contentColor = Color(0xFFFAFAF7)
        )) {
        Text("Restaurantes")
    }
    Spacer(Modifier.size(8.dp))

    Button(onClick = { onDismissRequest(dialogState)}, shape = RoundedCornerShape(50),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color(0xFF488B4A),
            contentColor = Color(0xFFFAFAF7)
        )) {
        Text("Hoteles")
    }
    Spacer(Modifier.size(8.dp))

    Button(onClick = { onDismissRequest(dialogState)}, shape = RoundedCornerShape(50),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color(0xFF989B98),
            contentColor = Color(0xFFFAFAF7)
        )) {
        Text("Todos")
    }
}
