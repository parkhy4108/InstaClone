package com.devyoung.profile.presentation.composable

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import com.devyoung.base.R.string as AppText
import com.devyoung.base.composable.ImgLoad
import com.devyoung.profile.data.data_source.User

@Composable
fun UserSection(user : User){
    Column (
        modifier = Modifier
            .fillMaxWidth()
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth(),
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
                    .padding(5.dp, 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "${user.postNum}")
                Text(text = stringResource(id = AppText.post), fontWeight = FontWeight.Bold)
            }
            Column(
                modifier = Modifier
                    .padding(5.dp, 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "${user.follower}")
                Text(text = stringResource(id = AppText.follower), fontWeight = FontWeight.Bold)
            }
            Column(
                modifier = Modifier
                    .padding(5.dp, 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "${user.following}")
                Text(text = stringResource(id = AppText.following), fontWeight = FontWeight.Bold)
            }
        }
        Text(
            modifier = Modifier.padding(20.dp, 2.dp),
            text = user.userNickName)
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp, 5.dp),
            onClick = { }
        ) {
            Text(text = stringResource(id = AppText.profileEdit))
        }
    }

}

