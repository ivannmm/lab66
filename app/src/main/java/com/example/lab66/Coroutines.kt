package com.example.lab66

import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.*
import java.net.URL

class Coroutines : AppCompatActivity() {
    private val url = "https://i.imgur.com/mv8e9fI.jpeg"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val imageView = findViewById<ImageView>(R.id.imageView)
        val url = URL(url)

        CoroutineScope(Dispatchers.IO).launch {
            val image = BitmapFactory.decodeStream(url.openStream())
            withContext(Dispatchers.Main) {
                imageView.setImageBitmap(image)
            }
        }

    }
}