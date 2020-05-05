package com.example.mvvm_api.view.video

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.MediaController
import com.example.mvvm_api.R
import kotlinx.android.synthetic.main.activity_play.*

class PlayActivity : AppCompatActivity() {

    var url : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play)
        init()
        playVideo()
    }

    fun init(){
        intent.extras?.let {
            url = it.getString("url").toString()
            tvUrl.text = url
        }
    }

    fun playVideo(){
        videoView.setVideoPath(url)
        val mediaController : MediaController = MediaController(this)
        videoView.setMediaController(mediaController)
        videoView.start()
    }
}
