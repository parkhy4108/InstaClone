package com.devyoung.base.snackbar

import androidx.annotation.StringRes
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

object SnackBarManager {
    private val _messages: MutableStateFlow<SnackBarMessage?> = MutableStateFlow(null)
    val messages: StateFlow<SnackBarMessage?> get() = _messages.asStateFlow()

//    fun showMessage(@StringRes message: Int) {
//        _messages.value = SnackBarMessage.ResourceSnackBar(message)
//    }
//
//    fun showMessage(message: SnackBarMessage) {
//        _messages.value = message
//    }

    fun showMessage(@StringRes message: Int) {
        _messages.update {
            SnackBarMessage.ResourceSnackBar(message)
        }
    }

    fun showMessage(message: SnackBarMessage) {
        _messages.value = message
    }
}




