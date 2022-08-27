package com.devyoung.profile.data.repository

import android.content.ContentValues.TAG
import android.net.Uri
import android.util.Log
import androidx.core.net.toUri
import com.devyoung.base.*
import com.devyoung.profile.data.model.Post
import com.devyoung.profile.data.model.User
import com.devyoung.profile.domain.repository.FirestoreRepository
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ListResult
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import javax.inject.Inject


class FirestoreRepositoryImpl @Inject constructor() : FirestoreRepository {

    /**
     * 유저 정보를 가져오는 함수
     */
    override suspend fun getUserInfo(
        email: String,
        onError: (Throwable) -> Unit,
        onSuccess: (User) -> Unit,
    ) {
        Firebase.firestore
            .collection(email).document(USER_INFO)
            .get()
            .addOnFailureListener { error -> onError(error) }
            .addOnSuccessListener { result ->
                onSuccess(result.toObject() ?: User())
            }
    }

    /**
     * 포스트들을 화면에 띄우기 위해 이미지 리스트를 반환 함수
     */
    override suspend fun getAllPosts(
        email: String,
        onError: (Throwable) -> Unit,
        onSuccess: (List<Uri>) -> Unit
    ) {
        val listRef = Firebase.storage.reference.child("$email/post/")
        val listAllTask: Task<ListResult> = listRef.listAll()
        val image = mutableListOf<Uri>()
        var cnt = 0
        listAllTask
            .addOnSuccessListener { result ->
                val items = result.items
                val size = items.size
                for(item in items) {
                    item.downloadUrl.addOnSuccessListener { uri ->
                        image.add(cnt,uri)
                        cnt++
                        if (size == cnt) {
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


    /**
     * 닉네임 변경 시 업데이트 하는 함수
     */
    override suspend fun updateNickName(
        email: String,
        oldNickName: String,
        newNickName: String,
        onResult: (Throwable?) -> Unit
    ) {
        val userInfoStore = Firebase.firestore.collection(email).document(USER_INFO)
        val postStore = Firebase.firestore.collection(email).document(POST)
        val searchStore = Firebase.firestore.collection(SEARCH_USER).document(oldNickName)
        userInfoStore.update(USER_NICKNAME, newNickName).addOnCompleteListener {
            postStore.get().addOnSuccessListener { document ->
                document.data?.forEach { (key, map) ->
                    val post = Post()
                    val m = map as Map<*, *>
                    post.postId = m[POST_ID].toString()
                    post.postImg = m[POST_IMAGE].toString()
                    post.userId = m[USER_ID].toString()
                    post.userImage = m[USER_IMAGE].toString()
                    post.userNickName = newNickName
                    post.date = m[POST_DATE].toString()
                    post.time = m[POST_TIME].toString()
                    post.comments = m[POST_COMMENTS].toString()
                    post.like = m[POST_LIKE].toString()
                    postStore.update(key, post).addOnSuccessListener {
                        searchStore.get().addOnSuccessListener { value ->
                            value.data?.let { data ->
                                Firebase.firestore.collection(SEARCH_USER).document(newNickName)
                                    .set(data).addOnSuccessListener {
                                        searchStore.delete()
                                            .addOnCompleteListener { onResult(it.exception) }
                                    }
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * 다이얼로그 확인 버튼 클릭시 이미지 변경하는 함수
     */
    override suspend fun updateProfileImg(
        email: String,
        image: String,
        onResult: (Throwable?) -> Unit
    ) {

        deleteExistingProfileImg(
            email = email,
            newImage = image,
        ) {
            onResult(it)
        }
    }


    /**
     * 존재하는 닉네임인지 체크하는 함수
     */
    override suspend fun checkNickName(
        nickName: String,
        onError: (Throwable) -> Unit,
        onSuccess: (Boolean) -> Unit
    ) {
        Firebase.firestore.collection(SEARCH_USER).document(nickName)
            .get()
            .addOnSuccessListener { document ->
                val boolean = document.exists()
                onSuccess(boolean)
            }
            .addOnFailureListener {
                onError(it)
            }
    }

    /**
     * 기존의 프로필 이미지 지우는 함수 in Storage
     */
    private fun deleteExistingProfileImg(
        email: String,
        newImage: String,
        onResult: (Throwable?) -> Unit
    ){
        val storageRef = Firebase.storage.reference
        val imagesRef: StorageReference = storageRef.child("$email/profileImg/profileImg.JPG")
        imagesRef.delete()
            .addOnSuccessListener {
                uploadNewProfileImg(
                    email = email,
                    newImage = newImage
                ) {
                    onResult(it)
                }
            }
    }

    /**
     * 새로운 프로필 이미지 업로드하는 함수 in Storage
     */
    private fun uploadNewProfileImg(
        email: String,
        newImage: String,
        onResult: (Throwable?) -> Unit
    ) {
        val storageRef = Firebase.storage.reference
        val imagesRef: StorageReference = storageRef.child("$email/profileImg/profileImg.JPG")
        imagesRef.putFile(newImage.toUri())
            .addOnSuccessListener {
                downloadNewProfileImgUrl(
                    email = email
                ) {
                    onResult(it)
                }
            }
            .addOnFailureListener { 
                onResult(it)
            }
    }

    /**
     * 업로드 된 새로운 이미지의 Storage Url 을 다운로드 하는 함수
     */
    private fun downloadNewProfileImgUrl(
        email: String,
        onResult: (Throwable?) -> Unit
    ) {
        val storageRef = Firebase.storage.reference
        val imagesRef: StorageReference = storageRef.child("$email/profileImg/profileImg.JPG")
        imagesRef.downloadUrl
            .addOnSuccessListener {
                updateImgUserInfo(
                    email = email,
                    newImage = it.toString()
                ){ result ->
                    onResult(result)
                }
            }
            .addOnFailureListener { onResult(it) }
    }

    /**
     * 다운받은 Url 주소로 Firestore userInfo 안에 있는 프로필 Img 를 변경 함수
     */
    private fun updateImgUserInfo(
        email: String,
        newImage: String,
        onResult: (Throwable?) -> Unit
    ) {
        val userInfoStore = Firebase.firestore.collection(email).document(USER_INFO)
        userInfoStore.update(USER_IMAGE, newImage)
            .addOnSuccessListener {
                updatePostUserImg(
                    email = email,
                    img = newImage
                ) {
                    onResult(it)
                }
            }
            .addOnFailureListener{ onResult(it) }
    }

    /**
     * 각 Post 에 있는 프로필 Img Update 하는 함수
     */
    private fun updatePostUserImg(
        email: String,
        img: String,
        onResult: (Throwable?) -> Unit
    ){
        val userPostStore = Firebase.firestore.collection(email).document(POST)

        getUserPosts(
            email = email,
            onError = { onResult(it) }
        ) {
            if(!it.exists()){
                onResult(null)
            }else{
                var cnt = it.data!!.size
                it.data?.forEach {  (key, map) ->
                    val post = Post()
                    val m = map as Map<*, *>
                    post.postId = m[POST_ID].toString()
                    post.postImg = m[POST_IMAGE].toString()
                    post.userId = m[USER_ID].toString()
                    post.userImage = img
                    post.userNickName =
                        m[USER_NICKNAME].toString()
                    post.date = m[POST_DATE].toString()
                    post.time = m[POST_TIME].toString()
                    post.comments = m[POST_COMMENTS].toString()
                    post.like = m[POST_LIKE].toString()
                    userPostStore.update(key, post)
                        .addOnSuccessListener {
                            cnt--
                            if(cnt == 0){
                                onResult(null)
                            }
                        }
                        .addOnFailureListener { error ->
                            onResult(error)
                        }
                }
            }
        }
    }

    /**
     * Firestore Post DocumentSnapshot 가져오는 함수
     */
    private fun getUserPosts(
        email: String,
        onError: (Throwable) -> Unit,
        onSuccess: (DocumentSnapshot) -> Unit
    ){
        val userPostStore = Firebase.firestore.collection(email).document(POST)
        userPostStore.get()
            .addOnSuccessListener {
                onSuccess(it)
            }
            .addOnFailureListener {
                onError(it)
            }
    }
}

