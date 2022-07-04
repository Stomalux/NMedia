package ru.netology.nmedia

data class Post(
    val id: Long = 0,
    val share: Long,
    var liked: Boolean = false,
    var allLiked: Long,
    var author: String = "Нетология. Университет интернет-профессий."

)