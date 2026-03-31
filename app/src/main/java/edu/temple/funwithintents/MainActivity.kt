package edu.temple.funwithintents


import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // This view contains the text to share
        val editText = findViewById<EditText>(R.id.editTextText)

        // When the user clicks this button, share the text if not empty
        findViewById<ImageButton>(R.id.shareImageButton).setOnClickListener {
            val textToShare = editText.text.toString().trim()

            if (textToShare.isNotEmpty()) {
                AlertDialog.Builder(this).apply {
                    setTitle("Share")
                    setMessage("Do you want to share this text with a contact?")
                    setPositiveButton("Yes") { _, _ ->
                        val smsIntent = Intent(Intent.ACTION_SENDTO).apply {
                            data = android.net.Uri.parse("smsto:") // This opens SMS app
                            putExtra("sms_body", textToShare)     // Pre-fill message
                        }
                        startActivity(smsIntent)
                    }
                    setNegativeButton("Cancel") { dialog, _ -> dialog.dismiss() }
                    show()
                }
            }
        }
    }
}