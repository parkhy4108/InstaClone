package com.devyoung.feeds.data.repository

import android.content.ContentValues
import android.net.Uri
import android.util.Log
import com.devyoung.base.*
import com.devyoung.feeds.data.model.Email
import com.devyoung.feeds.data.model.Post
import com.devyoung.feeds.data.model.User
import com.devyoung.feeds.domain.reposiroty.FirebaseRepository
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.flow.Flow
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class FirebaseRepositoryImpl @Inject constructor() : FirebaseRepository {

    private val myEmail = Firebase.auth.currentUser?.email.toString()
    private val storage = Firebase.storage
    private val currentTime: Long = System.currentTimeMillis()
    private val postId =
        SimpleDateFormat("yyyy-MM-dd-HH-mm-ss", Locale.getDefault()).format(currentTime).toString()

    override suspend fun getUserEmail(): String? {
        return Firebase.auth.currentUser?.email
    }

    override suspend fun savePost(post: Post, onResult: (Throwable?) -> Unit) {
        Firebase.firestore
            .collection(this.myEmail).document("post")
            .collection("postId").document(postId)
            .set(post)
            .addOnCompleteListener { onResult(it.exception) }
    }

    override suspend fun updatePostNum(onResult: (Throwable?) -> Unit) {
        Firebase.firestore
            .collection(this.myEmail).document("userInfo")
            .update("postNum", FieldValue.increment(1))
            .addOnCompleteListener { onResult(it.exception) }
    }

    override suspend fun uploadFile(uri: Uri, onResult: (Throwable?) -> Unit) {
        val storageRef = storage.reference
        val imagesRef: StorageReference = storageRef.child("${this.myEmail}/post/")
        imagesRef.child(postId).putFile(uri)
            .addOnCompleteListener {
                onResult(it.exception)
            }
    }

    override suspend fun getRequest(
        onError: (Throwable) -> Unit,
        onSuccess: (List<User>) -> Unit
    ) {

        Firebase.firestore.collection(this.myEmail).document(REQUESTED_LIST)
            .collection(REQUESTED_EMAIL)
            .get()
            .addOnSuccessListener { result ->
                val list = mutableListOf<User>()
                var cnt = 0
                for (document in result) {
                    val size = result.size()
                    Firebase.firestore.collection(document.id).document("userInfo")
                        .get()
                        .addOnSuccessListener { info ->
                            val user = info.toObject() ?: User()
                            list.add(user)
                            cnt++
                            if (cnt == size) {
                                onSuccess(list)
                            }
                        }
                }
            }
            .addOnFailureListener {
                onError(it)
            }
    }

    override suspend fun checkRequest(
        personEmail: String,
        onError: (Throwable) -> Unit,
        onSuccess: (Boolean) -> Unit
    ) {
        Firebase.firestore.collection(this.myEmail).document(WAITING_LIST)
            .collection(WAITING_EMAIL).document(personEmail)
            .get()
            .addOnSuccessListener { result ->
                val hasDoc: Boolean = result.exists()
                onSuccess(hasDoc)
            }
            .addOnFailureListener {
                onError(it)
            }
    }

    override suspend fun deleteFollowRequest(
        personEmail: String,
        onResult: (Throwable?) -> Unit
    ) {
        Firebase.firestore.collection(this.myEmail).document(REQUESTED_LIST)
            .collection(REQUESTED_EMAIL).document(personEmail)
            .delete()
            .addOnCompleteListener { onResult(it.exception) }
    }

    override suspend fun updateFollower(
        personEmail: String,
        onResult: (Throwable?) -> Unit
    ) {
        val email = Email(email = personEmail)
        Firebase.firestore.collection(this.myEmail).document(FOLLOWER_LIST)
            .collection(FOLLOWER_EMAIL).document(personEmail)
            .set(email)
            .addOnCompleteListener {
                onResult(it.exception)
            }
    }

    override suspend fun deleteFollowWaitingList(
        personEmail: String,
        onResult: (Throwable?) -> Unit
    ) {
        Firebase.firestore.collection(personEmail).document(WAITING_LIST)
            .collection(WAITING_EMAIL).document(this.myEmail)
            .delete()
            .addOnCompleteListener {
                onResult(it.exception)
            }
    }

    override suspend fun updateFollowing(
        personEmail: String,
        onResult: (Throwable?) -> Unit
    ) {
        Firebase.firestore.collection(personEmail).document(FOLLOWING_LIST)
            .collection(FOLLOWING_EMAIL).document(this.myEmail)
            .set(Email(email = this.myEmail))
            .addOnCompleteListener {
                onResult(it.exception)
            }
    }

    override suspend fun sendFollowRequest(
        personEmail: String,
        onResult: (Throwable?) -> Unit
    ) {
        Firebase.firestore.collection(personEmail).document(REQUESTED_LIST)
            .collection(REQUESTED_EMAIL).document(this.myEmail)
            .set(Email(email = myEmail))
            .addOnCompleteListener {
                onResult(it.exception)
            }
    }

    override suspend fun updateFollowWaitingList(
        personEmail: String,
        onResult: (Throwable?) -> Unit
    ) {
        Firebase.firestore.collection(this.myEmail).document(WAITING_LIST)
            .collection(WAITING_EMAIL).document(personEmail)
            .set(Email(email = personEmail))
            .addOnCompleteListener {
                onResult(it.exception)
            }
    }

    override suspend fun checkFollowerList(
        personEmail: String,
        onError: (Throwable) -> Unit,
        onSuccess: (Boolean) -> Unit
    ) {
        Firebase.firestore.collection(this.myEmail).document(FOLLOWER_LIST)
            .collection(FOLLOWER_EMAIL).document(personEmail)
            .get()
            .addOnSuccessListener { result ->
                val hasDoc: Boolean = result.exists()
                onSuccess(hasDoc)
            }
            .addOnFailureListener {
                onError(it)
            }
    }


}