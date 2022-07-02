package com.devyoung.profile.presentation.composable

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun RoundImgCard(imageUrl: String, modifier: Modifier) {
    Card(
        shape = CircleShape,
        modifier = modifier
    ) {
       ProfileImg(imgUrl = imageUrl)
    }
}