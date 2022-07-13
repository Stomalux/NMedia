package ru.netology.nmedia

data class Post(
    var id: Long = 0,
    val author: String,// = "Нетология. Университет интернет-профессий.",
    val content: String,
    val published: String,
    val likes: Int = 0,
    val likedByMe: Boolean = false,


    val share: Int = 7,
    val eye: Int = 100
)