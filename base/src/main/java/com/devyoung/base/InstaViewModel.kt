package com.devyoung.base

import androidx.lifecycle.ViewModel
import com.devyoung.base.SnackBarMessage.Companion.toSnackBarMessage
import kotlinx.coroutines.CoroutineExceptionHandler

open class InstaViewModel : ViewModel() {

    open val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError(throwable)
    }

    open fun onError(error: Throwable) {
        SnackBarManager.showMessage(error.toSnackBarMessage())
    }
}
