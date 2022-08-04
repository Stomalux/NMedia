package ru.netology.nmedia.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import ru.netology.nmedia.databinding.FragmentNewPostBinding
import ru.netology.nmedia.util.AndroidUtils
import ru.netology.nmedia.util.StringArg
import ru.netology.nmedia.viewmodel.PostViewModel

class NewPostFragment : Fragment() {

    companion object {


        var Bundle.textArg: String?  by StringArg //    var Bundle.textArg: String?
 //           set(value) = putString(TEXT_KEY, value)
 //           get() = getString(TEXT_KEY)
    }
    private val viewModel: PostViewModel by viewModels(
        ownerProducer = ::requireParentFragment
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentNewPostBinding.inflate(
            inflater,
            container,
            false
        )

        arguments?.textArg
            ?.let(binding.content::setText)

        binding.content.requestFocus()

        binding.save.setOnClickListener {
            viewModel.changeContent(binding.content.text.toString())
            viewModel.save()
            AndroidUtils.hideKeyboard(requireView())
            findNavController().navigateUp()
        }


//        binding.save.setOnClickListener {
//            val intent = Intent()
//            if (TextUtils.isEmpty(binding.content.text)) {
//                activity?.setResult(Activity.RESULT_CANCELED, intent)
//            } else {
//                val content: String = binding.content.text.toString()
//                intent.putExtra(Intent.EXTRA_TEXT, content)
//                activity?.setResult(Activity.RESULT_OK, intent)
//            }
//            findNavController().navigateUp()
//        }
        return binding.root
    }
}






//В демонстрации так:
    //  var Bundle.textArg: String?      by StringArg
//        object StringArg: ReadWriteProperty<Bundle, String?> {
//
//            override fun setValue(thisRef: Bundle, property: KProperty<*>, value: String?) {
//                thisRef.putString(property.name, value)
//            }
//
//            override fun getValue(thisRef: Bundle, property: KProperty<*>): String? =
//                thisRef.getString(property.name)
//        }
//


 //   }











//        val binding = FragmentNewPostBinding.inflate((layoutInflater))
//        setContentView(binding.root) //   setContentView(R.layout.activity_new_post)


//        val contents = intent.getStringExtra(Intent.EXTRA_TEXT)
//        binding.content.setText(contents)


          //  if (binding.content.text.isNullOrBlank()) {
//                Toast.makeText(
//                    this,
//                    getString(R.string.empty_post_error),
//                    Toast.LENGTH_SHORT
//                ).show()
//
//                setResult(
//                    Activity.RESULT_CANCELED,
//                    intent
//                )   /////////////////////////////////////////////// setResult(RESULT_CANCELED)
//
//            } else {
//                val result = Intent().putExtra(Intent.EXTRA_TEXT, binding.content.text.toString())
//           setResult(RESULT_OK, result)
                ////////////////////////////////////////
//                val content = binding.content.text.toString()
//                intent.putExtra(Intent.EXTRA_TEXT, content)
//                setResult(Activity.RESULT_OK, intent)
//
//                /////////////////////////////////////////////////////////////
//
//
//            }
//            finish()
//        }
//
//
//
//
//
//        return binding.root
//    }
//}

//class NewPostFragment : AppCompatActivity() {
//
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
 //       val binding = FragmentNewPostBinding.inflate((layoutInflater))
//        setContentView(binding.root) //   setContentView(R.layout.activity_new_post)
//        binding.content.requestFocus()
//
//        val contents = intent.getStringExtra(Intent.EXTRA_TEXT)
//        binding.content.setText(contents)
//
//        binding.save.setOnClickListener {
//            val intent = Intent()
//            if (binding.content.text.isNullOrBlank()) {
//                Toast.makeText(
//                    this,
//                    getString(R.string.empty_post_error),
//                    Toast.LENGTH_SHORT
//                ).show()
//
//                setResult(
//                    Activity.RESULT_CANCELED,
//                    intent
//                )   /////////////////////////////////////////////// setResult(RESULT_CANCELED)
//
//            } else {
////                val result = Intent().putExtra(Intent.EXTRA_TEXT, binding.content.text.toString())
////           setResult(RESULT_OK, result)
//                ////////////////////////////////////////
//                val content = binding.content.text.toString()
//                intent.putExtra(Intent.EXTRA_TEXT, content)
//                setResult(Activity.RESULT_OK, intent)
//
//                /////////////////////////////////////////////////////////////
//
//
//            }
//            finish()
//        }
//    }
//}