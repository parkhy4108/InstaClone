package com.devyoung.feeds.presentation.screen.userStory

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.hilt.navigation.compose.hiltViewModel
import com.devyoung.base.composable.ImgLoad
import com.devyoung.base.composable.FitImgLoad

@Composable
fun UserStoryScreen(
    userNickName: String?,
    userImage: String?,
    storyImage: String?,
    popUpScreen: () -> Unit,
    viewModel: UserStoryViewModel = hiltViewModel()
) {

    val state by viewModel.state

    val progressAnimDuration = 5000
    val progressAnimation by animateFloatAsState(
        targetValue = state.progress,
        animationSpec = tween(durationMillis = progressAnimDuration, easing = LinearEasing),
        visibilityThreshold = 0.001f
    )

    LaunchedEffect(true) {
        while (state.progress < 1f) {
            viewModel.onProgressChanged(state.progress + 0.1f)
        }
        viewModel.popUpScreen(popUpScreen)
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier.fillMaxSize()) {
            FitImgLoad(
                imgUrl = storyImage?.toUri(),
                modifier = Modifier.fillMaxSize()
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                LinearProgressIndicator(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(20.dp)),
                    progress = progressAnimation
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    FitImgLoad(
                        imgUrl = userImage?.toUri(),
                        modifier = Modifier
                            .size(60.dp)
                            .clip(CircleShape)
                            .border(2.dp, Color.White, CircleShape)
                    )
                    Text(text = userNickName.toString())
                }
            }
        }
    }
}

