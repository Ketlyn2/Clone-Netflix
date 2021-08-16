package com.netflix.clonenetflix

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import com.squareup.picasso.Picasso

class Episodios : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_episodios)

        var imagemVideo: ImageView = findViewById(R.id.imagemVideo)
        var playVideo: ImageView = findViewById(R.id.video)

        var imagemDoVideo = Uri.parse("https://firebasestorage.googleapis.com/v0/b/netflix-clone-b0013.appspot.com/o/Imagens%2Fvideo.jpg?alt=media&token=be1aeac5-103b-48b4-a4d4-24100db99733")
        Picasso.get().load(imagemDoVideo).placeholder(R.drawable.gif).into(imagemVideo)

        playVideo.setOnClickListener {

            var intent = Intent(this, Video::class.java)
            startActivity(intent)

        }

    }
}