package org.techtown.kotlinpractice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import kotlinx.android.synthetic.main.activity_out_stagram_my_post_list.*
import kotlinx.android.synthetic.main.activity_out_stagram_my_post_list.all_list
import kotlinx.android.synthetic.main.activity_out_stagram_my_post_list.my_list
import kotlinx.android.synthetic.main.activity_out_stagram_my_post_list.user_info
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OutStagramMyPostListActivity : AppCompatActivity() {

    lateinit var MyPostRecyclerView : RecyclerView
    lateinit var glide: RequestManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_out_stagram_my_post_list)

        MyPostRecyclerView = mypost_recyclerview
        glide = Glide.with(this@OutStagramMyPostListActivity)

        createList()

        all_list.setOnClickListener { startActivity(Intent(this,OutStagramPostListActivity::class.java)) }
        upload.setOnClickListener { startActivity(Intent(this,OutstagramUploadActivity::class.java)) }
        user_info.setOnClickListener { startActivity(Intent(this,OutStargramUserInfo::class.java)) }
    }
    
    //본인이 올린 이미지들 불러오는 함수
    fun createList() {
        (application as MasterApplication).service.getUserPostList()
            .enqueue(object : Callback<ArrayList<Post>> {
                override fun onResponse(call: Call<ArrayList<Post>>, response: Response<ArrayList<Post>>) {
                    if (response.isSuccessful) {
                        val myPostList = response.body()
                        val adapter = MyPostAdapter(myPostList!!, LayoutInflater.from(this@OutStagramMyPostListActivity),glide)
                        mypost_recyclerview.adapter = adapter
                        mypost_recyclerview.layoutManager = LinearLayoutManager(this@OutStagramMyPostListActivity)
                    }
                }
                override fun onFailure(call: Call<ArrayList<Post>>, t: Throwable) {

                }
            })
    }
}

//아답터 설정
class MyPostAdapter(
    var postList: ArrayList<Post>,
    val inflater: LayoutInflater,
    val glide: RequestManager
) : RecyclerView.Adapter<MyPostAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val postOwner: TextView
        val postImage: ImageView
        val postContent: TextView

        init {
            postOwner = itemView.findViewById(R.id.post_owner)
            postImage = itemView.findViewById(R.id.post_img)
            postContent = itemView.findViewById(R.id.post_content)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflater.inflate(R.layout.outstagram_item_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.postOwner.setText(postList[position].owner)
        holder.postContent.setText(postList[position].content)
        glide.load(postList[position].image).into(holder.postImage)
    }

    override fun getItemCount(): Int {
        return postList.size
    }
}