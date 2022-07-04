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
            allLiked = 1
        )
        with(binding) {
            netology.text = post.author
            favoriteText.text = post.allLiked.toString()
            favorite.setOnClickListener {
                post.liked = !post.liked
                favorite.setImageResource(
                    if (post.liked) {
                        post.allLiked++
                        favoriteText.text = post.allLiked.toString()
                        R.drawable.ic_baseline_favorite_24

                    } else {
                        post.allLiked--
                        favoriteText.text = post.allLiked.toString()
                        R.drawable.ic_baseline_favorite_border_24
                    }
                )
            }
        }
    }
}