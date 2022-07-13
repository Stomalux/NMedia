package ru.netology.nmedia.activity

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import ru.netology.nmedia.R
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


        val adapter = PostsAdapter(
            onClickListener = {
                viewModel.likeById(it.id)
            },
            onShareListener = {
                viewModel.sharesById(it.id)
            },
            onRemovedListener = {
                viewModel.removeById(it.id)
            }

        )
        binding.save.setOnClickListener {
            if (binding.content.text.isNullOrBlank()) {
                Toast.makeText(it.context,getString(R.string.empty_post_error) , Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }
            val text = binding.content.text.toString()
            viewModel.editContent(text)
            viewModel.save()

            binding.content.clearFocus()
            AndroidUtils.hideKeyboard(binding.content)
            binding.content.setText("")

        }

        binding.list.adapter = adapter
        viewModel.data.observe(this) { posts ->
            adapter.list = posts

        }
    }
}