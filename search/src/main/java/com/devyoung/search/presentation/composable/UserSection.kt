package com.devyoung.search.presentation.composable

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import com.devyoung.base.R.string as AppText
import com.devyoung.base.composable.ImgLoad
import com.devyoung.search.data.User
import com.devyoung.search.presentation.user.UserState


@Composable
fun UserSection(
    user : User,
    state : UserState,
    onFollowButtonClick: ()->Unit,
    onDialogCancel: ()-> Unit,
    onDialogConfirmClick: ()-> Unit,
){
    val buttonText =
        if(state.waiting) stringResource(id = AppText.sent)
        else if(state.following) stringResource(id = AppText.following)
        else if(state.searchMyself) stringResource(id = AppText.profileEdit)
        else stringResource(id = AppText.follow)

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
                .padding(0.dp,5.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            ImgLoad(
                imgUrl = user.userImage.toUri(),
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
                    .border(1.dp, Color(0xFF1976D2), CircleShape)
            )
            Column(
                modifier = Modifier
                    .padding(5.dp, 10.dp)
                ,
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
                Text(text = stringResource(id = AppText.dialog))
            },
            confirmButton = {
                Button(onClick = { onDialogConfirmClick() }) {
                    Text(text = stringResource(id = AppText.confirm))
                }
            },
            dismissButton = {
                Button(onClick = { onDialogCancel() }) {
                    Text(text = stringResource(id = AppText.dismiss))
                }
            },

        )
    }

}