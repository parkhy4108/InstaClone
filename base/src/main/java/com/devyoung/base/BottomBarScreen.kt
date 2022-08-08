package com.devyoung.base

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

const val ID_KEY = "email"
sealed class BottomBarScreen(
    val route: String,
    val icon: ImageVector
) {
    object Feed : BottomBarScreen(
        route = "Feed",
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