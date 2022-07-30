package com.devyoung.base

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.ViewModel
import com.devyoung.base.SnackbarMessage.Companion.toSnackbarMessage
import kotlinx.coroutines.CoroutineExceptionHandler

open class InstaViewModel : ViewModel() {

    open val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError(throwable)
    }

    open fun onError(error: Throwable) {
        Log.d(TAG, "onError: $error")
        SnackbarManager.showMessage(error.toSnackbarMessage())
    }


}