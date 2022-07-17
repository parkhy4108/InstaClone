package com.devyoung.feeds.data.repository

import android.net.Uri
import com.devyoung.feeds.data.model.Post
import com.devyoung.feeds.domain.reposiroty.FirestoreRepository
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ListResult
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.flow.Flow
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class FirestoreRepositoryImpl @Inject constructor() : FirestoreRepository {

    private val storage = Firebase.storage

    override suspend fun savePost(post: Post, onResult: (Throwable?) -> Unit) {
        Firebase.firestore
            .collection(post.userId).document("post")
            .collection("postId").document(post.postId)
            .set(post)
            .addOnCompleteListener { onResult(it.exception) }
    }

    override suspend fun updatePostNum(userEmail: String, onResult: (Throwable?) -> Unit) {
        Firebase.firestore
            .collection(userEmail).document("userInfo")
            .update("postNum",FieldValue.increment(1))
            .addOnCompleteListener { onResult(it.exception) }
    }

    override suspend fun uploadFile(userEmail: String, uri: Uri, onResult: (Throwable?) -> Unit) {
        val storageRef = storage.reference
        val imagesRef: StorageReference = storageRef.child("$userEmail/post/")
        val postId = SimpleDateFormat("yyyy-MM-dd-HH-mm-ss", Locale.getDefault())
        val currentTime : Long = System.currentTimeMillis()
        imagesRef.child(postId.format(currentTime).toString()).putFile(uri)
            .addOnCompleteListener {
                onResult(it.exception)
            }
    }

    override fun getLatestFeed(userId: String, onResult: (Throwable?) -> Unit) {
        Firebase.firestore.collection("Feed")
            .get()
            .addOnSuccessListener { result ->

            }
            .addOnFailureListener { exception ->

            }
    }

    override fun getNextFeed() {
        TODO("Not yet implemented")
    }

    override fun getPreviousFeed() {
        TODO("Not yet implemented")
    }

    override fun updateFeed(userEmail: String): Flow<List<Post>> {
        TODO("Not yet implemented")
    }


}