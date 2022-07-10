package ru.netology.nmedia.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.netology.nmedia.Post
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.CardPostBinding
import ru.netology.nmedia.viewmodel.PostViewModel

typealias OnAllListener = (post: Post) -> Unit


class PostsAdapter(private val onAllListener: OnAllListener) :
    RecyclerView.Adapter<PostViewHolder>() {

    var list = emptyList<Post>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = CardPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding, onAllListener)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = list[position]
        holder.bind(post)
    }

    override fun getItemCount(): Int = list.size

}

class PostViewHolder(
    private val binding: CardPostBinding,
    private val onAllListener: OnAllListener
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(post: Post) {
        binding.apply {
            author.text = post.author
            published.text = post.published
            content.text = post.content

            likeCount.text = post.likes.toString()
            shareText.text = post.share.toString()



            if (post.likedByMe) {
                like.setImageResource(R.drawable.ic_liked_24)
            } else {
                like.setImageResource(R.drawable.ic_like_24)
            }
            like.setOnClickListener {
                onAllListener(post)
            }
//            shares.setOnClickListener {
//                onAllListener(post)
//            }



        }
    }
}
