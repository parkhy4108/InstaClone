package com.devyoung.feeds.data.repository

import android.content.ContentValues
import android.content.ContentValues.TAG
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
import kotlin.collections.ArrayList
import kotlin.math.log

class FirebaseRepositoryImpl @Inject constructor() : FirebaseRepository {

    private val currentTime: Long = System.currentTimeMillis()
    private val postId =
        SimpleDateFormat("yyyy-MM-dd-HH-mm-ss", Locale.getDefault()).format(currentTime).toString()

    override suspend fun checkMyFollowerList(
        myEmail: String,
        personEmail: String,
        onError: (Throwable) -> Unit,
        onSuccess: (Boolean) -> Unit
    ) {
        Firebase.firestore.collection(myEmail).document(FOLLOWER_LIST)
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

    override suspend fun checkMyFollowingList(
        myEmail: String,
        personEmail: String,
        onError: (Throwable) -> Unit,
        onSuccess: (Boolean) -> Unit
    ) {
        Firebase.firestore.collection(myEmail).document(FOLLOWING_LIST)
            .collection(FOLLOWING_EMAIL).document(personEmail)
            .get()
            .addOnSuccessListener { result ->
                val hasDoc: Boolean = result.exists()
                onSuccess(hasDoc)
            }
            .addOnFailureListener {
                onError(it)
            }
    }

    override suspend fun checkMyWaitingList(
        myEmail: String,
        personEmail: String,
        onError: (Throwable) -> Unit,
        onSuccess: (Boolean) -> Unit
    ) {
        Firebase.firestore.collection(myEmail).document(WAITING_LIST)
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

    override suspend fun deleteRequestInMyList(
        myEmail: String,
        personEmail: String,
        onResult: (Throwable?) -> Unit
    ) {
        Firebase.firestore.collection(myEmail).document(REQUESTED_LIST)
            .collection(REQUESTED_EMAIL).document(personEmail)
            .delete()
            .addOnCompleteListener { onResult(it.exception) }
    }

    override suspend fun deleteRequestInUserList(
        myEmail: String,
        personEmail: String,
        onResult: (Throwable?) -> Unit
    ) {
        Firebase.firestore.collection(personEmail).document(REQUESTED_LIST)
            .collection(REQUESTED_EMAIL).document(myEmail)
            .delete()
            .addOnCompleteListener { onResult(it.exception) }
    }

    override suspend fun deleteUserEmailInMyWaitingList(
        myEmail: String,
        personEmail: String,
        onResult: (Throwable?) -> Unit
    ) {
        Firebase.firestore.collection(myEmail).document(WAITING_LIST)
            .collection(WAITING_EMAIL).document(personEmail)
            .delete()
            .addOnCompleteListener {
                onResult(it.exception)
            }
    }

    override suspend fun deleteMyEmailInUserWaitingList(
        myEmail: String,
        personEmail: String,
        onResult: (Throwable?) -> Unit
    ) {
        Firebase.firestore.collection(personEmail).document(WAITING_LIST)
            .collection(WAITING_EMAIL).document(myEmail)
            .delete()
            .addOnCompleteListener {
                onResult(it.exception)
            }
    }

    override suspend fun getMyInfo(email: String, onError: (Throwable) -> Unit,onSuccess: (User) -> Unit) {
        Firebase.firestore.collection(email).document("userInfo")
            .get()
            .addOnSuccessListener { result ->
                onSuccess(result.toObject()?: User())
            }
            .addOnFailureListener { error ->
                onError(error)
            }
    }



    override suspend fun getStoryUserInfo(
        myEmail: String,
        onSuccess: (List<User>) -> Unit,
        onError: (Throwable) -> Unit
    ) {
//        val list : ArrayList<User> = arrayListOf()
//        Firebase.firestore.collection(this.myEmail).document("userInfo")
//            .get()
//            .addOnSuccessListener { result ->
//                list.add(result.toObject()?: User())
//            }
    }

    override suspend fun getFeed(
        email: String,
        onError: (Throwable) -> Unit,
        onSuccess: (List<Post>) -> Unit
    ) {
        val postList = mutableListOf<Post>()
        var cnt = 0
        Firebase.firestore
            .collection(email).document("post")
            .collection("postId")
            .get()
            .addOnSuccessListener { documents ->
                for(document in documents){
                    Log.d(TAG, "getFeed: document size - ${documents.size()}")
                    if(cnt == documents.size()-1){
                        onSuccess(postList)
                    }
                    postList.add(cnt, document.toObject())
                    cnt++
                }
            }
            .addOnFailureListener { onError(it)}

    }


    override fun getUserEmail(): String? {
        return Firebase.auth.currentUser?.email
    }

    override suspend fun savePost(
        email: String,
        post: Post,
        onResult: (Throwable?) -> Unit
    ) {
        Log.d(TAG, "Post: $post ")
        Firebase.firestore
            .collection(email).document("post")
            .collection("postId").document(post.postId.toString())
            .set(post)
            .addOnCompleteListener { onResult(it.exception) }
    }

    override suspend fun sendRequestToUser(
        myEmail: String,
        personEmail: String,
        onResult: (Throwable?) -> Unit
    ) {
        Firebase.firestore.collection(personEmail).document(REQUESTED_LIST)
            .collection(REQUESTED_EMAIL).document(myEmail)
            .set(Email(email = myEmail))
            .addOnCompleteListener {
                onResult(it.exception)
            }
    }

    override suspend fun loadMyRequestedList(
        myEmail: String,
        onError: (Throwable) -> Unit,
        onSuccess: (List<User>) -> Unit
    ) {

        Firebase.firestore.collection(myEmail).document(REQUESTED_LIST)
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

    override suspend fun loadStories(
        myEmail: String,
        onSuccess: (List<User>) -> Unit,
        onError: (Throwable) -> Unit
    ) {

    }



    override suspend fun updatePostNum(
        myEmail: String,
        onResult: (Throwable?) -> Unit
    ) {
        Firebase.firestore
            .collection(myEmail).document("userInfo")
            .update("postNum", FieldValue.increment(1))
            .addOnCompleteListener { onResult(it.exception) }
    }

    override suspend fun updateFollowingNum(
        email: String,
        onResult: (Throwable?) -> Unit
    ) {
        Firebase.firestore
            .collection(email).document("userInfo")
            .update("following", FieldValue.increment(1))
            .addOnCompleteListener { onResult(it.exception) }
    }

    override suspend fun updateFollowerNum(
        email: String,
        onResult: (Throwable?) -> Unit
    ) {

        Firebase.firestore
            .collection(email).document("userInfo")
            .update("follower", FieldValue.increment(1))
            .addOnCompleteListener { onResult(it.exception) }
    }

    override suspend fun uploadFile(
        myEmail: String,
        postId: String,
        uri: Uri,
        onResult: (Throwable?) -> Unit
    ) {
        Log.d(TAG, "email: $myEmail")
        Log.d(TAG, "uri: $uri")
        val storageRef = Firebase.storage.reference
        val imagesRef: StorageReference = storageRef.child("$myEmail/post/")
        imagesRef.child(postId).putFile(uri)
            .addOnCompleteListener {
                onResult(it.exception)
            }
    }





    override suspend fun updateMyFollowerList(
        myEmail: String,
        personEmail: String,
        onResult: (Throwable?) -> Unit
    ) {
        val email = Email(email = personEmail)
        Firebase.firestore.collection(myEmail).document(FOLLOWER_LIST)
            .collection(FOLLOWER_EMAIL).document(personEmail)
            .set(email)
            .addOnCompleteListener {
                onResult(it.exception)
            }
    }



    override suspend fun updateUserFollowingList(
        myEmail: String,
        personEmail: String,
        onResult: (Throwable?) -> Unit
    ) {
        Firebase.firestore.collection(personEmail).document(FOLLOWING_LIST)
            .collection(FOLLOWING_EMAIL).document(myEmail)
            .set(Email(email = myEmail))
            .addOnCompleteListener {
                onResult(it.exception)
            }
    }



    override suspend fun updateMyWaitingList(
        myEmail: String,
        personEmail: String,
        onResult: (Throwable?) -> Unit
    ) {
        Firebase.firestore.collection(myEmail).document(WAITING_LIST)
            .collection(WAITING_EMAIL).document(personEmail)
            .set(Email(email = personEmail))
            .addOnCompleteListener {
                onResult(it.exception)
            }
    }


    private suspend fun checkPost(

    ){

    }


}