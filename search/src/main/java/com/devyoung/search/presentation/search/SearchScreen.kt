package com.devyoung.search.presentation.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.devyoung.search.R.drawable as AppImg

@Composable
fun SearchScreen(
    openScreen: (String) -> Unit,
    viewModel: SearchViewModel = hiltViewModel()
){

    Column(
        modifier = Modifier
            .fillMaxSize()
        ,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            IconButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(5.dp))
                    .background(color = Color.LightGray)
                ,
                onClick = { viewModel.onSearchClick(openScreen) }
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
                        text = "검색",
                        fontSize = 13.sp
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(1.dp).background(Color.LightGray).fillMaxWidth())


    }
}




