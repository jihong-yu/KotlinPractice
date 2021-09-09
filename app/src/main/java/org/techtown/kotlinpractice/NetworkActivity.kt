package org.techtown.kotlinpractice

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class NetworkActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_network)

        NetworkTask().execute()

    }
}

class NetworkTask() : AsyncTask<Any?,Any?,Any?>(){

    override fun doInBackground(vararg p0: Any?): Any? {
        val urlString = "http://mellowcode.org/json/students/"
        val url = URL(urlString) //URL 파싱
        val connection = url.openConnection() as HttpURLConnection //요청을 보냄

        connection.requestMethod = "GET"
        connection.setRequestProperty("Content-Type","application/json")

        var buffer = ""
        if(connection.responseCode == HttpURLConnection.HTTP_OK){
            Log.d("TAG", "inputStream: "+ connection.inputStream)
            val reader = BufferedReader( //뭉태기로' 읽는것(1,2,3 ->대입 4,5,6 대입 ...)
                InputStreamReader( //inputStream을 읽는것
                    connection.inputStream,
                    "UTF-8"
                )
            )
            buffer = reader.readLine()
            Log.d("TAG", "buffer: " + buffer)
        }

        val temp = buffer.get(7)
        return null
    }
}