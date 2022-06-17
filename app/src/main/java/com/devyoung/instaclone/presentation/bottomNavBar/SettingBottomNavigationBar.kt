package com.devyoung.instaclone.presentation.bottomNavBar

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.devyoung.instaclone.navigation.HomeNav
import kotlin.math.roundToInt

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SettingBottomNavigationBar(
    navController: NavHostController
){
    val scaffoldState = rememberScaffoldState()
    Scaffold(
        scaffoldState = scaffoldState,
        bottomBar = {
            BottomNavigationBar(
                modifier = Modifier,
                navController = navController
            )
        }
    ) {
        HomeNav(navController)
    }
}


//    val scaffoldState = rememberScaffoldState()
//    val bottomBarHeight = 56.dp
//    val bottomBarHeightPx = with(LocalDensity.current) {
//        bottomBarHeight.roundToPx().toFloat()
//    }
//    val bottomBarOffsetHeightPx = remember { mutableStateOf(0f) }
//    val nestedScrollConnection = remember {
//        object : NestedScrollConnection {
//            override fun onPreScroll(
//                available: Offset,
//                source: NestedScrollSource,
//            ): Offset {
//                val delta = available.y
//                val newOffset = bottomBarOffsetHeightPx.value + delta
//                bottomBarOffsetHeightPx.value =
//                    newOffset.coerceIn(-bottomBarHeightPx, 0f)
//                return Offset.Zero
//            }
//        }
//    }



//     Scaffold(
//        modifier = Modifier
//            .nestedScroll(nestedScrollConnection)
//        ,
//        scaffoldState = scaffoldState,
//        bottomBar = {
//            BottomNavigationBar(
//                modifier = Modifier
//                    .height(bottomBarHeight)
//                    .offset { IntOffset(x = 0, y = -bottomBarOffsetHeightPx.value.roundToInt()) },
//                navController = navController
//            )
//        }
//    ) {
//        HomeNav(navController)
//    }