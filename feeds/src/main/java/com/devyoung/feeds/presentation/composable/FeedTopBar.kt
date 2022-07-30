package com.devyoung.feeds.presentation.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devyoung.feeds.R.drawable as AppImg
@Composable
fun FeedTopBar(
    text: String,
    backgroundColor: Color,
    elevation: Dp,
    onAddButtonClick: () -> Unit,
    onHeartButtonClick: () -> Unit
){
    TopAppBar(
        modifier = Modifier
            .fillMaxHeight(0.1f),
        backgroundColor = backgroundColor,
        elevation = elevation
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp, 0.dp),
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
                modifier=Modifier
                    .width(130.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { onAddButtonClick() }) {
                    Icon(
                        modifier = Modifier
                            .then(Modifier.size(35.dp)),
                        painter = painterResource(id = AppImg.ic_add),
                        contentDescription = null)
                }
                IconButton(onClick = { onHeartButtonClick() }) {
                    Icon(
                        modifier = Modifier
                            .then(Modifier.size(30.dp)),
                        painter = painterResource(id = AppImg.ic_heart),
                        contentDescription = null)
                }
            }
        }

    }
}