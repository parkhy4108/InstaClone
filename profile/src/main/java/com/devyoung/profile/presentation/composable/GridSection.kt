package com.devyoung.profile.presentation.composable

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.devyoung.profile.data.data_source.User

@Composable
fun GridSection(user: User, width: Int) {
    val imageHeight = width / 3
    LazyVerticalGrid(
        columns = GridCells.Fixed(3)
    ) {
        item(span = { GridItemSpan(3) }) {
            UserSection(user = user)
        }
        items(user.postNum) {
            Card(
                modifier = androidx.compose.ui.Modifier
                    .height(imageHeight.dp)
            ) {
                ProfileImg(imgUrl = user.userImage)
            }
        }
    }
}