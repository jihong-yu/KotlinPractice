package org.techtown.kotlinpractice

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.SurfaceHolder
import kotlinx.android.synthetic.main.activity_media_player.*
import java.lang.Exception

class MediaPlayerActivity : AppCompatActivity(), SurfaceHolder.Callback {
    private lateinit var surfaceHolder: SurfaceHolder
    private lateinit var mediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_media_player)

        mediaPlayer = MediaPlayer()
        surfaceHolder = surfaceview.holder
        surfaceHolder.addCallback(this@MediaPlayerActivity)

        button_start.setOnClickListener {
            var mediaPlayer = MediaPlayer()
            mediaPlayer.setDataSource("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4")
            mediaPlayer.prepare()
            mediaPlayer.start()
        }


    }

    override fun surfaceCreated(p0: SurfaceHolder) {

        try {
            mediaPlayer.setDataSource("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4")
            mediaPlayer.setDisplay(surfaceHolder)
            mediaPlayer.prepare()
            mediaPlayer.start()

        } catch ( e: Exception) {
            e.printStackTrace()
        }


    }

    override fun surfaceChanged(p0: SurfaceHolder, p1: Int, p2: Int, p3: Int) {

    }

    override fun surfaceDestroyed(p0: SurfaceHolder) {

    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release()
    }
}