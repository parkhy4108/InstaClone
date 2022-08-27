package com.devyoung.feeds.presentation.screen.feed

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.core.net.toUri
import androidx.hilt.navigation.compose.hiltViewModel
import com.devyoung.base.composable.ImgLoad
import com.devyoung.base.R.drawable as AppImg
import com.devyoung.base.R.string as AppText
import android.net.Uri
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import com.devyoung.base.composable.BoundImgLoad
import com.devyoung.feeds.data.model.Post
import com.devyoung.feeds.data.model.Story
import com.devyoung.feeds.data.model.User


@Composable
fun FeedScreen(
    openScreen: (String) -> Unit,
    viewModel: FeedViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.getMyInfo()
        viewModel.getFeed()
        viewModel.getStories()
    }

    val feedState by viewModel.feedState
    val selectedState = viewModel.isSelectedState
    val likedState = viewModel.isLikedState
    val focusRequester by remember { mutableStateOf(FocusRequester()) }
    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        FeedTopBar(
            text = stringResource(id = AppText.InstaClone),
            backgroundColor = Color.White,
            isDropExpended = feedState.isDropExpanded,
            dropDownOpen = { viewModel.dropDownOpen() },
            dropDownCancel = { viewModel.dropDownCancel() },
            onPostAddButtonClick = { viewModel.onPostAddClick(openScreen) },
            onHeartButtonClick = { viewModel.onHeartClick(openScreen) },
            onStoryAddClick = { viewModel.onStoryAddClicked(openScreen) },
            focusManager = focusManager,
            focusRequester = focusRequester
        )

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            item {
                StoryList(
                    users = feedState.storyList,
                    onStoryAddClick = { viewModel.onStoryAddClicked(openScreen) }
                ) {
                    viewModel.onStoryClicked(openScreen, it)
                }
            }
            item {
                if (!feedState.hasFollowing) {
                    Box(modifier = Modifier.fillMaxSize()) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Column(
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Spacer(modifier = Modifier.height(20.dp))
                                Text(
                                    text = stringResource(id = AppText.feedDescription1),
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Spacer(modifier = Modifier.height(14.dp))
                                Text(
                                    text = stringResource(id = AppText.feedDescription2),
                                    fontSize = 12.sp
                                )
                                Spacer(modifier = Modifier.height(20.dp))
                            }
                            UserListIndex(
                                userList = feedState.userList,
                                onCardClicked = { viewModel.onCardClicked() },
                                selectedState = selectedState,
                                onFollowButtonClicked = viewModel::onFollowButtonClick,
                                modifier = Modifier
                            )
                        }
                    }
                }
            }
            items(feedState.feedList) { feed ->
                PostCard(
                    post = feed,
                    isLiked = likedState[feed] ?: false,
                    onLikedButtonClicked = { viewModel.onLikeButtonClick(feed) },
                    onCommentClicked = { viewModel.onCommentClick() }
                )
                Spacer(modifier = Modifier.height(15.dp))
            }
        }
    }
}

@Composable
fun FeedTopBar(
    text: String,
    backgroundColor: Color,
    isDropExpended: Boolean,
    dropDownCancel: () -> Unit,
    dropDownOpen: () -> Unit,
    onPostAddButtonClick: () -> Unit,
    onHeartButtonClick: () -> Unit,
    onStoryAddClick: () -> Unit,
    focusManager: FocusManager,
    focusRequester: FocusRequester
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp, 0.dp)
            .background(backgroundColor),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.padding(5.dp),
            text = text,
            fontSize = 28.sp,
            color = Color.Black,
            fontFamily = FontFamily.Cursive,
            fontWeight = FontWeight.Bold
        )
        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(modifier = Modifier) {
                IconButton(
                    onClick = { dropDownOpen() }
                ) {
                    Icon(
                        modifier = Modifier.size(30.dp),
                        painter = painterResource(id = AppImg.ic_add),
                        contentDescription = null
                    )
                }
                DropdownMenu(
                    expanded = isDropExpended,
                    onDismissRequest = { dropDownCancel() }
                ) {
                    DropdownMenuItem(
                        modifier = Modifier
                            .fillMaxWidth(),
                        onClick = {
                            onPostAddButtonClick()
                            dropDownCancel()
                        }
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = stringResource(id = AppText.board))
                            Icon(
                                painter = painterResource(id = AppImg.ic_board),
                                contentDescription = null
                            )
                        }
                    }
                    DropdownMenuItem(
                        modifier = Modifier
                            .fillMaxWidth(),
                        onClick = {
                            onStoryAddClick()
                            dropDownCancel()
                        }
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = stringResource(id = AppText.storyString))
                            Icon(
                                painter = painterResource(id = AppImg.ic_story),
                                contentDescription = null
                            )
                        }
                    }
                }
            }

            IconButton(onClick = { onHeartButtonClick() }) {
                Icon(
                    modifier = Modifier.size(25.dp),
                    painter = painterResource(id = AppImg.ic_heart),
                    contentDescription = null
                )
            }

        }
    }
}


