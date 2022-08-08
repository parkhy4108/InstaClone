package com.devyoung.feeds.presentation.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devyoung.base.R.drawable as AppImg

@Composable
fun FeedTopBar(
    text: String,
    backgroundColor: Color,
    onAddButtonClick: () -> Unit,
    onHeartButtonClick: () -> Unit
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp, 0.dp)
            .background(backgroundColor),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.padding(5.dp),
            text = text,
            fontSize = 28.sp,
            color = Color.Black,
            fontFamily = FontFamily.Cursive,
            fontWeight = FontWeight.Bold
        )
        Row(
            modifier=Modifier,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { onAddButtonClick() }) {
                Icon(
                    modifier = Modifier.size(30.dp),
                    painter = painterResource(id = AppImg.ic_add),
                    contentDescription = null)
            }
            IconButton(onClick = { onHeartButtonClick() }) {
                Icon(
                    modifier = Modifier.size(25.dp),
                    painter = painterResource(id = AppImg.ic_heart),
                    contentDescription = null)
            }
        }
    }
}