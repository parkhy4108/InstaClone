package com.devyoung.instaclone.presentation

import android.content.res.Resources
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.devyoung.base.snackbar.SnackBarManager
import com.devyoung.instaclone.navigation.MainGraph
import com.devyoung.instaclone.presentation.composable.BottomNavigationBar
import com.devyoung.instaclone.ui.theme.InstaCloneTheme
import kotlinx.coroutines.CoroutineScope

@Composable
fun InstaCloneApp() {
    InstaCloneTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            val appState = rememberAppState()
            val bottomBarState = rememberSaveable {
                appState.bottomBarState
            }
            Scaffold(
                scaffoldState = appState.scaffoldState,
                snackbarHost = {
                    SnackbarHost(
                        hostState = it,
                        modifier = Modifier,
                        snackbar = { snackBarData ->
                            Snackbar(snackBarData)
                        }
                    )
                },
                bottomBar =
                {
                    BottomNavigationBar(
                        modifier = Modifier,
                        navController = appState.navController,
                        bottomBarState = bottomBarState
                    )
                }
            ) {
                Box(modifier = Modifier.padding(it)) {
                    MainGraph(appState = appState, bottomBarState = bottomBarState)
                }
            }
        }
    }
}

@Composable
fun rememberAppState(
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    navController: NavHostController = rememberNavController(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    snackBarManager: SnackBarManager = SnackBarManager,
    resources: Resources = resource()
) = remember(scaffoldState, navController, snackBarManager, resources, coroutineScope)  {
    AppState(scaffoldState,navController,snackBarManager,resources,coroutineScope)
}

@Composable
fun resource(): Resources {
    return LocalContext.current.resources
}







