package com.devyoung.base.snackbar

import android.content.ContentValues.TAG
import android.content.res.Resources
import android.util.Log
import androidx.annotation.StringRes
import com.devyoung.base.R

sealed class SnackBarMessage {
    class StringSnackBar(val message: String): SnackBarMessage()
    class ResourceSnackBar(@StringRes val message: Int): SnackBarMessage()

    companion object {
        fun SnackBarMessage.toMessage(resources: Resources): String {
            return when (this) {
                is StringSnackBar -> this.message
                is ResourceSnackBar -> resources.getString(this.message)
            }
        }

        fun Throwable.toSnackBarMessage(): SnackBarMessage {
            val message = this.message.orEmpty()
            Log.d(TAG, "message toSnackBarMessage: $message")
            return if (message.isNotBlank()) StringSnackBar(message)
            else ResourceSnackBar(R.string.error)
        }
    }
}