package ru.netology.nmedia.repository

import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import ru.netology.nmedia.dto.Post

interface PostRepository {
    fun getAll(): LiveData<List<Post>>
    fun likeById(id: Long)
    fun save(post: Post)
    fun removeById(id: Long)

    fun sharesById(id: Long)


    ///   fun findPostById(postId: Long): Post?

}