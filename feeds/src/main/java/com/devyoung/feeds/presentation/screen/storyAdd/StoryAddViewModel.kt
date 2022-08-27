package com.devyoung.feeds.presentation.screen.storyAdd

import android.content.ContentValues.TAG
import android.net.Uri
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.devyoung.base.InstaViewModel
import com.devyoung.base.SnackBarManager
import com.devyoung.feeds.domain.usecase.GetUserEmail
import com.devyoung.feeds.domain.usecase.SaveStoryImg
import com.devyoung.base.R.string as AppText
import com.devyoung.base.DATE_FORMAT
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class StoryAddViewModel @Inject constructor(
    getUserEmail : GetUserEmail,
    private val saveStoryImg: SaveStoryImg
) : InstaViewModel() {

    var state = mutableStateOf(StoryAddState())

    private val imageUri get() = state.value.imageUrl

    private val email = getUserEmail()

    fun onImageChanged(newImageUri: Uri?, popUpScreen: () -> Unit) {
        if(newImageUri == null){
            popUpScreen()
        }
        state.value = state.value.copy(imageUrl = newImageUri)

    }

    fun onSaveButtonClicked() {
        if(imageUri != null){
            state.value = state.value.copy(openDialog = true)
        }
    }

    fun onDialogConfirmClick(
        popUpScreen: () -> Unit,
    ) {
        val dateForm = SimpleDateFormat(DATE_FORMAT, Locale.getDefault())
        val currentTime: Long = System.currentTimeMillis()
        state.value = state.value.copy(openDialog = false)
        state.value = state.value.copy(circleLoading = true)
        viewModelScope.launch(exceptionHandler) {
            saveStoryImg(email.toString(), imageUri.toString(), dateForm.format(currentTime) ) { error ->
                if(error!=null) onError(error)
                else {
                    SnackBarManager.showMessage(AppText.storyComplete)
                    popUpScreen()
                }
                state.value = state.value.copy(circleLoading = false)
            }
        }
    }

    fun onDialogCancel(){
        state.value = state.value.copy(openDialog = false)
    }

    fun onBackButtonClicked(
        popUpScreen: () -> Unit
    ) {
        popUpScreen()
    }


}