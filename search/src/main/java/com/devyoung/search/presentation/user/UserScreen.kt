package com.devyoung.search.presentation.user

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.hilt.navigation.compose.hiltViewModel
import com.devyoung.base.R
import com.devyoung.base.composable.CircularIndicatorProgressBar
import com.devyoung.base.composable.ImgLoad
import com.devyoung.search.data.model.User

@Composable
fun UserScreen(
    id: String?,
    popUpScreen: () -> Unit,
    openScreen: (String)-> Unit,
    viewModel: UserViewModel = hiltViewModel()
) {
    val userState by viewModel.userState
    val imageWidth = LocalConfiguration.current.screenWidthDp
    val imageHeight = imageWidth / 3

    LaunchedEffect(Unit){
        if (id != null) {
            viewModel.getPersonInfo(id)
            viewModel.getPersonPosts(id)
        }
    }

    if(!userState.circleLoading){
        Box(modifier = Modifier.fillMaxSize()){
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                userState.user?.userNickName?.let {
                    UserTopBar(
                        text = it,
                        onClick = popUpScreen,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp, 0.dp)
                            .background(color = Color.White)
                    )
                }

                Spacer(modifier = Modifier.height(1.dp))

                LazyVerticalGrid(columns = GridCells.Fixed(3)){
                    item(span = { GridItemSpan(3) }) {
                        userState.user?.let {
                            UserSection(
                                user = it,
                                state = userState,
                                onFollowButtonClick = { viewModel.onFollowButtonClicked(id.toString(), openScreen) },
                                onDialogCancel = { viewModel.onDialogCancel() },
                                onDialogConfirmClick = { viewModel.onDialogConfirmClicked(id.toString()) }
                            )
                        }
                    }
                    items(userState.post) { image ->
                        ImgLoad(
                            imgUrl = image.toUri(),
                            modifier = Modifier
                                .height(imageHeight.dp)
                        )
                    }
                }
            }
        }
    }
    else {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 20.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            CircularProgressIndicator(
                color = MaterialTheme.colors.primary
            )
        }
    }


}

@Composable
fun UserTopBar(
    text: String,
    onClick : () -> Unit,
    modifier: Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = { onClick() }) {
            Icon(
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = null
            )
        }
        Text(
            modifier = Modifier,
            text = text,
            color = Color.Black,
            fontWeight = FontWeight.Bold
        )

    }
}


@Composable
fun UserSection(
    user : User,
    state : UserState,
    onFollowButtonClick: ()->Unit,
    onDialogCancel: ()-> Unit,
    onDialogConfirmClick: ()-> Unit,
){
    val buttonText =
        if(state.waiting) stringResource(id = R.string.sent)
        else if(state.following) stringResource(id = R.string.following)
        else if(state.searchMyself) stringResource(id = R.string.profileEdit)
        else stringResource(id = R.string.follow)

    val buttonColor by animateColorAsState(
        if(state.waiting||state.following) Color.LightGray
        else Color(0xFF1E88E5)
    )
    Column (
        modifier = Modifier
            .fillMaxWidth()
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 5.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            ImgLoad(
                imgUrl = user.userImage.toUri(),
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
                    .border(2.dp, Color(0xFF1976D2), CircleShape)
            )
            Column(
                modifier = Modifier
                    .padding(5.dp, 10.dp)
                ,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "${user.postNum}")
                Text(text = stringResource(id = R.string.post), fontWeight = FontWeight.Bold)
            }
            Column(
                modifier = Modifier
                    .padding(5.dp, 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "${user.follower}")
                Text(text = stringResource(id = R.string.follower), fontWeight = FontWeight.Bold)
            }
            Column(
                modifier = Modifier
                    .padding(5.dp, 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "${user.following}")
                Text(text = stringResource(id = R.string.following), fontWeight = FontWeight.Bold)
            }
        }

        Text(
            modifier = Modifier.padding(20.dp, 2.dp),
            text = user.userNickName, fontWeight = FontWeight.Bold)

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp, 2.dp),
            onClick = { onFollowButtonClick() },
            colors = ButtonDefaults.buttonColors(backgroundColor = buttonColor)
        ) {
            Text(text = buttonText)
        }
        if(state.openDialog)
            AlertDialog(
                onDismissRequest = { onDialogCancel()},
                text = {
                    Text(text = stringResource(id = R.string.userDialog))
                },
                confirmButton = {
                    Button(onClick = { onDialogConfirmClick() }) {
                        Text(text = stringResource(id = R.string.confirm))
                    }
                },
                dismissButton = {
                    Button(onClick = { onDialogCancel() }) {
                        Text(text = stringResource(id = R.string.dismiss))
                    }
                },
            )
    }
}
