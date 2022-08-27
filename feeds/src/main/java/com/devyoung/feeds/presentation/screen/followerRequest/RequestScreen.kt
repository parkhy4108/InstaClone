package com.devyoung.feeds.presentation.screen.followerRequest

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import androidx.hilt.navigation.compose.hiltViewModel
import com.devyoung.base.composable.CircularIndicatorProgressBar
import com.devyoung.base.composable.ImgLoad
import com.devyoung.feeds.data.model.User
import com.devyoung.base.R.string as AppText
import com.devyoung.base.R.drawable as AppImg


@Composable
fun FollowerRequestScreen(
    popUpScreen: () -> Unit,
    viewModel: RequestViewModel = hiltViewModel()
) {
    val requestState by viewModel.requestState

    val isSelectedState = viewModel.isSelectedState
    val userListState = viewModel.userListState

    LaunchedEffect(true) {
        viewModel.loadMyRequestedList()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        TopBar(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            onBackClicked = { viewModel.onBack(popUpScreen) }
        )
        Box(modifier = Modifier.fillMaxSize()){
            FollowRequestList(
                userList = userListState,
                selectedState = isSelectedState,
                onFollowButtonClicked = viewModel::onFollowButtonClicked,
                onDeleted = viewModel::onDelete
            )
            CircularIndicatorProgressBar(isDisplayed = requestState.loading)
        }


    }
}

@Composable
fun TopBar(
    modifier: Modifier=Modifier,
    onBackClicked: ()->Unit
){
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = { onBackClicked() }
        ) {
            Icon(painter = painterResource(id = AppImg.ic_back), contentDescription = null)
        }
        Text(
            text = stringResource(id = AppText.followRequest),
            modifier = Modifier
                .fillMaxWidth()
                .padding(30.dp, 0.dp)
        )

    }
}


@Composable
fun FollowRequestList(
    userList: List<User>,
    selectedState: Map<String, Int>,
    onFollowButtonClicked:(User) -> Unit,
    onDeleted: (User) -> Unit,
    modifier: Modifier = Modifier
){
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(15.dp),
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ){
        items(items = userList){ user ->
            FollowerUserCard(
                user = user,
                isSelected = selectedState[user.userEmail],
                onFollowButtonClicked = onFollowButtonClicked,
                onDeleted = onDeleted,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun FollowerUserCard (
    user: User,
    isSelected: Int?,
    onFollowButtonClicked:(User)->Unit,
    onDeleted:(User)-> Unit,
    modifier: Modifier = Modifier
){
    val buttonColor by animateColorAsState(
        if(isSelected==1) Color(0xFF1E88E5)
        else Color.LightGray
    )

    val buttonText =
        if(isSelected==1) stringResource(id = AppText.followUp)
        else stringResource(id = AppText.sent)

    Card(
        modifier = modifier.fillMaxWidth(),
        elevation = 0.dp,
        shape = RectangleShape
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(0.5f),
                verticalAlignment = Alignment.CenterVertically
            ) {
                ImgLoad(
                    imgUrl = user.userImage.toUri(),
                    modifier = Modifier
                        .size(60.dp)
                        .clip(CircleShape)
                        .border(2.dp, Color.Blue, CircleShape)
                )
                Text(
                    text = user.userNickName,
                    modifier = Modifier.padding(10.dp,0.dp,0.dp,0.dp)
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End,
            ) {
                if(isSelected==0 || isSelected==3){
                    Button(
                        modifier = Modifier
                            .size(60.dp, 30.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color(
                                0xFF1E88E5
                            )
                        ),
                        onClick = { onFollowButtonClicked(user) }
                    ) {
                        Text(text = stringResource(id = AppText.okay), fontSize = 10.sp)
                    }
                    Button(
                        modifier = Modifier
                            .padding(10.dp, 0.dp, 0.dp, 0.dp)
                            .size(60.dp, 30.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color(
                                0xFF9DA3A8
                            )
                        ),
                        onClick = { onDeleted(user) }
                    ) {
                        Text(text = stringResource(id = AppText.delete), fontSize = 10.sp)
                    }
                }
                else {
                    Button(
                        modifier = Modifier
                            .size(80.dp, 30.dp),
                        colors = ButtonDefaults.buttonColors(buttonColor),
                        onClick = { onFollowButtonClicked(user) }
                    ) {
                        Text(text = buttonText, fontSize = 10.sp)
                    }
                }
            }
        }
    }
}