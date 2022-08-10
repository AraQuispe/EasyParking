package com.ges.easyparking.screens.registration

import RoundedButton
import TransparentTextField
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.constraintlayout.compose.ConstraintLayout


@Composable
fun RegistrationScreen(
    navController: NavController,
    state: RegistrationScreenState,
    isRefreshing: Boolean,
    refreshData: ()->Unit,
    register: (String, String, String) -> Unit
){
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
                        form(navController, state, register)
                    }

                }
            }
        }
    }
}
@Composable
fun form(navController: NavController, state: RegistrationScreenState, register: (String, String, String) -> Unit){
    val nameValue = rememberSaveable { mutableStateOf("") }
    val nameValidate = rememberSaveable{( mutableStateOf(false) ) }

    val emailValue = rememberSaveable { mutableStateOf("") }
    val emailValidate = rememberSaveable{( mutableStateOf(false) ) }

    val passwordValue = rememberSaveable { mutableStateOf("") }
    val passwordValidate = rememberSaveable{( mutableStateOf(false) ) }
    val passwordValueRepeat = rememberSaveable { mutableStateOf("") }
    val password2Validate = rememberSaveable{( mutableStateOf(false) ) }

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
            text = "Ingresa tus datos para registrarte",
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
                imeAction = ImeAction.Next,
                isError = nameValidate.value,
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
                imeAction = ImeAction.Next,
                isError = emailValidate.value
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
                },
                isError = passwordValidate.value
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
                },
                isError = false
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
                    var fields_valid = false
                    var email_valid = false
                    var passwords_valid = false

                    if (validateFields(nameValue.value, emailValue.value, passwordValue.value)){
                        fields_valid = true
                    } else {
                        // msj: los campos deben estar llenos
                        Log.d("iok", "campos vacios")
                        nameValidate.value = true
                        emailValidate.value = true
                        passwordValidate.value = true
                        password2Validate.value = true
                    }

                    if (validateEmail(emailValue.value)){
                        email_valid = true
                    } else {
                        // msj: el email no es valido
                        Log.d("iok", "email invalido")
                        emailValidate.value = true
                    }

                    if (validatePasswords(passwordValue.value, passwordValueRepeat.value)){
                        passwords_valid = true
                    } else {
                        // msj: las contrasenas no coinciden
                        Log.d("iok", "contrasenias no coinciden")
                        passwordValidate.value = true
                        password2Validate.value = true
                    }

                    if (fields_valid && email_valid && passwords_valid) {
                        // search email in DB
                        var count = 0
                        state.users.forEach { item ->
                            if (item.email == emailValue.value) count++;
                        }

                        if(count == 0) {
                            register(nameValue.value, emailValue.value, passwordValue.value)
                            Log.d("Register", "Success")
                        } else {
                            // msj: este email ya esta registrado
                            Log.d("Register", "email registrado")
                            nameValidate.value = false
                            emailValidate.value = false
                            passwordValidate.value = false
                            password2Validate.value = false
                        }
                    }

                }
            )

        }
    }
}

fun validateEmail(email: String): Boolean {
    return android.util.Patterns.EMAIL_ADDRESS.toString().toRegex().matches(email);
}

fun validateFields(name: String, email: String, password1: String): Boolean {
    return name != "" && email != "" && password1 != ""
}

fun validatePasswords(password1: String, password2: String): Boolean {
    return password1 == password2
}