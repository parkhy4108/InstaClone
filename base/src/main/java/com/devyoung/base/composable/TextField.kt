package com.devyoung.base.composable

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.devyoung.base.R.drawable as AppIcon
import com.devyoung.base.R.string as AppText

@Composable
fun UserNameField(value: String, onNewValue: (String)-> Unit, modifier: Modifier) {
    TextField(
        modifier = modifier,
        value = value,
        onValueChange = { onNewValue(it) },
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.White),
        placeholder = { Text(text = stringResource(id = AppText.email))}
    )
}

@Composable
fun UserPasswordField(
    value: String,
    onNewValue: (String)-> Unit,
    modifier: Modifier
){
    var isVisible by remember {
        mutableStateOf(false)
    }

    val icon = if(isVisible) painterResource(id = AppIcon.ic_visibility)
    else painterResource(id = AppIcon.ic_visibility_off)

    val visualTransformation = if(isVisible) VisualTransformation.None
    else PasswordVisualTransformation()

    TextField(
        value = value,
        onValueChange ={onNewValue(it)},
        placeholder = { Text(text = stringResource(id = AppText.password))},
        trailingIcon = {
            IconButton(onClick = { isVisible= !isVisible}) {
                Icon(painter = icon, contentDescription ="Visibility")
            }
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        visualTransformation = visualTransformation,
        colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.White)
    )
}

@Composable
fun UserNickNameField(value: String, onNewValue: (String)-> Unit, modifier: Modifier) {
    TextField(
        modifier = modifier,
        value = value,
        onValueChange = { onNewValue(it) },
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.White),
        placeholder = { Text(text = stringResource(id = AppText.nickname))}
    )
}