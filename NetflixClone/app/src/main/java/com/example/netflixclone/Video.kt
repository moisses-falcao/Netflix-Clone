package com.example.netflixclone

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.MediaController
import com.example.netflixclone.databinding.ActivityVideoBinding

class Video : AppCompatActivity() {

    private lateinit var binding: ActivityVideoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityVideoBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        supportActionBar!!.hide()

        val videoURL = Uri.parse("https://firebasestorage.googleapis.com/v0/b/netflix-clone-21ef4.appspot.com/o/THE%20WITCHER%20_%20TRAILER%20FINAL%20_%20NETFLIX.mp4?alt=media&token=c630a181-4947-4965-bc8e-fdcffc72c6af")

        val video = binding.video
        video.setMediaController(MediaController(this))
        video.setVideoURI(videoURL)
        video.requestFocus()
        video.start()

    }


}