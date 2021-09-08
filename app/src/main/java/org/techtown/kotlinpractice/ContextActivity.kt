package org.techtown.kotlinpractice

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class ContextActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_context)


        val context : Context = this
        val applicationContext = applicationContext //(=getApplicationContext)






    }
}