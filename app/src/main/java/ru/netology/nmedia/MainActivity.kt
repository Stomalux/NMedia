package ru.netology.nmedia

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.viewmodel.PostViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
e
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel by viewModels<PostViewModel>()
        viewModel.data.observe(this) { post ->
            with(binding) {
                author.text = post.author
                published.text = post.published
                content.text = post.content
                like.setImageResource(
                    if (post.likedByMe) R.drawable.ic_liked_24 else R.drawable.ic_like_24
                )


                //netology.text = post.author
                favoriteText.text = Servis.formatKM(post.allLiked)
                shareText.text = Servis.formatKM(post.share)
                eyeText.text = Servis.formatKM(post.eye)

                favorite.setOnClickListener {
//                post.liked = !post.liked
                    favorite.setImageResource(
                        if (post.liked) {
                            post.allLiked++
                            favoriteText.text = Servis.formatKM(post.allLiked)
                            R.drawable.ic_baseline_favorite_24

                        } else {
                            post.allLiked--
                            favoriteText.text = Servis.formatKM(post.allLiked)
                            R.drawable.ic_baseline_favorite_border_24
                        }
                    )
                }

                baselineShare.setOnClickListener {
                    post.share++
                    shareText.text = Servis.formatKM(post.share)
                }

        }
//
//        val post = Post(
//            share = 990,
//            allLiked = 999
//        )

//            root.setOnClickListener {
//                post.eye --
//                eyeText.text = Servis.formatKM(post.eye)
//
//            }
//            icNetology48dp.setOnClickListener {
//                post.share --
//                shareText.text = Servis.formatKM(post.share)
//            }
        }
    }
}
