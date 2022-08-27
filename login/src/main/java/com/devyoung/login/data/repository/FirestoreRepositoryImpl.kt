package com.devyoung.login.data.repository

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.compose.foundation.Image
import com.devyoung.base.*
import com.devyoung.login.data.User
import com.devyoung.login.domain.repository.FirestoreRepository
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import java.io.ByteArrayOutputStream
import javax.inject.Inject

class FirestoreRepositoryImpl @Inject constructor() : FirestoreRepository {

    /**
     * 가입시 유저 정보 저장하는 함수
     */
    override fun saveUserInfo(
        user: User,
        img: ByteArray,
        onResult: (Throwable?) -> Unit
    ) {
        Firebase.firestore
            .collection(user.userEmail).document(USER_INFO)
            .set(user)
            .addOnCompleteListener {
                createMyStory(
                    userEmail = user.userEmail,
                    onResult = { onResult(it) }
                )
                createSearchUser(
                    userEmail = user.userEmail,
                    userNickName = user.userNickName,
                    onResult = { onResult(it) }
                )
                createStoryList(
                    userEmail = user.userEmail,
                    onResult = { onResult(it) }
                )
                createEmptyProfileImg(
                    userEmail = user.userEmail,
                    img = img,
                    onResult = { onResult(it) }
                )

            }
    }

    /**
     * MyStory 파일 만들어주는 함수 in Firestore
     * */
    private fun createMyStory(
        userEmail: String,
        onResult: (Throwable?) -> Unit
    ) {
        Firebase.firestore
            .collection(userEmail).document(MYSTORY)
            .set(mapOf(DATE to "", IMG to ""))
            .addOnCompleteListener { onResult(it.exception) }
    }

    /**
     * SearchUser 콜렉션에 유저 닉네임 추가하는 함수
     */
    private fun createSearchUser(
        userEmail: String,
        userNickName: String,
        onResult: (Throwable?) -> Unit
    ) {
        Firebase.firestore.collection(SEARCH_USER).document(userNickName)
            .set(mapOf(EMAIL to userEmail))
            .addOnCompleteListener { onResult(it.exception) }
    }

    /**
     * 가입 유저 콜렉션에 스토리 리스트 다큐멘트 만드는 함수
     */
    private fun createStoryList(
        userEmail: String,
        onResult: (Throwable?) -> Unit
    ) {
        Firebase.firestore.collection(userEmail).document(STORY_LIST)
            .set(mapOf(EXIST to true))
            .addOnCompleteListener { onResult(it.exception) }
    }

    /**
     * 회원생성시 초기 프로필 이미지 만들어주는 함수
     * ic_empty_user_img 를 byteArray 변환 후 Upload 함
     */
    private fun createEmptyProfileImg(
        img: ByteArray,
        userEmail: String,
        onResult: (Throwable?) -> Unit
    ) {
        val storageRef = Firebase.storage.reference
        val imagesRef: StorageReference = storageRef.child("$userEmail/profileImg/profileImg.JPG")

        val upload = imagesRef.putBytes(img)
        upload.addOnCompleteListener { onResult(it.exception) }

    }
}