package com.devyoung.base.composable

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun TopBar(
    text: String,
    backgroundColor : Color,
    elevation : Dp
){
    TopAppBar(
        backgroundColor = backgroundColor,
        elevation = elevation
    ) {
        Text(
            modifier = Modifier.padding(5.dp),
            text = text,
            color = Color.Black,
        )
    }

}