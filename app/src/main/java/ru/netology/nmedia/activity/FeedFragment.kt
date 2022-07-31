// 19-07-22 21-41

package ru.netology.nmedia.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.activity.result.launch
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import ru.netology.nmedia.R
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.adapter.OnInteractionListener
import ru.netology.nmedia.adapter.PostsAdapter
//import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.databinding.FragmentFeedBinding

import ru.netology.nmedia.viewmodel.PostViewModel


class FeedFragment : Fragment() {
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    val viewModel by viewModels<PostViewModel>()
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    val newPostLauncher = registerForActivityResult(NewPostActivityContract()) { text ->
        text ?: return@registerForActivityResult
        viewModel.changeContent(text.toString())
        viewModel.save()
    }

    val editPostLauncher = registerForActivityResult(EditPostActivityContract()) { text ->
        text?.let {                                         //: return@registerForActivityResult
            viewModel.changeContent(text.toString())
            //  viewModel.changeContent(it)
            viewModel.save()
        }
    }
    val intentHandlerLauncher = registerForActivityResult(IntentHadlerActivityContract()) { text ->
        text ?: return@registerForActivityResult
        viewModel.changeContent(text.toString())
        viewModel.save()
    }
 ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentFeedBinding.inflate(
            inflater,
            container,
            false
        )
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        val adapter = PostsAdapter(object : OnInteractionListener {


            override fun  onHandler(post: Post){
                viewModel.edit(post)
                intentHandlerLauncher.launch(post.content)


            }
            override fun onEdit(post: Post) {
                viewModel.edit(post)
                editPostLauncher.launch(post.content)
            }

            override fun onLike(post: Post) {
                viewModel.likeById(post.id)
            }

            override fun onShare(post: Post) {
                viewModel.sharesById(post.id)
                ////////////////////////////////////////////////////////////////////////
                val intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, post.content)
                    type = "text/plain"
                }

                val shareIntent =
                    Intent.createChooser(intent, getString(R.string.chooser_share_post))
                startActivity(shareIntent)
                /////////////////////////////////////////////////////////////////////////////
            }

            override fun onRemove(post: Post) {
                viewModel.removeById(post.id)
            }

            override fun onVideo(post: Post) {
                val intentVideo = Intent(Intent.ACTION_VIEW, Uri.parse(post.video))
                startActivity(intentVideo)
            }
        })
        binding.list.adapter = adapter
        viewModel.data.observe(viewLifecycleOwner)
        { posts ->
            val newPost = adapter.itemCount < posts.size
            adapter.submitList(posts) {
                if (newPost) binding.list.smoothScrollToPosition(0)
            }
        }

        binding.create.setOnClickListener {
            newPostLauncher.launch()
        }

//////////////////////////////////////////////////////////////////////////////////////////////////////////////
        return binding.root
    }
}










