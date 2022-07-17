package com.devyoung.login.data.repository

import android.content.ContentValues.TAG
import android.util.Log
import com.devyoung.login.domain.repository.FirebaseRepository
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import javax.inject.Inject

class FirebaseRepositoryImpl @Inject constructor() : FirebaseRepository {

    override fun hasUser(): Boolean {
        return Firebase.auth.currentUser != null
    }

    override fun userLogin(email: String, password: String, onResult: (Throwable?) -> Unit) {
        Firebase.auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                onResult(it.exception)
            }
    }

    override fun userSignUp(email: String, password: String, onResult: (Throwable?) -> Unit) {
        Firebase.auth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener { onResult(it.exception) }
    }


}