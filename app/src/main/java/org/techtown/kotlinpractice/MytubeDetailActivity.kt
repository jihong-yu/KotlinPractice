package org.techtown.kotlinpractice

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.media.ThumbnailUtils
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.service.controls.templates.ThumbnailTemplate
import android.widget.MediaController
import kotlinx.android.synthetic.main.activity_mytube_detail.*

class MytubeDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mytube_detail)


        val url = intent.getStringExtra("video_url")
        val thumbnail = intent.getStringExtra("thumbnail")



        val mediaController = MediaController((this@MytubeDetailActivity)) //안드로이드에서 비디오를 재생하기 위한 컨트롤러 생성
        video_view.setMediaController(mediaController) //미디어 컨트롤러 보이기
        video_view.setVideoPath(url) //재생할 url 입력
        video_view.requestFocus()
        video_view.start() // 자동시작
        mediaController.show()
        //mediaController.hide()

        //Exoplayer <- 더 전문적으로 영상을 다루기 위한 컨트롤러
        // drm -> digital right management -> 불법적인 다운로드를 막기위한 기능(Exoplayer에만 있음)


    }
}