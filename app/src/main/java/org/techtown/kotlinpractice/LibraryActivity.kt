package org.techtown.kotlinpractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_library.*

class LibraryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_library)

        Glide.with(this@LibraryActivity)
            .load("https://github.com/bumptech/glide/raw/master/static/glide_logo.png")
            .into(glide)

        Glide.with(this@LibraryActivity)
            .load("https://github.com/bumptech/glide/raw/master/static/glide_logo.png").centerCrop()
            .into(glide2)
    }
}