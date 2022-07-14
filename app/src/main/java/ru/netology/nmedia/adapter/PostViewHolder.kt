package ru.netology.nmedia.adapter
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.CardPostBinding
import ru.netology.nmedia.dto.Post
import kotlin.math.round


class PostViewHolder(
    private val binding: CardPostBinding,
    private val onInteractionListener: OnInteractionListener,
) : RecyclerView.ViewHolder(binding.root) {


    fun bind(post: Post) {
        binding.apply {
            author.text = post.author
            published.text = post.published
            content.text = post.content

            likeCount.text = Servis.formatKM(post.likes)
            shareText.text = Servis.formatKM(post.share)
            eyeText.text = post.eye.toString()

            like.setImageResource(
                if (post.likedByMe) R.drawable.ic_liked_24 else R.drawable.ic_like_24
            )

            like.setOnClickListener {
                onInteractionListener.onLike(post)
            }

            shares.setOnClickListener {
                onInteractionListener.onShare(post)
            }

            menu.setOnClickListener {
                PopupMenu(it.context, it).apply {
                    inflate(R.menu.post_menu)
                    setOnMenuItemClickListener { item ->
                        when (item.itemId) {
                            R.id.remove -> {
                                onInteractionListener.onRemove(post)
                                true
                            }
                            R.id.edit -> {
                                onInteractionListener.onEdit(post)
                                true
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

    fun formatKM(number: Int): String {

        when (number) {
            in 0..999 -> return number.toString()
            in 1_000..1_099 -> return "${round(number.toDouble() / 1_000).toInt()} K"
            in 1_100..9_999 -> return "${Math.round((number.toDouble() / 1_000) * 10.0) / 10.0} K"
            in 10_000..999_999 -> return "${round(number.toDouble() / 1_000).toInt()} K"
            else -> return "${round(number.toDouble() / 1_000_000).toInt()} M"

        }
    }
}