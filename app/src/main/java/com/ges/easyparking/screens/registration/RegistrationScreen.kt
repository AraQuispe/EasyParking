package com.ges.easyparking.screens.registration

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
fun RegistrationScreen(navController: NavController){
    Scaffold {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background)
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.BottomCenter
            ) {
                ConstraintLayout {
                    val (surface, fab) = createRefs()

                    Surface(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .constrainAs(surface) {
                                bottom.linkTo(parent.bottom)
                            },
                        color = Color.White,
                        shape = RoundedCornerShape(
                            topStartPercent = 8,
                            topEndPercent = 8
                        )
                    ) {
                        form(navController)
                    }

                }
            }
        }
    }
}
@Composable
fun form(navController: NavController){
    val nameValue = rememberSaveable { mutableStateOf("") }
    val emailValue = rememberSaveable { mutableStateOf("") }

    val passwordValue = rememberSaveable { mutableStateOf("") }
    val passwordValueRepeat = rememberSaveable { mutableStateOf("") }

    var passwordVisibility by remember { mutableStateOf(false) }
    var passwordRepeatVisibility by remember { mutableStateOf(false) }

    val focusManager = LocalFocusManager.current
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(
            text = "Bienvenido a EASY Parking!",
            style = MaterialTheme.typography.h4.copy(
                fontWeight = FontWeight.Medium
            )
        )

        Text(
            text = "Registro",
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
                textFieldValue = nameValue,
                textLabel = "Nombres",
                keyboardType = KeyboardType.Text,
                keyboardActions = KeyboardActions(
                    onNext = {
                        focusManager.moveFocus(FocusDirection.Down)
                    }
                ),
                imeAction = ImeAction.Next
            )
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
            TransparentTextField(
                textFieldValue = passwordValueRepeat,
                textLabel = "Repita la contraseña",
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
                            passwordRepeatVisibility = !passwordRepeatVisibility
                        }
                    ) {
                        Icon(
                            imageVector = if (passwordRepeatVisibility) {
                                Icons.Default.Visibility
                            } else {
                                Icons.Default.VisibilityOff
                            },
                            contentDescription = "Toggle Password Icon"
                        )
                    }
                },
                visualTransformation = if (passwordRepeatVisibility) {
                    VisualTransformation.None
                } else {
                    PasswordVisualTransformation()
                }
            )
        }

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            RoundedButton(
                text = "Registrarme",
                displayProgressBar = false,
                onClick = {
                    //Register
                }
            )

        }
    }
}
