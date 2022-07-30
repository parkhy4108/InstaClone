package com.devyoung.instaclone.presentation

import android.content.res.Resources
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.devyoung.base.SnackbarManager
import com.devyoung.instaclone.graph.RootNavGraph
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
            Scaffold(
                snackbarHost = {
                    SnackbarHost(
                        hostState = it,
                        modifier = Modifier.padding(8.dp),
                        snackbar = { snackbarData ->
                            Snackbar(snackbarData, contentColor = MaterialTheme.colors.onPrimary)
                        }
                    )
                },
                scaffoldState = appState.scaffoldState,
            ) {
                RootNavGraph(
                    appState = appState,
                    modifier = Modifier.padding(it)
                )
            }
        }
    }
}

@Composable
fun rememberAppState(
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    navController: NavHostController = rememberNavController(),
    snackScope: CoroutineScope = rememberCoroutineScope(),
    snackbarManager: SnackbarManager = SnackbarManager,
    resources: Resources = resources(),
) = remember(scaffoldState, navController,snackbarManager,resources,snackScope)  {
    AppState(scaffoldState,navController,snackbarManager,resources,snackScope)
}

@Composable
@ReadOnlyComposable
fun resources(): Resources {
    LocalConfiguration.current
    return LocalContext.current.resources
}






