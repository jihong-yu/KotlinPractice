package org.techtown.kotlinpractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_resource.*

class Resource : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resource)

        //1
        val ment = resources.getString(R.string.hello2)
        Log.d("TAG", "onCreate: " + ment)

        //2
        val ment2 = getString(R.string.hello2)
        Log.d("TAG", "onCreate: " + ment2)


        val color = getColor(R.color.textview_color)
        Log.d("TAG", "onCreate: " + color)

        button.setBackgroundColor(resources.getColor(R.color.textview_color))
    }

}