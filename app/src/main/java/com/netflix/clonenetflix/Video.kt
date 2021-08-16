package com.netflix.clonenetflix

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.MediaController
import android.widget.VideoView

class Video : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video)

        var videoView: VideoView = findViewById(R.id.video)

        var video = Uri.parse("https://firebasestorage.googleapis.com/v0/b/netflix-clone-b0013.appspot.com/o/video%2FTHE%20WITCHER%20_%20TRAILER%20FINAL%20_%20NETFLIX.mp4?alt=media&token=eda5cf24-3a3c-4881-83e5-42eb37ad3bb8")

        var decordView = window.decorView
        var opcoes = View.SYSTEM_UI_FLAG_FULLSCREEN
        decordView.systemUiVisibility = opcoes

        videoView.setMediaController(MediaController(this))
        videoView.setVideoURI(video)
        videoView.requestFocus()




    }
}