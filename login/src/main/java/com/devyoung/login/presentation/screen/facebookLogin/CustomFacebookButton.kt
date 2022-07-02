package com.devyoung.login.presentation.screen.facebookLogin

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.devyoung.base.R
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton

//@Composable
//fun CustomFacebookButton(
//    modifier: Modifier = Modifier,
//    enabled: Boolean = true,
//    onSuccess: (LoginResult) -> Unit,
//    onCancel: () -> Unit,
//    onError: (FacebookException?) -> Unit,
//) {
//    // Button Logic
//    val callbackManager = FacebookUtil.callbackManager
//    val loginText = stringResource(R.string.facebookLoginScreen)
//    AndroidView(
//        modifier = modifier.fillMaxWidth().height(50.dp),
//        factory = ::LoginButton,
//        update = { button ->
//            button.setLoginText(loginText)
//            button.setPermissions("email")
//            button.isEnabled = enabled
//
//            button.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
//                override fun onSuccess(result: LoginResult) {
//                    onSuccess(result)
//                }
//
//                override fun onCancel() {
//                    onCancel()
//                }
//
//                override fun onError(error: FacebookException) {
//                    onError(error)
//                }
//            })
//        }
//    )
//}
//
//
//object FacebookUtil {
//    val callbackManager by lazy {
//        CallbackManager.Factory.create()
//    }
//}