package com.devyoung.profile.presentation.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun ProfileTopBar(
    text: String,
    onClick : () -> Unit,
    modifier: Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier,
            text = text,
            color = Color.Black,
        )
        Button(
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.LightGray),
            onClick = { onClick() }
        ) {
            Text(text = "로그아웃", color = Color.Black)
        }
    }
}