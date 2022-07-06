package com.devyoung.profile.presentation.composable

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun ProfileTopBar(
    text: String,
    backgroundColor: Color,
    elevation: Dp,
    onResult : () -> Unit
) {
    TopAppBar(
        backgroundColor = backgroundColor,
        elevation = elevation
    ) {
        Text(
            modifier = Modifier.padding(5.dp),
            text = text,
            color = Color.Black,
        )
        Button(
            onClick = {
                onResult()
            }
        ) {
            Text(text = "로그아웃")
        }
    }
}