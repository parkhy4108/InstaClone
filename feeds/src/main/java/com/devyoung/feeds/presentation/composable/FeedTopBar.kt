package com.devyoung.feeds.presentation.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.devyoung.feeds.R.drawable as AppImg
@Composable
fun FeedTopBar(
    text: String,
    backgroundColor: Color,
    elevation: Dp,
    onAddButtonClick: ()->Unit,
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
        Button(
            onClick = { onAddButtonClick() }
        ) {
            Image(
                painter = painterResource(id = AppImg.ic_add),
                contentDescription = null
            )
        }
    }
}