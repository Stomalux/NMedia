package ru.netology.nmedia.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import ru.netology.nmedia.db.AppDb
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.repository.PostRepository
import ru.netology.nmedia.repository.PostRepositoryImpl


private val empty = Post(
    id = 0,
    content = "",
    author = "",
    likedByMe = false,
    published = ""
)
      var tempPost: Post? = null

class PostViewModel(application: Application) : AndroidViewModel(application) {

//    private val repository: PostRepository =
//        PostRepositoryFileImpl(application)    для файла  и  ---- для мемори        /// PostRepositoryInMemoryImpl()

    private val repository: PostRepository = PostRepositoryImpl (                          // PostRepositorySQLiteImpl(
        AppDb.getInstance (context = application).postDao()                 ///  для SQL (application).postDao
    )

    ////////////////////////////////////////////////////////////////////////////////////////
    val data = repository.getAll()
    val edited = MutableLiveData(empty)


    fun save() {
        edited.value?.let {
            repository.save(it)
        }
        edited.value = empty
    }

    fun edit(post: Post) {
        println(post.id)
        edited.value = post
    }

    fun removeById(id: Long) = repository.removeById(id)

    fun changeContent(content: String) {
        val text = content.trim()
        println(text)
        if (edited.value?.content == text) {
            return
        }
        edited.value = edited.value?.copy(content = text)
        println(edited.value.toString())
    }

    fun likeById(id: Long) = repository.likeById(id)

    fun sharesById(id: Long) = repository.sharesById(id)

    fun clear() {
        edited.value = empty
    }
}
