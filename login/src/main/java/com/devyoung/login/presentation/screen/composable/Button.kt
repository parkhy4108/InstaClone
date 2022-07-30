package com.devyoung.login.presentation.screen.composable

import androidx.annotation.StringRes
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BasicTextButton(
    @StringRes text: Int,
    enable : Boolean,
    modifier: Modifier,
    action: () -> Unit) {

    TextButton(
        onClick = action,
        modifier = modifier,
        enabled = enable,
        shape = RoundedCornerShape(30.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF1A2FCD))
    ) {
        Text(
            text = stringResource(id = text),
            fontSize = 15.sp,
            style = TextStyle(color = Color.White)
        )
    }
}


