package com.devyoung.search.data.repository


import com.devyoung.base.*
import com.devyoung.search.data.model.User
import com.devyoung.search.domain.repository.FirebaseRepository
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FieldPath
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ListResult
import com.google.firebase.storage.ktx.storage
import javax.inject.Inject

class FirebaseRepositoryImpl @Inject constructor() : FirebaseRepository {

    override fun getMyAccountEmail(): String? {
        return Firebase.auth.currentUser?.email
    }

    override suspend fun getUserInfo(
        userEmail: String,
        onError: (Throwable) -> Unit,
        onSuccess: (User) -> Unit
    ) {
        Firebase.firestore.collection(userEmail).document(USER_INFO)
            .get()
            .addOnFailureListener { error -> onError(error) }
            .addOnSuccessListener { result ->
                onSuccess((result.toObject() ?: User()))
            }
    }

    override suspend fun searchUser(
        userNickname: String,
        onError: (Throwable) -> Unit,
        onSuccess: (String, String) -> Unit
    ) {
        Firebase.firestore
            .collection(SEARCH_USER).document(userNickname)
            .get()
            .addOnSuccessListener {
                val email = it.get(EMAIL).toString()
                val profileImg = Firebase.storage.reference.child("${email}/profileImg/profileImg.JPG")
                profileImg.downloadUrl
                    .addOnSuccessListener { uri ->
                        onSuccess(uri.toString(), email)
                    }
                    .addOnFailureListener { error ->
                        onError(error)
                    }
            }
            .addOnFailureListener { error ->
                onError(error)
            }
    }



    override suspend fun getAllPosts(
        userEmail: String,
        onError: (Throwable) -> Unit,
        onSuccess: (ArrayList<String>) -> Unit
    ) {
        val listRef = Firebase.storage.reference.child("$userEmail/post/")
        val listAllTask: Task<ListResult> = listRef.listAll()
        val image = arrayListOf<String>()
        var cnt = 0
        listAllTask
            .addOnSuccessListener { result ->
                val items = result.items
                val size = items.size
                items.forEach { ref ->
                    ref.downloadUrl.addOnSuccessListener { uri ->
                        image.add(uri.toString())
                        cnt++
                        if (cnt == size) {
                            image.sortDescending()
                            onSuccess(image)
                        }
                    }
                }
            }
            .addOnFailureListener { exception ->
                onError(exception)
            }
    }

    override suspend fun checkRequest(
        email: String,
        personEmail: String,
        onError: (Throwable) -> Unit,
        onSuccess: (Boolean) -> Unit
    ) {
        Firebase.firestore.collection(email).document(WAITING_LIST)
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

    override suspend fun checkFollowingList(
        email: String,
        personEmail: String,
        onError: (Throwable) -> Unit,
        onSuccess: (Boolean) -> Unit
    ) {
        Firebase.firestore.collection(email).document(FOLLOWING_LIST)
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

    override suspend fun sendFollowRequest(
        email: String,
        personEmail: String,
        onResult: (Throwable?) -> Unit
    ) {
        Firebase.firestore.collection(personEmail).document(REQUESTED_LIST)
            .collection(REQUESTED_EMAIL).document(email)
            .set(mapOf(EMAIL to email))
            .addOnCompleteListener {
                onResult(it.exception)
            }
    }

    override suspend fun deleteFollowRequest(
        email: String,
        personEmail: String,
        onResult: (Throwable?) -> Unit
    ) {
        Firebase.firestore.collection(personEmail).document(REQUESTED_LIST)
            .collection(REQUESTED_EMAIL).document(email)
            .delete()
            .addOnCompleteListener {
                onResult(it.exception)
            }
    }

    override suspend fun updateFollowWaitingList(
        email: String,
        personEmail: String,
        onResult: (Throwable?) -> Unit
    ) {
        Firebase.firestore.collection(email).document(WAITING_LIST)
            .collection(WAITING_EMAIL).document(personEmail)
            .set(mapOf(EMAIL to personEmail))
            .addOnCompleteListener {
                onResult(it.exception)
            }
    }

    override suspend fun deleteFollowWaitingList(
        email: String,
        personEmail: String,
        onResult: (Throwable?) -> Unit
    ) {
        Firebase.firestore.collection(email).document(WAITING_LIST)
            .collection(WAITING_EMAIL).document(personEmail)
            .delete()
            .addOnCompleteListener {
                onResult(it.exception)
            }
    }

    override suspend fun deleteFollower(
        email: String,
        personEmail: String,
        onResult: (Throwable?) -> Unit
    ) {
        Firebase.firestore.collection(personEmail).document(FOLLOWER_LIST)
            .collection(FOLLOWER_EMAIL).document(email)
            .delete()
            .addOnCompleteListener {
                onResult(it.exception)
            }
    }

    override suspend fun deleteFollowing(
        email: String,
        personEmail: String,
        onResult: (Throwable?) -> Unit
    ) {
        Firebase.firestore.collection(email).document(FOLLOWING_LIST)
            .collection(FOLLOWING_EMAIL).document(personEmail)
            .delete()
            .addOnCompleteListener {
                deleteUserStoryList(email,personEmail){
                    onResult(it)
                }
            }
    }

    override suspend fun updateFollowingNum(
        email: String,
        onResult: (Throwable?) -> Unit
    ) {
        Firebase.firestore
            .collection(email).document(USER_INFO)
            .update(FOLLOWING, FieldValue.increment(-1))
            .addOnCompleteListener { onResult(it.exception) }
    }

    override suspend fun updateFollowerNum(
        email: String,
        onResult: (Throwable?) -> Unit
    ) {
        Firebase.firestore
            .collection(email).document(USER_INFO)
            .update(FOLLOWER, FieldValue.increment(-1))
            .addOnCompleteListener { onResult(it.exception) }
    }


    private fun deleteUserStoryList(
        email: String,
        personEmail: String,
        onResult: (Throwable?) -> Unit
    ){
        Firebase.firestore.collection(email).document(STORY_LIST)
            .update(FieldPath.of(personEmail) , FieldValue.delete())
            .addOnCompleteListener { onResult(it.exception) }
    }



}

