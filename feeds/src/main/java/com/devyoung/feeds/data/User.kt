package com.devyoung.feeds.data

data class User(
    val name: String = "정진 무사시",
    val description: String = "오늘도 정진! 항상 한걸음 나아가는 사람이 되자!",
    val profileImage : String = "https://randomuser.me/api/portraits/men/15.jpg"
)

object DummyDataProvider{
    val userLists = List(20){User()}
}
