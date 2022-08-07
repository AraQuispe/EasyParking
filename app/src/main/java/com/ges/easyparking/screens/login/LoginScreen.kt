package com.ges.easyparking.screens.login

import RoundedButton
import androidx.compose.foundation.Image
import TransparentTextField
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ges.easyparking.R
import androidx.constraintlayout.compose.ConstraintLayout
import com.ges.easyparking.navigation.AppScreens
import com.ges.easyparking.userManager
import kotlinx.coroutines.launch


@Composable
fun LoginScreen(
    navController: NavController,
    state: LoginScreenState,
    isRefreshing: Boolean,
    refreshData: ()->Unit
){
    Scaffold {
        val emailValue = rememberSaveable { mutableStateOf("") }
        val passwordValue = rememberSaveable { mutableStateOf("") }
        var passwordVisibility by remember { mutableStateOf(false) }
        val focusManager = LocalFocusManager.current
        val scope = rememberCoroutineScope()

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background)
        ) {
            Image(
                painter = painterResource(id = R.drawable.logoo),
                contentDescription = "Login Image",
                contentScale = ContentScale.Inside
            )
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.BottomCenter
            ){
                ConstraintLayout{
                    val (surface, fab) = createRefs()

                    Surface(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(400.dp)
                            .constrainAs(surface) {
                                bottom.linkTo(parent.bottom)
                            },
                        color = Color.White,
                        shape = RoundedCornerShape(
                            topStartPercent = 8,
                            topEndPercent = 8
                        )
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            verticalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Text(
                                text = "EASY Parking!",
                                style = MaterialTheme.typography.h4.copy(
                                    fontWeight = FontWeight.Medium
                                )
                            )

                            Text(
                                text = "Inicia sesión",
                                style = MaterialTheme.typography.subtitle2.copy(
                                    color = MaterialTheme.colors.primary
                                )
                            )

                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                TransparentTextField(
                                    textFieldValue = emailValue,
                                    textLabel = "Correo",
                                    keyboardType = KeyboardType.Email,
                                    keyboardActions = KeyboardActions(
                                        onNext = {
                                            focusManager.moveFocus(FocusDirection.Down)
                                        }
                                    ),
                                    imeAction = ImeAction.Next
                                )

                                TransparentTextField(
                                    textFieldValue = passwordValue,
                                    textLabel = "Contraseña",
                                    keyboardType = KeyboardType.Password,
                                    keyboardActions = KeyboardActions(
                                        onDone = {
                                            focusManager.clearFocus()

                                            //TODO("LOGIN")
                                        }
                                    ),
                                    imeAction = ImeAction.Done,
                                    trailingIcon = {
                                        IconButton(
                                            onClick = {
                                                passwordVisibility = !passwordVisibility
                                            }
                                        ) {
                                            Icon(
                                                imageVector = if (passwordVisibility) {
                                                    Icons.Default.Visibility
                                                } else {
                                                    Icons.Default.VisibilityOff
                                                },
                                                contentDescription = "Toggle Password Icon"
                                            )
                                        }
                                    },
                                    visualTransformation = if (passwordVisibility) {
                                        VisualTransformation.None
                                    } else {
                                        PasswordVisualTransformation()
                                    }
                                )

                                Text(
                                    modifier = Modifier.fillMaxWidth(),
                                    text = "¿Olvidaste tu contraseña?",
                                    style = MaterialTheme.typography.body1,
                                    textAlign = TextAlign.End
                                )
                            }

                            Column(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                RoundedButton(
                                    text = "Login",
                                    displayProgressBar = false,
                                    onClick = {
                                        val size = state.users.size
                                        state.users.forEach { item ->
                                            val email = item.email.toString()
                                            val password = item.password.toString()

                                            Log.d("kover", email)
                                            Log.d("kover", password)
                                        }

                                        Log.d("kover", size.toString())

                                        if(emailValue.value =="admin" && passwordValue.value =="admin"){
                                            scope.launch {
                                                userManager.storeDisplayTopBar(true)
                                                userManager.storeLoginState(true)
                                            }
                                            navController.navigate(route = AppScreens.HomeScreen.route)

                                        }
                                    }
                                )

                                ClickableText(
                                    text = buildAnnotatedString {
                                        append("¿No tienes una cuenta? ")

                                        withStyle(
                                            style = SpanStyle(
                                                color = MaterialTheme.colors.primary,
                                                fontWeight = FontWeight.Bold
                                            )
                                        ) {
                                            append("Registrarse")
                                        }
                                    }
                                ) {
                                    navController.navigate(route = AppScreens.RegistrationScreen.route)
                                }
                            }
                        }
                    }

                }
            }
        }

    }
}
