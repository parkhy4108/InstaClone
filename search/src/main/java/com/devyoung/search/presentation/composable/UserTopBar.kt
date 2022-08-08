package com.devyoung.search.presentation.composable


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.devyoung.base.R.drawable as AppImg

@Composable
fun UserTopBar(
    text: String,
    onClick : () -> Unit,
    modifier: Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = { onClick() }) {
            Icon(
                painter = painterResource(id = AppImg.ic_back),
                contentDescription = null
            )
        }
        Text(
            modifier = Modifier,
            text = text,
            color = Color.Black,
            fontWeight = FontWeight.Bold
        )

    }
}