package com.devyoung.search.presentation.search

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import androidx.hilt.navigation.compose.hiltViewModel
import com.devyoung.base.composable.ImgLoad
import com.devyoung.base.R.drawable as AppImg
import com.devyoung.base.R.string as AppText


@Composable
fun SearchScreen(
    openScreen: (String) -> Unit,
    viewModel: SearchViewModel = hiltViewModel()
){

    val img1 ="https://randomwordgenerator.com/img/picture-generator/54e7d04b4250ae14f1dc8460962e33791c3ad6e04e5074417d2e72d1934cc1_640.jpg"
    val img2 ="https://randomwordgenerator.com/img/picture-generator/55e8d5424f51ab14f1dc8460962e33791c3ad6e04e507440742e7dd59f4dc3_640.jpg"
    val img3 ="https://randomwordgenerator.com/img/picture-generator/57e0d1424e50ac14f1dc8460962e33791c3ad6e04e507441722872d79644c7_640.jpg"
    val img4 ="https://randomwordgenerator.com/img/picture-generator/51e9d145435bb10ff3d8992cc12c30771037dbf85254794e732a7dd09644_640.jpg"
    val img5 ="https://randomwordgenerator.com/img/picture-generator/55e8d24b4b52a414f1dc8460962e33791c3ad6e04e5074417d2e72d39744c6_640.jpg"
    val img6 ="https://randomwordgenerator.com/img/picture-generator/5ee1d2424f4faa0df7c5d57bc32f3e7b1d3ac3e4565979487c2973d095_640.jpg"
    val img7 ="https://randomwordgenerator.com/img/picture-generator/57e9d6424a55ab14f1dc8460962e33791c3ad6e04e5074417d2e72dd9349c0_640.jpg"
    val img8 ="https://randomuser.me/api/portraits/men/14.jpg"
    val img9 ="https://randomuser.me/api/portraits/women/16.jpg"
    val img10 ="https://randomuser.me/api/portraits/women/49.jpg"
    val img11 = "https://randomuser.me/api/portraits/women/12.jpg"
    val img12 = "https://randomuser.me/api/portraits/women/32.jpg"
    val img13 ="https://randomuser.me/api/portraits/women/58.jpg"
    val img14 ="https://randomuser.me/api/portraits/men/95.jpg"
    val img15 ="https://randomuser.me/api/portraits/men/38.jpg"
    val img16 ="https://randomuser.me/api/portraits/women/45.jpg"
    val img17 ="https://randomuser.me/api/portraits/women/96.jpg"
    val img18 ="https://randomuser.me/api/portraits/women/65.jpg"
    val img19 ="https://randomuser.me/api/portraits/women/67.jpg"
    val img20 ="https://randomuser.me/api/portraits/women/39.jpg"
    val img21 ="https://randomwordgenerator.com/img/picture-generator/55e6dc414a54af14f1dc8460962e33791c3ad6e04e5074417c2f7cd3924fc7_640.jpg"
    val img22 ="https://randomwordgenerator.com/img/picture-generator/54e6d1464e51aa14f1dc8460962e33791c3ad6e04e507441722a72dc9e4bc7_640.jpg"
    val img23 ="https://randomwordgenerator.com/img/picture-generator/54e2d2464852ae14f1dc8460962e33791c3ad6e04e507440712b7bd2964ec0_640.jpg"
    val img24 ="https://randomwordgenerator.com/img/picture-generator/54e3d5434d53af14f1dc8460962e33791c3ad6e04e507749712a72dd9345c2_640.jpg"
    val img25 = "https://randomwordgenerator.com/img/picture-generator/5ee3dc4a4a4faa0df7c5d57bc32f3e7b1d3ac3e456597641702c7cd291_640.jpg"
    val img26 ="https://randomwordgenerator.com/img/picture-generator/55e1d54a4d57ab14f1dc8460962e33791c3ad6e04e50744076297cd49349cc_640.jpg"
    val img27 ="https://randomwordgenerator.com/img/picture-generator/blue-sea-anemone-3204596.jpg"
    val img28 ="https://randomwordgenerator.com/img/picture-generator/57e8d5444f53a414f1dc8460962e33791c3ad6e04e50744172287ed2914fc7_640.jpg"
    val img29 ="https://randomwordgenerator.com/img/picture-generator/54e4d6414852a514f1dc8460962e33791c3ad6e04e5074417d2c7ed09048c7_640.jpg"
    val img30 ="https://randomwordgenerator.com/img/picture-generator/53e8dd4b4c4faa0df7c5d57bc32f3e7b1d3ac3e45659794b7d287bd793_640.jpg"
    val img31 ="https://randomwordgenerator.com/img/picture-generator/art-1281718_640.jpg"

    val imgList: List<String> =
        listOf(
            img1, img19,img3,img15,img14,img9,img7,
            img18,img10,img2,img13,img4,img5,img6,
            img16,img8,img11,img12,img17,img20,img21,
            img22,img23,img24,img25,img26,img27,img28,img29,img30,img31
        )

    Column(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            SearchBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(5.dp))
                    .background(color = Color.LightGray),
                onClick = { viewModel.onSearchClick(openScreen) }
            )
        }
        Divider(color = Color.LightGray)
        RandomImgGrid(
            imgList = imgList,
            onClick = {viewModel.onImgClick()}
        )
    }
}

@Composable
fun SearchBar(
    modifier: Modifier,
    onClick: ()->Unit
){
    IconButton(
        modifier = modifier,
        onClick = { onClick() }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Icon(
                modifier = Modifier
                    .padding(10.dp,0.dp,0.dp,0.dp),
                painter = painterResource(id = AppImg.ic_search),
                contentDescription = null
            )
            Text(
                modifier = Modifier
                    .padding(10.dp,0.dp,0.dp,0.dp),
                text = stringResource(id = AppText.search),
                fontSize = 13.sp
            )
        }
    }
}

@Composable
fun RandomImgGrid(
    imgList: List<String>,
    onClick: () -> Unit
){
    val imageWidth = LocalConfiguration.current.screenWidthDp
    val imageHeight = imageWidth / 3

    LazyVerticalGrid(
        columns = GridCells.Fixed(3)
    ){
        items(imgList){ img ->
            ImgLoad(
                imgUrl = img.toUri(),
                modifier = Modifier
                    .height(imageHeight.dp)
                    .clickable {
                        onClick()
                    }
            )
        }
    }
}

//IconButton(
//modifier = Modifier
//.fillMaxWidth()
//.clip(RoundedCornerShape(5.dp))
//.background(color = Color.LightGray)
//,
//onClick = { viewModel.onSearchClick(openScreen) }
//) {
//    Row(
//        modifier = Modifier
//            .fillMaxWidth(),
//        verticalAlignment = Alignment.CenterVertically,
//        horizontalArrangement = Arrangement.Start
//    ) {
//        Icon(
//            modifier = Modifier
//                .padding(10.dp,0.dp,0.dp,0.dp),
//            painter = painterResource(id = AppImg.ic_search),
//            contentDescription = null
//        )
//        Text(
//            modifier = Modifier
//                .padding(10.dp,0.dp,0.dp,0.dp),
//            text = "검색",
//            fontSize = 13.sp
//        )
//    }
//}
//}
