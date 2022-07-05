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
            share = 990,
            allLiked = 999
        )
        with(binding) {
            netology.text = post.author
            favoriteText.text =Servis.formatKM(post.allLiked)
            shareText.text = Servis.formatKM(post.share)
            favorite.setOnClickListener {
                post.liked = !post.liked
                favorite.setImageResource(
                    if (post.liked) {
                        post.allLiked++
                        favoriteText.text = Servis.formatKM(post.allLiked)
                        R.drawable.ic_baseline_favorite_24

                    } else {
                        post.allLiked --
                        favoriteText.text = Servis.formatKM(post.allLiked)
                        R.drawable.ic_baseline_favorite_border_24
                    }
                )
            }

            baselineShare.setOnClickListener {
                post.share ++
                shareText.text = Servis.formatKM(post.share)
            }
        }
    }
}
