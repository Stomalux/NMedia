package ru.netology.nmedia.adapter

import android.nfc.NfcAdapter
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ru.netology.nmedia.Post
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.CardPostBinding
import kotlin.math.round

typealias OnAllListener = (post: Post) -> Unit

class PostsAdapter(
    val onClickListener: OnAllListener,
    val onShareListener: OnAllListener,
    val onRemovedListener: OnAllListener) :
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
            onRemovedListener = onRemovedListener)
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
            likeCount.text = Servis.formatKM(post.likes)
            shareText.text = Servis.formatKM(post.share)
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

object Servis {

    fun formatKM (number: Int): String{

        when (number){
            in 0..999 -> return number.toString()
            in 1_000.. 1_099 -> return "${ round(number.toDouble() / 1_000).toInt()} K"
            in 1_100.. 9_999 -> return "${Math.round((number.toDouble() / 1_000)*10.0)/10.0} K"
            in 10_000.. 999_999 -> return "${round(number.toDouble() / 1_000).toInt()} K"
            else -> return  "${round(number.toDouble() / 1_000_000).toInt()} M"

        }
    }
}
class PostDiffCallback : DiffUtil.ItemCallback<Post>() {
    override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem == newItem
    }
}