package com.devyoung.profile.data.repository

import android.content.ContentValues.TAG
import android.util.Log
import com.devyoung.profile.data.data_source.Post
import com.devyoung.profile.data.data_source.User
import com.devyoung.profile.domain.repository.FirestoreRepository
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ListResult
import com.google.firebase.storage.ktx.storage
import javax.inject.Inject

const val USER_INFO = "userInfo"
const val POST = "post"
const val POST_ID = "postId"

class FirestoreRepositoryImpl @Inject constructor() : FirestoreRepository {

    override suspend fun getUserInfo(
        email: String,
        onError: (Throwable) -> Unit,
        onSuccess: (User) -> Unit,
    ) {
        Firebase.firestore
            .collection(email).document(USER_INFO)
            .get()
            .addOnFailureListener { error -> onError(error) }
            .addOnSuccessListener {  result ->
                onSuccess(result.toObject()?: User())
            }
    }

    override suspend fun getAllPosts(
        email: String,
        onError: (Throwable) -> Unit,
        onSuccess: (ArrayList<String>) -> Unit
    ) {
        val listRef = Firebase.storage.reference.child("$email/post/")
        val listAllTask: Task<ListResult> = listRef.listAll()
        val image = arrayListOf<String>()
        var cnt = 0
        listAllTask
            .addOnSuccessListener { result ->
                val items = result.items.reversed()
                val size = items.size
                items.forEach { ref ->
                    Log.d(TAG, "getAllPosts: $ref")
                    ref.downloadUrl.addOnSuccessListener { uri ->
                        image.add(uri.toString())
                        cnt++
                        if(size == cnt){
                            onSuccess(image)
                        }
                    }
                }
            }
            .addOnFailureListener { exception ->
                onError(exception)
            }
    }
}

