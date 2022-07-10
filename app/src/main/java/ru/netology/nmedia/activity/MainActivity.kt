package ru.netology.nmedia.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import ru.netology.nmedia.R
import ru.netology.nmedia.Servis
import ru.netology.nmedia.adapter.PostsAdapter
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.databinding.CardPostBinding

import ru.netology.nmedia.viewmodel.PostViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
////////////////////////////////////////////////////////////////////////////////////////////////////
        val viewModel by viewModels<PostViewModel>()
////////////////////////////////////////////////////////////////////

        val adapter = PostsAdapter( {

            viewModel.likeById(it.id)},{
            viewModel.sharesById(it.id)
        })
        binding.list.adapter = adapter
        viewModel.data.observe(this){ posts ->
            adapter.list = posts
        }


//        val adapterSharesListner = PostsAdapter {
//            viewModel.sharesById(it.id)
//        }
//        binding.list.adapter = adapterSharesListner
//        viewModel.data.observe(this){ posts ->
//            adapter.list = posts
//        }



//        viewModel.data.observe(this) { posts ->
//            binding.container.removeAllViews()
//
//            posts.map { post ->
//
//                CardPostBinding.inflate(layoutInflater,binding.container,false).apply {
//
//                    author.text = post.author
//                    published.text = post.published
//                    content.text = post.content
//                    likeCount.text = post.likes.toString()
//                    shareText.text = post.share.toString()
//                    val likeImaje = if (post.likedByMe) {
//                        R.drawable.ic_liked_24
//                    } else {
//                        R.drawable.ic_like_24
//                    }
//                    like.setImageResource(likeImaje)
//                    like.setOnClickListener {
//                        viewModel.likeById(post.id)
//                    }
//                    shares.setOnClickListener {
//                        viewModel.sharesById(post.id)
//                    }
//
//                }.root
//            }.forEach {
//                binding.container.addView(it)
//            }
//        }
    // /////////////////////////////////////////////////////////////
   }
}

//            post.likedByMe = !post.likedByMe
//
//            like.setImageResource(
//                if (post.likedByMe) {
//
//                    post.allLiked++
//                    allLikedText.text = Servis.formatKM(post.allLiked)
//                    R.drawable.ic_liked_24
//
//                } else {
//                    post.allLiked--
//                    allLikedText.text = Servis.formatKM(post.allLiked)
//                    R.drawable.ic_like_24
//                }
//            )
//        }
//
//        baselineShare.setOnClickListener {
//            post.share++
//            shareText.text = Servis.formatKM(post.share)
//        }
//    }
//}
//                allLikedText.text = Servis.formatKM(post.allLiked)
//                shareText.text = Servis.formatKM(post.share)
//                eyeText.text = Servis.formatKM(post.eye)
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

