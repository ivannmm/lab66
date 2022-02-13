package com.example.lab66

import android.app.Application
import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import java.net.URL
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.Future

class ExecutorService : AppCompatActivity() {
    lateinit var imageView: ImageView
    lateinit var future: Future<*>

    private val url = "https://i.imgur.com/mv8e9fI.jpeg"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        imageView = findViewById(R.id.imageView)

        future =  (application as ExecutorUtil).executeService.submit {
            val newurl = URL(url)
            val mIcon_val = BitmapFactory.decodeStream(newurl.openConnection().getInputStream())
            imageView.post {
                imageView.setImageBitmap(mIcon_val)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        future.cancel(true)
    }

    class ExecutorUtil : Application() {
        var executeService: ExecutorService = Executors.newSingleThreadExecutor()
    }
}

