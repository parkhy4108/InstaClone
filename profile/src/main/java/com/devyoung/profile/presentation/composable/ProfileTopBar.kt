package com.devyoung.profile.presentation.composable

import android.text.Layout
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun ProfileTopBar(
    text: String,
    backgroundColor: Color,
    elevation: Dp,
    onClick : () -> Unit
) {
    TopAppBar(
        backgroundColor = backgroundColor,
        elevation = elevation
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp, 0.dp),
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
}