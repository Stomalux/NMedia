package ru.netology.nmedia.dto

data class Post(
    var id: Long = 0,
    val author: String,// = "Нетология. Университет интернет-профессий.",
    val content: String,
    val published: String,
    val likedByMe: Boolean = false,

    val likes: Int = 0,
    val share: Int = 7,
    val eye: Int = 100
)