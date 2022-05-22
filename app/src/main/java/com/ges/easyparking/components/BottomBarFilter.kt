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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import com.ges.easyparking.R
import androidx.compose.ui.zIndex

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
                            .background(colorResource(id = R.color.orange), RoundedCornerShape(cornerSize))
                            .border(1.dp, color = Color.Black, RoundedCornerShape(cornerSize))
                    ) {
                        Column(
                            modifier = Modifier.padding(horizontal = 10.dp)
                        ) {
                            ItemDialog(painterResource(id = R.drawable.ic_ftmoney),"Precio")
                            Divider(modifier = Modifier.border(1.dp, Color.White))
                            ItemDialog(painterResource(id = R.drawable.ic_distance),"Distancia")
                            Divider(modifier = Modifier.border(1.dp, Color.White))
                            ItemDialog(painterResource(id = R.drawable.ic_build),"Negocio")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ItemDialog(icon: Painter,text:String){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .clip(RoundedCornerShape(12)),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Icon(
            modifier = Modifier.size(26.dp),
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




