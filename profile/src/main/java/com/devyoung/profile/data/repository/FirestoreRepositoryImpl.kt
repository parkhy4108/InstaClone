package com.devyoung.profile.data.repository

import android.app.DownloadManager
import android.content.ContentValues.TAG
import android.net.Uri
import android.util.Log
import com.devyoung.profile.data.data_source.Post
import com.devyoung.profile.data.data_source.User
import com.devyoung.profile.domain.repository.FirestoreRepository
import com.devyoung.profile.presentation.ProfileState
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ListResult
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import kotlin.math.log


class FirestoreRepositoryImpl @Inject constructor() : FirestoreRepository {

    private val storage = Firebase.storage

    override suspend fun getUserInfo(
        userEmail: String,
        onError: (Throwable) -> Unit,
        onSuccess: (User) -> Unit,
    ) {
        Firebase.firestore
            .collection(userEmail)
            .document("userInfo")
            .get()
            .addOnFailureListener { error -> onError(error) }
            .addOnSuccessListener {  result ->
                onSuccess(result.toObject()?: User())
            }
    }

    override suspend fun getAllPosts(
        userEmail: String,
        onError: (Throwable) -> Unit,
        onSuccess: (ListResult) -> Unit
    ) {
        val listRef = storage.reference.child("$userEmail/post/")
        val listAllTask: Task<ListResult> = listRef.listAll()
        listAllTask
            .addOnSuccessListener { result->
                onSuccess(result)
            }
            .addOnFailureListener{ exception ->
                onError(exception)
            }
    }


//    override fun getAllPosts(
//        userEmail: String,
//        onError: (Throwable) -> Unit,
//        onSuccess: (List<Post?>) -> Unit
//    ) {
//        Firebase.firestore
//            .collection(userEmail).document("post")
//            .collection("postId")
//            .orderBy("postId",Query.Direction.DESCENDING)
//            .addSnapshotListener { value, error ->
//                if(error != null) onError(error)
//                if(value!=null) {
//                    val doc = value.documents.map { it.toObject(Post::class.java) }
//                    onSuccess(doc)
//                }
//            }
//    }



}