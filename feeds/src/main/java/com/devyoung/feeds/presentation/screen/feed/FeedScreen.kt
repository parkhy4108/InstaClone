package com.devyoung.feeds.presentation.screen.feed

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.animation.animateColorAsState
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.net.toUri
import androidx.hilt.navigation.compose.hiltViewModel
import com.devyoung.base.composable.ImgLoad
import com.devyoung.base.R.drawable as AppImg
import com.devyoung.base.R.string as AppText
import com.devyoung.feeds.data.model.Post
import com.devyoung.feeds.data.model.User

import com.skydoves.landscapist.glide.GlideImage
import com.devyoung.feeds.presentation.composable.*

@Composable
fun FeedScreen(
    openScreen: (String) -> Unit,
    viewModel: FeedViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit){
        viewModel.getMyInfo()
        viewModel.getFeed()
    }

    val feedState by viewModel.feedState
    val selectedState = viewModel.isSelectedState
    val likedState = viewModel.isLikedState

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        FeedTopBar(
            text = stringResource(id = AppText.InstaClone),
            backgroundColor = Color.White,
            onAddButtonClick = { viewModel.onPostAddClick(openScreen) },
            onHeartButtonClick = { viewModel.onHeartClick(openScreen) }
        )
        LazyColumn(modifier = Modifier.fillMaxSize()){
            item {
                StoryList(
                    users = feedState.userList,
                    onStoryClicked = { viewModel.onStoryClicked() })
            }
            item {
                if(!feedState.hasFollowing){
                    Box(modifier = Modifier.fillMaxSize()){
                        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                            Column(verticalArrangement = Arrangement.Center,horizontalAlignment = Alignment.CenterHorizontally) {
                                Spacer(modifier = Modifier.height(20.dp))
                                Text(text = stringResource(id = AppText.feedDescription1), fontSize = 18.sp , fontWeight = FontWeight.Bold)
                                Spacer(modifier = Modifier.height(14.dp))
                                Text(text = stringResource(id = AppText.feedDescription2), fontSize = 12.sp)
                                Spacer(modifier = Modifier.height(20.dp))
                            }
                            UserListIndex(
                                userList = feedState.userList,
                                onCardClicked = {viewModel.onCardClicked()},
                                selectedState = selectedState,
                                onFollowButtonClicked= viewModel::onFollowButtonClick,
                                modifier = Modifier
                            )
                            Divider(modifier = Modifier.height(1.dp), color = Color.LightGray)
                        }
                    }
                }
            }
            items(feedState.feedList){ feed ->
                PostCard(
                    post = feed,
                    isLiked = likedState[feed]?: false,
                    onLikedButtonClicked = {viewModel.onLikeButtonClick(feed)},
                    onCommentClicked = {viewModel.onCommentClick(feed) }
                )
                Spacer(modifier = Modifier.height(15.dp))
            }
        }
    }
}

//@Composable
//fun PostList(
//    postList: List<Post>,
//    likedState: Map<String, Boolean>,
//    onLikedButtonClicked: (Post) -> Unit,
//    onCommentClicked: () -> Unit,
//    modifier: Modifier
//){
//    LazyColumn(
//        modifier = modifier.fillMaxSize(),
//        verticalArrangement = Arrangement.spacedBy(4.dp)
//    ){
//        items(postList){ post ->
//            PostCard(
//                post = post,
//                isLiked = likedState[post.postImg]?: false,
//                onLikedButtonClicked = onLikedButtonClicked,
//                onCommentClicked = onCommentClicked
//            )
//        }
//    }
//}

@Composable
fun PostCard(
    post: Post,
    isLiked: Boolean,
    onLikedButtonClicked: (Post) -> Unit,
    onCommentClicked: () -> Unit
){
    val buttonResource =
        if(isLiked) painterResource(id = AppImg.ic_like) else painterResource(id = AppImg.ic_nolike)
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
                post.postImg.toUri(),
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .border(2.dp, Color.Blue, CircleShape)
            )
            Text(text = post.userNickname, modifier = Modifier.padding(), fontSize = 15.sp)
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
                .padding(10.dp,0.dp)
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
                Text(modifier=Modifier.clickable {  },text = "좋아요 ${post.like}개")
                Text(
                    buildAnnotatedString {
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)){
                            append(post.userNickname)
                        }
                        append("  ${post.comments}" )
                    }

                )
                Text(modifier = Modifier.clickable {  },text = "댓글 0개 모두보기", color = Color.LightGray)

                Text(text = post.date)
            }
        }
    }
    
//    Box(
//        modifier = Modifier.fillMaxSize()
//    ){
//
//    }
}


@Composable
fun UserListIndex(
    userList: List<User>,
    selectedState: Map<Int,Boolean>,
    onFollowButtonClicked: (Int) -> Unit,
    onCardClicked: (String) -> Unit,
    modifier: Modifier = Modifier
) {

    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val cardWidthSize = 240.dp
    val startPadding = (screenWidth - cardWidthSize)/2


    LazyRow(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(startPadding,45.dp),
        horizontalArrangement = Arrangement.spacedBy(35.dp)
    ) {
        itemsIndexed(items = userList) { index, item ->
            RandomUserCard(
                index = index,
                user = item,
                selectedState[index] ?: false,
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
    index: Int,
    user: User,
    isSelected: Boolean,
    onCardClicked: (String) -> Unit,
    onFollowButtonClicked: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val randomImage1 =
        "https://randomwordgenerator.com/img/picture-generator/5fe1d546434faa0df7c5d57bc32f3e7b1d3ac3e4565871497c2d72d296_640.jpg"
    val randomImage2 =
        "https://randomwordgenerator.com/img/picture-generator/53e6dc404d51ab14f1dc8460962e33791c3ad6e04e507749712e79d0944fcd_640.jpg"
    val randomImage3 =
        "https://randomwordgenerator.com/img/picture-generator/55e1dd454a54a514f1dc8460962e33791c3ad6e04e507749712e79d2934cc4_640.jpg"

    val buttonColor by animateColorAsState(if (isSelected) Color.LightGray else Color(0xFF1E88E5))
    val buttonText = if(isSelected) stringResource(id = AppText.following) else stringResource(id = AppText.follow)

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
                GlideImage(
                    modifier = Modifier
                        .size(80.dp),
                    imageModel = randomImage1
                )
                GlideImage(
                    modifier = Modifier
                        .size(80.dp),
                    imageModel = randomImage2
                )
                GlideImage(
                    modifier = Modifier
                        .size(80.dp),

                    imageModel = randomImage3
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