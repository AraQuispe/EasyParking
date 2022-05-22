package com.ges.easyparking.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.ges.easyparking.navigation.AppScreens
import com.ges.easyparking.navigation.CurrentRoute
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun Drawer(
    scope: CoroutineScope,
    scaffoldState: ScaffoldState,
    navController: NavHostController,
    items: List<AppScreens>
) {
    Column {
        Header(modifier = Modifier.fillMaxWidth().padding(16.dp))

        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(15.dp))

        val currentRoute = CurrentRoute(navController)
        items.forEach { item ->
            DrawerItem(item = item, selected = currentRoute == item.route) {
                navController.navigate(item.route) {
                    launchSingleTop = true
                }

                scope.launch {
                    scaffoldState.drawerState.close()
                }
            }
        }
    }
}

@Composable
fun Header(modifier: Modifier = Modifier){
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Icon(imageVector = Icons.Default.Person, contentDescription = null)
        Spacer(modifier = Modifier.size(4.dp))
        Text(text = "Mi perfil")
    }
}
@Composable
fun DrawerItem(
    item: AppScreens,
    selected: Boolean,
    onItemClick: (AppScreens) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(6.dp)
            .clip(RoundedCornerShape(12))
            .background(if (selected) Color.Blue.copy(alpha = 0.25f) else Color.Transparent)
            .padding(8.dp)
            .clickable { onItemClick(item) },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.size(48.dp),
            imageVector = item.icon,
            contentDescription = item.title,
            tint = if(selected) Color.Blue else Color.Gray
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text= item.title,
            style = TextStyle(fontSize = 18.sp),
            color = if(selected) Color.Blue else Color.Black
        )
    }
}
