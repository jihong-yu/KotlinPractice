package org.techtown.kotlinpractice

import android.app.Activity
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import kotlinx.android.synthetic.main.activity_melon.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class MelonActivity : AppCompatActivity() {

    var mediaPlayer : MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_melon)

        (application as MasterApplication).service.getSongList().enqueue(
            object : Callback<ArrayList<Song>>{
                override fun onResponse(
                    call: Call<ArrayList<Song>>,
                    response: Response<ArrayList<Song>>
                ) {
                    if (response.isSuccessful){
                        val songList = response.body()
                        val adapter = MelonAdapter(songList!!, LayoutInflater.from(
                            this@MelonActivity),Glide.with(this@MelonActivity),
                        this@MelonActivity)
                        
                        song_list.adapter = adapter //아답터 등록
                    }
                }

                override fun onFailure(call: Call<ArrayList<Song>>, t: Throwable) {

                }
            })

    }

    override fun onPause() {
        super.onPause()
        mediaPlayer?.stop()
    }
    inner class MelonAdapter(
        var songList : ArrayList<Song>,
        val inflater: LayoutInflater,
        val glide: RequestManager,
        val activity: Activity
    ): RecyclerView.Adapter<MelonAdapter.ViewHolder>(){

        inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
            val title : TextView
            val thumbnail : ImageView
            val play : ImageView
            val stop : ImageView

            init {
                title = itemView.findViewById(R.id.song_title)
                thumbnail = itemView.findViewById(R.id.song_img)
                play = itemView.findViewById(R.id.song_play)
                stop = itemView.findViewById(R.id.song_stop)

                play.setOnClickListener {
                    val position : Int = adapterPosition
                    val path = songList[position].song

                    try {
                        mediaPlayer?.stop() //플레이어을 멈춘다.
                        mediaPlayer?.release() //미디어 플레이어가 가지고 있던 영상이나 음악 리소스를 지움
                        mediaPlayer = null
                        mediaPlayer = MediaPlayer.create(this@MelonActivity, Uri.parse(path)) //미디어플레이어를 생성
                        mediaPlayer?.start() //미디어플레이어 시작
                    } catch (e:Exception){
                        e.printStackTrace()
                    }

                }

                stop.setOnClickListener {
                    if (mediaPlayer != null) {
                        mediaPlayer?.pause()
                    }
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = inflater.inflate(R.layout.song_item_view,parent,false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.title.setText(songList[position].title)
            glide.load(songList[position].thumbnail).into(holder.thumbnail)
        }

        override fun getItemCount(): Int {
            return songList.size
        }
    }
}