@Composable
fun PostCard(
    post: Post,
    isLiked: Boolean,
    onLikedButtonClicked: (Post) -> Unit,
    onCommentClicked: () -> Unit
) {
    val buttonResource =
        if (isLiked) painterResource(id = AppImg.ic_like) else painterResource(id = AppImg.ic_nolike)
    Column(
        modifier = Modifier.fillMaxSize(),

        ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp, 7.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            ImgLoad(
                post.userImage.toUri(),
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .border(2.dp, Color.Blue, CircleShape)
            )
            Text(text = post.userNickName, modifier = Modifier.padding(), fontSize = 15.sp)
        }

        ImgLoad(
            imgUrl = post.postImg.toUri(),
            modifier = Modifier
                .fillMaxSize()
                .clip(RectangleShape)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp, 0.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp),
                verticalAlignment = Alignment.Top
            ) {
                IconButton(
                    onClick = { onLikedButtonClicked(post) }
                ) {
                    Icon(
                        painter = buttonResource,
                        contentDescription = null
                    )
                }
                IconButton(
                    onClick = { onCommentClicked() }
                ) {
                    Icon(
                        painter = painterResource(id = AppImg.ic_comment),
                        contentDescription = null
                    )
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(modifier = Modifier.clickable { }, text = "좋아요 ${post.like}개")
                Text(
                    buildAnnotatedString {
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                            append(post.userNickName)
                        }
                        append("  ${post.comments}")
                    }

                )
                Text(
                    modifier = Modifier.clickable { },
                    text = "댓글 0개 모두보기",
                    color = Color.LightGray
                )

                Text(text = post.date)
            }
        }
    }
}

@Composable
fun UserListIndex(
    userList: List<User>,
    selectedState: Map<Int, Boolean>,
    onFollowButtonClicked: (Int) -> Unit,
    onCardClicked: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val randomImage1 =
        "https://randomwordgenerator.com/img/picture-generator/5fe1d546434faa0df7c5d57bc32f3e7b1d3ac3e4565871497c2d72d296_640.jpg"
    val randomImage2 =
        "https://randomwordgenerator.com/img/picture-generator/53e6dc404d51ab14f1dc8460962e33791c3ad6e04e507749712e79d0944fcd_640.jpg"
    val randomImage3 =
        "https://randomwordgenerator.com/img/picture-generator/55e1dd454a54a514f1dc8460962e33791c3ad6e04e507749712e79d2934cc4_640.jpg"

    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val cardWidthSize = 240.dp
    val startPadding = (screenWidth - cardWidthSize) / 2

    LazyRow(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(startPadding, 45.dp),
        horizontalArrangement = Arrangement.spacedBy(35.dp)
    ) {
        itemsIndexed(items = userList) { index, item ->
            RandomUserCard(
                index = index,
                user = item,
                randomImage1 = randomImage1.toUri(),
                randomImage2 = randomImage2.toUri(),
                randomImage3 = randomImage3.toUri(),
                isSelected = selectedState[index] ?: false,
                onCardClicked = onCardClicked,
                onFollowButtonClicked = onFollowButtonClicked,
                modifier = Modifier
                    .width(cardWidthSize)
                    .height(350.dp)
            )
        }
    }
}

