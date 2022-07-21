package ru.netology.nmedia.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.ActivityIntentHandlerBinding

class IntentHandlerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityIntentHandlerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        intent?.let {
            if (it.action != Intent.ACTION_SEND) {
                return@let
            }

            val text = it.getStringExtra(Intent.EXTRA_TEXT)
            if (text.isNullOrBlank()) {
                Snackbar.make(binding.root, R.string.empty_post_error,
                    BaseTransientBottomBar.LENGTH_INDEFINITE
                )
                    .setAction(android.R.string.ok) {
                        finish()
                    }
                    .show()
                return@let
            }
            val contents = intent.getStringExtra(Intent.EXTRA_TEXT)
            binding.content.setText(contents)
            // TODO: handle text

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
}