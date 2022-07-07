package ru.netology.nmedia

data class Post(
    val id: Long = 0,
    //var share: Long,
    var liked: Boolean = false,
    //var allLiked: Long,
    var author: String = "Нетология. Университет интернет-профессий.",
    var eye: Long = 100,
    val content: String,
    val published: String,
    val likedByMe: Boolean,
    var likes: Int = 1
)