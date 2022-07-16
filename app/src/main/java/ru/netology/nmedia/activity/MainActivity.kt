package ru.netology.nmedia.activity

import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.LiveData
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.R
import ru.netology.nmedia.adapter.OnInteractionListener
import ru.netology.nmedia.adapter.PostsAdapter
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.util.AndroidUtils

import ru.netology.nmedia.viewmodel.PostViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val viewModel by viewModels<PostViewModel>()
        val adapter = PostsAdapter(object : OnInteractionListener {
            override fun onEdit(post: Post) {
                binding.group.visibility = VISIBLE
                viewModel.edit(post)

            }

            override fun onLike(post: Post) {
                viewModel.likeById(post.id)
            }

            override fun onShare(post: Post) {
                viewModel.sharesById(post.id)
            }

            override fun onRemove(post: Post) {
                viewModel.removeById(post.id)
            }
        })
        binding.list.adapter = adapter
        viewModel.data.observe(this) { posts ->
            adapter.submitList(posts)
        }

        viewModel.edited.observe(this) { post ->
            if (post.id == 0L) {
                return@observe
            }
            with(binding.content) {
                requestFocus()
                setText(post.content)

            }
        }
        binding.undo.setOnClickListener {
            with(binding.content) {
                binding.group.visibility = GONE
                setText("")
                clearFocus()
                AndroidUtils.hideKeyboard(this)
            }
        }

        binding.save.setOnClickListener {
            with(binding.content) {

                if (text.isNullOrBlank()) {
                    Toast.makeText(
                        this@MainActivity,
                        context.getString(R.string.empty_post_error),
                        Toast.LENGTH_SHORT
                    ).show()
                    binding.group.visibility = VISIBLE
                    return@setOnClickListener
                }

                viewModel.changeContent(text.toString())
                viewModel.save()

                binding.group.visibility = GONE
                setText("")
                clearFocus()
                AndroidUtils.hideKeyboard(this)
            }
        }
    }
}
