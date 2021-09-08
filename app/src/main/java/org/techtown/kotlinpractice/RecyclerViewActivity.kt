package org.techtown.kotlinpractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_recycler_view.*

class RecyclerViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)

        val carList = ArrayList<CarForList>()
        for (i in 0 until 50) {
            carList.add(CarForList("" + i + " 번째 자동차", "" + i + "순위 엔진"))
        }
        val adapter = RecyclerViewAdapter(carList,this@RecyclerViewActivity.layoutInflater)
        recycler_view.adapter = adapter
        recycler_view.layoutManager = LinearLayoutManager(this@RecyclerViewActivity)
        //recycler_view.layoutManager = GridLayoutManager(this@RecyclerViewActivity,2)
    }
}

class RecyclerViewAdapter(
    val itemList: ArrayList<CarForList>,
    val inflater: LayoutInflater
) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) { //inner를 설정하게 될 경우 아우터클래스 변수에 접근가능
        val carName: TextView
        val carEngine: TextView

        init {
            carName = itemView.findViewById(R.id.car_name)
            carEngine = itemView.findViewById(R.id.car_engine)
            itemView.setOnClickListener {
                val position = adapterPosition //onBindViewHolder에서의 포지션 값을 들고옴
                val engineName = itemList[position].engine
                Log.d("TAG", "engine: "+engineName)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Log.d("TAG", "onCreateViewHolder: "+ parent)
        val view : View = inflater.inflate(R.layout.item_view,parent,false) //item하나가 들어갈 view를 만들어줌
        return ViewHolder(view) //그 하나를 ViewHolder에 넣어줌(위에 내부 클래스에 인자로 전달)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) { //TAG를 달아주는 기능
        holder.carName.setText(itemList[position].name) //위에서 만들어진 View에다가 글씨를 채워줌
        holder.carEngine.setText(itemList[position].engine)
    }

}