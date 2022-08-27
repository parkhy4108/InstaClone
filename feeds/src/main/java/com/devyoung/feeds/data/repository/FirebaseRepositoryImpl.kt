package com.devyoung.feeds.data.repository

import android.net.Uri
import android.util.Log
import androidx.core.net.toUri
import com.devyoung.base.*
import com.devyoung.feeds.data.model.*
import com.devyoung.feeds.domain.reposiroty.FirebaseRepository
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FieldPath
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class FirebaseRepositoryImpl @Inject constructor() : FirebaseRepository {

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

    override suspend fun getMyInfo(
        email: String,
        onError: (Throwable) -> Unit,
        onSuccess: (User) -> Unit
    ) {
        Firebase.firestore.collection(email).document(USER_INFO)
            .get()
            .addOnSuccessListener { result ->
                onSuccess(result.toObject() ?: User())
            }
            .addOnFailureListener { error ->
                onError(error)
            }
    }

    override suspend fun getStories(
        myEmail: String,
        onError: (Throwable) -> Unit,
        onSuccess: (List<Story>) -> Unit,
    ) {

        val myInfoStore = Firebase.firestore.collection(myEmail).document(USER_INFO)
        val myStoryStore = Firebase.firestore.collection(myEmail).document(MYSTORY)
        val storyListStore = Firebase.firestore.collection(myEmail).document(STORY_LIST)
        val dateForm = SimpleDateFormat(TIME_FORMAT, Locale.getDefault())
        val currentTime: Long = System.currentTimeMillis()
        val currentDate = dateForm.format(currentTime).toString().toLong()
        val list = mutableListOf<Story>()
        var cnt = 0

        myInfoStore.get().addOnSuccessListener { myInfo ->

            val myStoryObject = Story()

            myStoryObject.userNickName = myInfo.data?.get(USER_NICKNAME).toString()
            myStoryObject.userImage = myInfo.data?.get(USER_IMAGE).toString()

            myStoryStore.get()
                .addOnSuccessListener { story ->
                    if (story.get(IMG).toString() == "") {
                        myStoryObject.userStoryImg = ""
                        list.add(myStoryObject)
                        cnt++
                    } else {
                        if (currentDate - story.get(DATE).toString().toLong() > 1000000) {
                            myStoryStore.update(
                                mapOf(DATE to "", IMG to "")
                            ).addOnSuccessListener {
                                myStoryObject.userStoryImg = ""
                                list.add(myStoryObject)
                                cnt++
                            }
                        } else {
                            myStoryObject.userStoryImg = story.get(IMG).toString()
                            list.add(myStoryObject)
                            cnt++
                        }
                    }
                }
                .addOnFailureListener { onError(it) }

            storyListStore.get()
                .addOnSuccessListener { userList ->
                    val storyData = userList.data
                    if (storyData!!.size!=1) {
                        storyData.forEach { (userEmail, storedStoryImg) ->
                            Log.d("TAG", "getStories: userEmail = $userEmail")
                            val userStory = Story()
                            if (userEmail != EXIST) {
                                Firebase.firestore.collection(userEmail).document(MYSTORY).get()
                                    .addOnSuccessListener { document ->
                                        val currentStoryImg = document.get(IMG).toString()
                                        if (currentStoryImg == "") {
                                            cnt++
                                            if (cnt == (storyData.size)) {
                                                onSuccess(list)
                                            }
                                        } else {
                                            val storyDate = if(document.get(DATE).toString()=="") 0 else document.get(DATE).toString().toLong()
                                            if (currentDate - storyDate < 1000000) {
                                                getUserNickNameAndImg(
                                                    userEmail,
                                                    onError
                                                ) { nickName, img ->
                                                    if (storedStoryImg != currentStoryImg) {
                                                        updateStory(
                                                            email = myEmail,
                                                            userEmail = userEmail,
                                                            image = currentStoryImg
                                                        ) {
                                                            if (it != null) {
                                                                onError(it)
                                                            }
                                                        }
                                                    }

                                                    userStory.userNickName = nickName
                                                    userStory.userImage = img
                                                    userStory.userStoryImg = currentStoryImg

                                                    list.add(userStory)
                                                    cnt++

                                                    if (cnt == storyData.size) {
                                                        onSuccess(list)
                                                    }
                                                }
                                            }
                                            else {
                                                updateStory(myEmail, userEmail, "") {
                                                    if (it != null) {
                                                        onError(it)
                                                    } else {
                                                        cnt++
                                                        if (cnt == storyData.size) {
                                                            onSuccess(list)
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    .addOnFailureListener { onError(it) }
                            }

                        }
                    } else {
                        onSuccess(list)
                    }
                }
                .addOnFailureListener { onError(it) }
        }.addOnFailureListener {
            onError(it)
        }
    }

    override suspend fun getFeed(
        email: String,
        onError: (Throwable) -> Unit,
        onSuccess: (List<Post>) -> Unit
    ) {
        val postList = mutableListOf<Post>()
        var cnt = 0

        Firebase.firestore
            .collection(email).document(POST)
            .get()
            .addOnSuccessListener { tasks ->
                tasks.data?.forEach { (_, map) ->
                    val post = Post()
                    val m = map as Map<*, *>
                    post.postId = m[POST_ID].toString()
                    post.postImg = m[POST_IMAGE].toString()
                    post.userId = m[USER_ID].toString()
                    post.userImage = m[USER_IMAGE].toString()
                    post.userNickName = m[USER_NICKNAME].toString()
                    post.date = m[POST_DATE].toString()
                    post.time = m[POST_TIME].toString()
                    post.comments = m[POST_COMMENTS].toString()
                    post.like = m[POST_LIKE].toString()
                    postList.add(post)
                    if (cnt == (tasks.data?.size?.minus(1) ?: 0)) {
                        onSuccess(postList)
                    }
                    cnt++
                }
            }
            .addOnFailureListener { onError(it) }

    }


    override fun getUserEmail(): String? {
        return Firebase.auth.currentUser?.email
    }

    override suspend fun savePost(
        email: String,
        post: Post,
        onResult: (Throwable?) -> Unit
    ) {
        getUserNickNameAndImg(email, onResult) { nickName, img ->
            post.userNickName = nickName
            post.userImage = img
            Firebase.firestore
                .collection(email).document(POST)
                .update(post.postId, post)
                .addOnCompleteListener { onResult(it.exception) }
        }
    }

    override suspend fun sendRequestToUser(
        myEmail: String,
        personEmail: String,
        onResult: (Throwable?) -> Unit
    ) {
        Firebase.firestore.collection(personEmail).document(REQUESTED_LIST)
            .collection(REQUESTED_EMAIL).document(myEmail)
            .set(mapOf(EMAIL to myEmail))
            .addOnCompleteListener {
                onResult(it.exception)
            }
    }

    override suspend fun saveStoryImg(
        email: String,
        imageUrl: String,
        date: String,
        onResult: (Throwable?) -> Unit
    ) {
        val storageRef = Firebase.storage.reference
        val imagesRef: StorageReference = storageRef.child("$email/story/")
        imagesRef.child(MYSTORY).putFile(imageUrl.toUri())
            .addOnSuccessListener {
                Firebase.storage.reference.child("$email/story/myStory")
                    .downloadUrl.addOnSuccessListener {
                        Firebase.firestore.collection(email).document(MYSTORY)
                            .update(IMG, it.toString())
                            .addOnSuccessListener {
                                Firebase.firestore.collection(email).document(MYSTORY)
                                    .update(DATE, date)
                                    .addOnCompleteListener { result ->
                                        onResult(result.exception)
                                    }
                            }
                            .addOnFailureListener { error ->
                                onResult(error)
                            }
                    }
                    .addOnFailureListener { error ->
                        onResult(error)
                    }
            }
            .addOnFailureListener {
                onResult(it)
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
                    Firebase.firestore.collection(document.id).document(USER_INFO).get()
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


    override suspend fun updatePostNum(
        myEmail: String,
        onResult: (Throwable?) -> Unit
    ) {
        Firebase.firestore
            .collection(myEmail).document(USER_INFO)
            .update(POST_NUM, FieldValue.increment(1))
            .addOnCompleteListener { onResult(it.exception) }
    }

    override suspend fun updateFollowingNum(
        email: String,
        onResult: (Throwable?) -> Unit
    ) {
        Firebase.firestore
            .collection(email).document(USER_INFO)
            .update(FOLLOWING, FieldValue.increment(1))
            .addOnCompleteListener { onResult(it.exception) }
    }

    override suspend fun updateFollowerNum(
        email: String,
        onResult: (Throwable?) -> Unit
    ) {

        Firebase.firestore
            .collection(email).document(USER_INFO)
            .update(FOLLOWER, FieldValue.increment(1))
            .addOnCompleteListener { onResult(it.exception) }
    }

    override suspend fun uploadFile(
        myEmail: String,
        postId: String,
        uri: Uri,
        onError: (Throwable) -> Unit,
        onSuccess: (String) -> Unit
    ) {
        val storageRef = Firebase.storage.reference
        val imagesRef: StorageReference = storageRef.child("$myEmail/post/")

        imagesRef.child(postId).putFile(uri)
            .addOnSuccessListener {
                Firebase.storage.reference.child("$myEmail/post/$postId")
                    .downloadUrl.addOnSuccessListener {
                        onSuccess(it.toString())
                    }
            }
            .addOnFailureListener { onError(it) }
    }

    override suspend fun updateMyFollowerList(
        myEmail: String,
        personEmail: String,
        onResult: (Throwable?) -> Unit
    ) {
        Firebase.firestore.collection(myEmail).document(FOLLOWER_LIST)
            .collection(FOLLOWER_EMAIL).document(personEmail)
            .set(mapOf(EMAIL to personEmail))
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
            .set(mapOf(EMAIL to myEmail))
            .addOnCompleteListener {
                onResult(it.exception)
            }
    }

    override suspend fun updateStoryList(
        email: String,
        personEmail: String,
        onResult: (Throwable?) -> Unit
    ) {
        Firebase.firestore.collection(personEmail).document(STORY_LIST)
            .update(FieldPath.of(email), "")
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
            .set(mapOf(EMAIL to personEmail))
            .addOnCompleteListener {
                onResult(it.exception)
            }
    }


    private fun updateStory(
        email: String,
        userEmail: String,
        image: String?,
        onResult: (Throwable?) -> Unit
    ) {
        Firebase.firestore.collection(email).document(STORY_LIST)
            .update(FieldPath.of(userEmail),image)
            .addOnCompleteListener {
                onResult(it.exception)
            }
    }

    private fun getUserNickNameAndImg(
        email: String,
        onError: (Throwable) -> Unit,
        onSuccess: (String, String) -> Unit
    ) {
        Firebase.firestore.collection(email).document(USER_INFO)
            .get()
            .addOnSuccessListener {
                if (it.data != null) {
                    onSuccess(
                        it.data!!["userNickName"].toString(),
                        it.data!!["userImage"].toString()
                    )
                }
            }
            .addOnFailureListener {
                onError(it)
            }
    }


}
