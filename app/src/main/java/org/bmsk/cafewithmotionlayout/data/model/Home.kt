package org.bmsk.cafewithmotionlayout.data.model

data class Home(
    val user: User,
    val appbarImage: String,
)

data class User(
    val nickname: String,
    val startCount: Int,
    val totalCount: Int,
)