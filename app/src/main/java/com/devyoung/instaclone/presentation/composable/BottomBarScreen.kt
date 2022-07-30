package com.devyoung.instaclone.presentation.composable

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen(
    val route: String,
    val icon: ImageVector
) {
    object Feed : BottomBarScreen(
        route = "HOME",
        icon = Icons.Default.Home
    )
    object Profile : BottomBarScreen(
        route = "PROFILE",
        icon = Icons.Default.Person
    )
    object Search : BottomBarScreen(
        route = "SEARCH",
        icon = Icons.Default.Search
    )
}