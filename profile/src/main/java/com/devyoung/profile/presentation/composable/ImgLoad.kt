package com.devyoung.profile.presentation.composable

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.Uri
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.devyoung.profile.R
import com.google.firebase.storage.StorageReference


//@Composable
//fun ImgLoad(
//    imgUrl: StorageReference,
//    modifier: Modifier = Modifier
//){
//    val bitmap: MutableState<Bitmap?> = remember{ mutableStateOf(null) }
//
//    Glide.with(LocalContext.current)
//        .asBitmap()
//        .load(imgUrl)
//        .into(object : CustomTarget<Bitmap>(){
//            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
//                bitmap.value = resource
//            }
//            override fun onLoadCleared(placeholder: Drawable?) {
//            }
//
//        })
//
//    bitmap.value?.asImageBitmap()?.let {
//        Image(
//            bitmap = it,
//            contentScale = ContentScale.FillBounds,
//            contentDescription = null,
//            modifier = modifier
//        )
//    } ?: Image(
//        painter = painterResource(id = DEFAULT_IMAGE),
//        contentScale = ContentScale.FillBounds,
//        contentDescription = null,
//        modifier = modifier
//    )
//}



@Composable
fun loadPicture(
    uri: StorageReference,
    @DrawableRes defaultImage: Int
) : MutableState<Bitmap?>{
    val bitmapState : MutableState<Bitmap?> = remember { mutableStateOf(null) }
    Glide.with(LocalContext.current)
        .asBitmap()
        .load(defaultImage)
        .into(object : CustomTarget<Bitmap>() {
            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                bitmapState.value = resource
            }

            override fun onLoadCleared(placeholder: Drawable?) {
                TODO("Not yet implemented")
            }
        })
    Glide.with(LocalContext.current)
        .asBitmap()
        .load(uri)
        .into(object : CustomTarget<Bitmap>() {
            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                bitmapState.value = resource
            }

            override fun onLoadCleared(placeholder: Drawable?) {
                TODO("Not yet implemented")
            }
        })
    return bitmapState
}