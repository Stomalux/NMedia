package ru.netology.nmedia.activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.databinding.ActivityNewPostBinding

class NewPostActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityNewPostBinding.inflate((layoutInflater))
        setContentView(binding.root) //   setContentView(R.layout.activity_new_post)
        binding.content.requestFocus()

        val contents = intent.getStringExtra(Intent.EXTRA_TEXT)
        binding.content.setText(contents)

        binding.save.setOnClickListener {
            val intent = Intent()
            if (binding.content.text.isNullOrBlank()) {
                Toast.makeText(
                    this,
                    getString(R.string.empty_post_error),
                    Toast.LENGTH_SHORT
                ).show()

                setResult(Activity.RESULT_CANCELED, intent)   /////////////////////////////////////////////// setResult(RESULT_CANCELED)

            } else {
//                val result = Intent().putExtra(Intent.EXTRA_TEXT, binding.content.text.toString())
//           setResult(RESULT_OK, result)
            ////////////////////////////////////////
                val content = binding.content.text.toString()
                intent.putExtra(Intent.EXTRA_TEXT, content)
                setResult(Activity.RESULT_OK, intent)

            /////////////////////////////////////////////////////////////


            }
            finish()
        }
    }
}