package com.devyoung.base

const val ARG_KEY = "id"
const val ARG_NICKNAME = "userNickName"
const val ARG_USERIMG = "userImage"
const val ARG_STORYIMG = "storyImage"
sealed class Screen(val route: String) {
    object Login : Screen(route = "LOGIN")
    object SignUp : Screen(route = "Sign Up")
    object Post : Screen(route = "Post")
    object First : Screen(route = "First")
    object SearchDetail : Screen(route = "SearchDetail")
    object User : Screen(route = "User/{$ARG_KEY}")
    object FollowerRequest : Screen(route = "FollowerRequest")
    object EditProfile : Screen(route = "Edit")
    object UserStory : Screen(route = "UserStory?userNickName={$ARG_NICKNAME}&userImage={$ARG_USERIMG}&storyImage={$ARG_STORYIMG}")
    object StoryAdd : Screen(route = "StoryAdd")

    fun passId(id: String): String {
        return this.route.replace(
            oldValue = "{$ARG_KEY}",
            newValue = id
        )
    }
    fun passStory(
        userNickName: String = "",
        userImage: String = "",
        storyImage: String = ""
    ): String {
        return "UserStory?userNickName=$userNickName&userImage=$userImage&storyImage=$storyImage"
    }
}
