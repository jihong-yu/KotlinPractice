package org.techtown.kotlinpractice

import android.content.Context
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_network.*
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class NetworkActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_network)

        NetworkTask(
            recycler_person,this.layoutInflater,this
        ).execute()

    }
}

class NetworkTask( //네트워크는 메인쓰레드에서 작동할 수 없기 때문에 비동기방식으로 처리해야한다.
    val recyclerView: RecyclerView,
    val inflater: LayoutInflater,
    val context: Context
) : AsyncTask<Any?, Any?, Array<PersonFromServer>?>() {

    override fun onPostExecute(result: Array<PersonFromServer>?) { //UI쓰레드에 접근이 가능하다. 여기서 뷰를 그려준다.
        val adapter = PersonAdapter(result!!,inflater)
        recyclerView.adapter = adapter
        //recyclerView.layoutManager = LinearLayoutManager(context) //layout에서도 설정이 가능하다.

    }

    override fun doInBackground(vararg p0: Any?): Array<PersonFromServer>? { //return한것이 onPostExecute로 들어감
        val urlString = "http://mellowcode.org/json/students/"
        val url = URL(urlString) //URL 파싱
        val connection = url.openConnection() as HttpURLConnection //요청을 보냄

        connection.requestMethod = "GET"
        connection.setRequestProperty("Content-Type", "application/json")

        var buffer = ""
        if (connection.responseCode == HttpURLConnection.HTTP_OK) {
            Log.d("TAG", "inputStream: " + connection.inputStream)
            val reader = BufferedReader( //뭉태기로' 읽는것(1,2,3 ->대입 4,5,6 대입 ...)
                InputStreamReader( //inputStream을 읽는것
                    connection.inputStream,
                    "UTF-8"
                )
            )
            buffer = reader.readLine()
            Log.d("TAG", "buffer: " + buffer)
        }
        //Gson -> 넘어온 Json데이터를 편리하게 미리 설정한 class형식으로 파싱해줌
        val data = Gson().fromJson(
            buffer,
            Array<PersonFromServer>::class.java
        ) //array형식으로 넘어온것은 array형식으로 받아야됨
        //val age = data[0].age
        return data //onPostExecute로 넘어감
    }
}

class PersonAdapter(
    val personList: Array<PersonFromServer>,
    val inflater: LayoutInflater
) : RecyclerView.Adapter<PersonAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView
        val age: TextView
        val intro: TextView

        init {
            name = itemView.findViewById(R.id.person_name)
            age = itemView.findViewById(R.id.person_age)
            intro = itemView.findViewById(R.id.person_ment)
        }
    }

    override fun getItemCount(): Int {
        return personList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflater.inflate(R.layout.person_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.setText(personList[position].name ?: "")
        holder.age.setText(personList[position].age.toString())
        holder.intro.setText(personList[position].intro ?: "")
    }
}