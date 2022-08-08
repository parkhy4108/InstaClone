package com.devyoung.base.composable

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.devyoung.base.R

@Composable
fun ImgLoad(
    imgUrl: Uri?,
    modifier: Modifier = Modifier
){
    val bitmap: MutableState<Bitmap?> = remember{ mutableStateOf(null) }

    Glide.with(LocalContext.current)
        .asBitmap()
        .load(imgUrl)
        .into(object : CustomTarget<Bitmap>(){
            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                bitmap.value = resource
            }
            override fun onLoadCleared(placeholder: Drawable?) {
            }

        })

    bitmap.value?.asImageBitmap()?.let {
        Image(
            bitmap = it,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = modifier
        )
    } ?: Image(
        painter = painterResource(id = R.drawable.ic_empty_user_img),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = modifier
    )
}