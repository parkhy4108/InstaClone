package com.devyoung.search.data.repository


import android.content.ContentValues
import android.util.Log
import com.devyoung.base.*
import com.devyoung.search.data.Email
import com.devyoung.search.data.User
import com.devyoung.search.domain.repository.FirebaseRepository
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ListResult
import com.google.firebase.storage.ktx.storage
import javax.inject.Inject


data class UserEmail(
    val userEmail: String = ""
)


class FirebaseRepositoryImpl @Inject constructor() : FirebaseRepository {

    private val myEmail = Firebase.auth.currentUser?.email!!
    private val storage = Firebase.storage

    override suspend fun getMyAccountEmail(): String? {
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
        onError: (Throwable, String) -> Unit,
        onSuccess: (String, String) -> Unit
    ) {
        Firebase.firestore
            .collection("SearchUser").document(userNickname)
            .get()
            .addOnSuccessListener {
                val user: UserEmail? = it.toObject(UserEmail::class.java)
                if (user != null) {
                    val profileImg =
                        storage.reference.child("${user.userEmail}/profileImg/profileImg.JPG")
                    profileImg.downloadUrl
                        .addOnSuccessListener { uri ->
                            onSuccess(uri.toString(), user.userEmail)
                        }
                        .addOnFailureListener { error ->
                            onError(error, user.userEmail)
                        }
                }
            }
    }

    override suspend fun getAllPosts(
        userEmail: String,
        onError: (Throwable) -> Unit,
        onSuccess: (ArrayList<String>) -> Unit
    ) {
        val listRef = storage.reference.child("$userEmail/post/")
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
        personEmail: String,
        onError: (Throwable) -> Unit,
        onSuccess: (Boolean) -> Unit
    ) {
        Firebase.firestore.collection(this.myEmail).document(WAITING_LIST)
            .collection(WAITING_EMAIL).document(personEmail)
            .get()
            .addOnSuccessListener { result ->
                Log.d(ContentValues.TAG, "getRequestList: ${result.exists()}")
                val hasDoc: Boolean = result.exists()
                onSuccess(hasDoc)
            }
            .addOnFailureListener {
                onError(it)
            }
    }

    override suspend fun sendFollowRequest(
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

    override suspend fun deleteFollowRequest(
        personEmail: String,
        onResult: (Throwable?) -> Unit
    ) {
        Firebase.firestore.collection(personEmail).document(REQUESTED_LIST)
            .collection(REQUESTED_EMAIL).document(this.myEmail)
            .delete()
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

    override suspend fun deleteFollowWaitingList(
        personEmail: String,
        onResult: (Throwable?) -> Unit
    ) {
        Firebase.firestore.collection(this.myEmail).document(WAITING_LIST)
            .collection(WAITING_EMAIL).document(personEmail)
            .delete()
            .addOnCompleteListener {
                onResult(it.exception)
            }
    }


}

