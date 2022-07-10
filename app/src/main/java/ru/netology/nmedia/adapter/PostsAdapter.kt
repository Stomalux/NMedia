package ru.netology.nmedia.adapter

import android.nfc.NfcAdapter
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import ru.netology.nmedia.Post
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.CardPostBinding

typealias OnAllListener = (post: Post) -> Unit

class PostsAdapter(
    val onClickListener: OnAllListener,
    val onShareListener: OnAllListener,
    val onRemovedListener: OnAllListener

) :
    RecyclerView.Adapter<PostViewHolder>() {
    var list = emptyList<Post>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = CardPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(
            binding = binding,
            onClickListener = onClickListener,
            onShareListener = onShareListener,
            onRemovedListener = onRemovedListener
        )
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = list[position]
        holder.bind(post)
    }

    override fun getItemCount(): Int = list.size
}

class PostViewHolder(
    private val binding: CardPostBinding,
    val onClickListener: OnAllListener,
    val onShareListener: OnAllListener,
    val onRemovedListener: OnAllListener
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
                onClickListener(post)
            }
            shares.setOnClickListener {
                onShareListener(post)
            }


            menu.setOnClickListener {
                PopupMenu(it.context, it).apply {
                    inflate(R.menu.post_menu)

                    setOnMenuItemClickListener { menuItem ->
                        when (menuItem.itemId) {
                            R.id.remove -> {
                                onRemovedListener(post)
                                return@setOnMenuItemClickListener true
                            }



                            else -> false


                        }


                    }


                }.show()
            }

        }
    }
}