@Composable
fun RandomUserCard(
    randomImage1: Uri,
    randomImage2: Uri,
    randomImage3: Uri,
    index: Int,
    user: User,
    isSelected: Boolean,
    onCardClicked: (String) -> Unit,
    onFollowButtonClicked: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val buttonColor by animateColorAsState(if (isSelected) Color.LightGray else Color(0xFF1E88E5))
    val buttonText =
        if (isSelected) stringResource(id = AppText.following) else stringResource(id = AppText.follow)

    Card(
        modifier = modifier
            .clickable { onCardClicked(user.userEmail) },
        elevation = 8.dp,
        shape = RoundedCornerShape(20.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(5.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(8.dp))
            ImgLoad(
                imgUrl = user.userImage.toUri(),
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
                    .border(2.dp, Color(0xFF1976D2), CircleShape)
            )
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                modifier = Modifier
                    .padding(0.dp),
                text = user.userNickName,
                fontWeight = FontWeight.Bold
            )
            Text(
                modifier = Modifier
                    .padding(0.dp),
                text = user.userNickName,
                fontSize = 15.sp,
                color = Color.LightGray
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                BoundImgLoad(
                    imgUrl = randomImage1,
                    modifier = Modifier
                        .clip(RectangleShape)
                        .weight(1f)
                        .aspectRatio(1f)
                )
                BoundImgLoad(
                    imgUrl = randomImage2,
                    modifier = Modifier
                        .clip(RectangleShape)
                        .weight(1f)
                        .aspectRatio(1f)
                )
                BoundImgLoad(
                    imgUrl = randomImage3,
                    modifier = Modifier
                        .clip(RectangleShape)
                        .weight(1f)
                        .aspectRatio(1f)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = stringResource(id = AppText.feedDescription3),
                fontSize = 10.sp,
                color = Color.LightGray
            )

            Spacer(modifier = Modifier.height(18.dp))

            Button(
                onClick = { onFollowButtonClicked(index) },
                modifier = Modifier,
                colors = ButtonDefaults.buttonColors(buttonColor),
            ) {
                Text(text = buttonText, fontSize = 12.sp, color = Color.White)
            }

        }
    }
}

@Composable
fun StoryList(
    users: List<Story>,
    onStoryAddClick: () -> Unit,
    onStoryClicked: (Story) -> Unit
) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(15.dp, 5.dp),
        horizontalArrangement = Arrangement.spacedBy(25.dp)
    ) {
        itemsIndexed(items = users) { index, user ->
            when (index) {
                0 -> {
                    MyStory(
                        modifier = Modifier,
                        myStoryImg = user.userStoryImg,
                        myProfileImg = user.userImage,
                        onStoryClicked = {
                            if (user.userStoryImg == "") onStoryAddClick()
                            else onStoryClicked(user)
                        }
                    )
                }
                else -> {
                    MyPeopleStory(
                        nickName = user.userNickName,
                        userImage = user.userImage,
                        modifier = Modifier,
                        onStoryClicked = { onStoryClicked(user) }
                    )
                }
            }
        }
    }
}

@Composable
fun MyStory(
    modifier: Modifier,
    myStoryImg: String,
    myProfileImg: String,
    onStoryClicked: () -> Unit
) {
    val borderColor = if (myStoryImg == "") Color(0xFF1976D2) else Color(0xFF67FB2D)
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier,
            contentAlignment = Alignment.BottomEnd
        ) {
            ImgLoad(imgUrl = myProfileImg.toUri(), modifier = Modifier
                .size(60.dp)
                .clip(CircleShape)
                .border(2.dp, borderColor, CircleShape)
                .clickable { onStoryClicked() }
            )
            if (myStoryImg == "") {
                Icon(
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(Color.White),
                    painter = painterResource(id = AppImg.ic_add_circle),
                    contentDescription = null
                )
            }

        }

        Text(
            text = stringResource(id = AppText.myStory),
            fontSize = 10.sp,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun MyPeopleStory(
    nickName: String,
    userImage: String,
    modifier: Modifier,
    onStoryClicked: () -> Unit
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ImgLoad(
            imgUrl = userImage.toUri(),
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape)
                .border(2.dp, Color(0xFFFDD835), CircleShape)
                .clickable { onStoryClicked() })
        Text(text = nickName, fontSize = 10.sp, textAlign = TextAlign.Center)
    }
}