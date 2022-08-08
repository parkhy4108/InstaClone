package com.devyoung.base

import android.content.ContentValues
import android.content.res.Resources
import android.util.Log
import androidx.annotation.StringRes
import com.devyoung.base.R.string as AppText

sealed class SnackbarMessage {
    class StringSnackBar(val message: String): SnackbarMessage()
    class ResourceSnackBar(@StringRes val message: Int): SnackbarMessage()

    companion object {
        fun SnackbarMessage.toMessage(resources: Resources): String {
            return when (this) {
                is StringSnackBar -> this.message
                is ResourceSnackBar -> resources.getString(this.message)
            }
        }

        fun Throwable.toSnackbarMessage(): SnackbarMessage {
            val message = this.message.orEmpty()
            Log.d(ContentValues.TAG, "message toSnackBarMessage: $message")
            return if (message.isNotBlank()) StringSnackBar(message)
            else ResourceSnackBar(R.string.error)
        }
    }
}
