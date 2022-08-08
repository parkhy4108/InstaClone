package com.devyoung.base

import androidx.annotation.StringRes
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

object SnackbarManager {
    private val _messages: MutableStateFlow<SnackbarMessage?> = MutableStateFlow(null)
    val messages: StateFlow<SnackbarMessage?> get() = _messages.asStateFlow()

    fun showMessage(@StringRes message: Int) {
        _messages.update {
            SnackbarMessage.ResourceSnackBar(message)
        }
    }

    fun showMessage(message: SnackbarMessage) {
        _messages.value = message
    }
}