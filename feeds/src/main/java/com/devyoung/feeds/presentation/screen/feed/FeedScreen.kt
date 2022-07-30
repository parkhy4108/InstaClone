package com.devyoung.feeds.presentation.screen.feed

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel

import com.skydoves.landscapist.glide.GlideImage
import com.devyoung.feeds.data.model.User
import com.devyoung.feeds.presentation.composable.*
import com.devyoung.feeds.R.drawable as AppImg

@Composable
fun FeedScreen(
    openScreen: (String) -> Unit,
    viewModel: FeedViewModel = hiltViewModel()
) {
//    val feedState by viewModel.feedState

    val configuration = LocalConfiguration.current

    val randomImage1 = "https://randomwordgenerator.com/img/picture-generator/5fe1d546434faa0df7c5d57bc32f3e7b1d3ac3e4565871497c2d72d296_640.jpg"
    val randomImage2 = "https://randomwordgenerator.com/img/picture-generator/53e6dc404d51ab14f1dc8460962e33791c3ad6e04e507749712e79d0944fcd_640.jpg"
    val randomImage3 = "https://randomwordgenerator.com/img/picture-generator/55e1dd454a54a514f1dc8460962e33791c3ad6e04e507749712e79d2934cc4_640.jpg"

    Column(
        modifier = Modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        FeedTopBar(
            text = "InstaClone",
            backgroundColor = Color.White,
            elevation = 0.dp,
            onAddButtonClick = { viewModel.onPostAddClick(openScreen) },
            onHeartButtonClick = { viewModel.onHeartClick(openScreen)}
        )
        LazyColumn(
            modifier = Modifier
        ) {
            item {
                val story = false
                if(story){
//                    StoryList(user = dumData)
                }
                else {
                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Spacer(modifier = Modifier.height(30.dp))
                        Text(text = "Instaclone에 오신 것을\r\n환영합니다", fontSize = 22.sp , fontWeight = FontWeight.Bold)
//                        Text(text = "환영합니다", fontSize = 22.sp , fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(14.dp))
                        Text(text = "사진과 동영상을 보고 싶은 사람을 팔로우해보세요." , fontSize = 13.sp)

                        Spacer(modifier = Modifier.height(20.dp))

                        LazyRow(
                            modifier = Modifier
                                .fillMaxHeight(),
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ){
                            items(10){ index ->
                                val screenWidth = configuration.screenWidthDp.dp
                                val cardWidthSize = 240.dp
                                val startPadding = if (index == 0) (screenWidth - cardWidthSize)/2 else 30.dp

                                Card(
                                    modifier = Modifier
                                        .padding(startPadding, 45.dp, 0.dp, 20.dp)
                                        .width(cardWidthSize)
                                        .height(350.dp),
                                    elevation = 8.dp,
                                    shape = RoundedCornerShape(20.dp)
                                ){
                                    Column(
                                        modifier = Modifier
                                            .padding(5.dp),
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                    ) {
                                        Spacer(modifier = Modifier.height(8.dp))
                                        GlideImage(
                                            imageModel = painterResource(id = AppImg.ic_empty_user_img),
                                            contentScale = ContentScale.FillBounds,
                                            placeHolder = painterResource(id = AppImg.ic_empty_user_img),
                                            modifier = Modifier
                                                .size(90.dp)
                                                .border(
                                                    width = 1.dp,
                                                    color = Color.LightGray,
                                                    shape = CircleShape
                                                )
                                                .clip(CircleShape)
                                        )
                                        Spacer(modifier = Modifier.height(8.dp))

                                        Text(
                                            modifier = Modifier
                                                .padding(0.dp),
                                            text = "Nick Name",
                                            fontWeight = FontWeight.Bold
                                        )
                                        Text(
                                            modifier = Modifier
                                                .padding(0.dp),
                                            text = "Nick Name",
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
                                                    .size(cardWidthSize/3),
                                                imageModel = randomImage1
                                            )
                                            GlideImage(
                                                modifier = Modifier
                                                    .size(cardWidthSize/3),
                                                imageModel = randomImage2
                                            )
                                            GlideImage(
                                                modifier = Modifier
                                                    .size(cardWidthSize/3),

                                                imageModel = randomImage3
                                            )
                                        }

                                        Spacer(modifier = Modifier.height(12.dp))

                                        Text(
                                            text = "Instaclone 추천",
                                            fontSize = 10.sp,
                                            color = Color.LightGray
                                        )

                                        Spacer(modifier = Modifier.height(18.dp))

                                        Button(
                                            onClick = { },
                                            modifier = Modifier,
                                            colors = ButtonDefaults.buttonColors(Color(0xFF1E88E5)),
                                        ) {
                                            Text(
                                                text = "팔로우",
                                                fontSize = 12.sp,
                                                color = Color.White,
                                            )
                                        }
                                        Spacer(modifier = Modifier.height(8.dp))
                                    }
                                }
                            }
                        }

                    }
                }

            }
            val feed = false
            if(feed){
                items(10){
                    Column(modifier = Modifier
                        .fillParentMaxHeight()) {
//                        FeedUserView(user = i)
                    }
                }
            }
            else {

            }

        }
    }



}

@Preview
@Composable
fun FeedPreview() {

}




