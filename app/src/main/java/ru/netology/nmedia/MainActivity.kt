package ru.netology.nmedia

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.netology.nmedia.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)                       //(R.layout.activity_main)

        var post = Post(
            share = 0,
            allLiked = 333333333333
        )

        binding.netology.text =post.rr
        binding.favorite.setOnClickListener {
            post.liked = !post.liked
            binding.favorite.setImageResource(
                if (post.liked) {
                    post.allLiked ++



                    R.drawable.ic_baseline_favorite_24



                } else {
                    post.allLiked --
                    R.drawable.ic_baseline_favorite_border_24
                }
            )
        }
    }
}