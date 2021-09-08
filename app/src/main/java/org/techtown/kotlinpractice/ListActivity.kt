package org.techtown.kotlinpractice

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_list.*

class ListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_list)
        val carList = arrayListOf<CarForList>()

        for (i in 0 until 50) {
            carList.add(CarForList("" + i + "번째 자동차", "" + i + "순위 엔진"))
        }

        val adapter = ListViewAdapter(carList, this@ListActivity)
        listView.adapter = adapter

        listView.setOnItemClickListener { adapterView, view, i, l ->
           val carName = (adapter.getItem(i) as CarForList).name //Any형이라 캐스팅 필수
           val carEngine = (adapter.getItem(i) as CarForList).engine //Any형이라 캐스팅 필수

            Toast.makeText(this,carName + " " + carEngine,Toast.LENGTH_SHORT).show()
        }
    }

}

class ListViewAdapter(
    private val carForList: ArrayList<CarForList>,
    val context: Context
) : BaseAdapter() {


    override fun getCount(): Int {
        //그리고자 하는 아이템 리스트의 전체 개수
        return carForList.size //개수를 리턴
    }

    override fun getItem(p0: Int): Any {
        //그리고자 하는 아이템 리스트의 하나(포지션에 해당하는)
        return carForList[p0]
    }

    override fun getItemId(p0: Int): Long {
        //해당 포지션의 위치에 있는 아이템뷰의 ID 설정
        return p0.toLong() //ID를 그냥 p0(Postion)으로 해주기 때문에 그대로 p0을 반환한다.
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
//        val layoutInflater = LayoutInflater.from(context)
//        val view = layoutInflater.inflate(R.layout.item_view, null)
//        var carNameTextView = view.findViewById<TextView>(R.id.car_name) //findViewById는 리소스를 많이 잡아먹음
//        var carEngineTextView = view.findViewById<TextView>(R.id.car_engine) //findViewById는 리소스를 많이 잡아먹음
//
//        carEngineTextView.setText(carForList[p0].name)
//        carNameTextView.setText(carForList[p0].engine)


        //adapter를 사용할 때 findviewById는 리소스를 많이먹기때문에 리소스의 사용량을 줄이는 방법
        val view : View
        val holder : ViewHolder
        //Log.d("TAG", "p0: "+ p0 + " p1: " + p1 + " p2: " +p2)
        if(p1 == null){
            val layoutInflater = LayoutInflater.from(context)
            view = layoutInflater.inflate(R.layout.item_view,null)
            holder = ViewHolder()
            holder.carName = view.findViewById(R.id.car_name)
            holder.carEngine = view.findViewById(R.id.car_engine)

            view.tag = holder
            Log.d("TAG", "getView: " + view)
        }else{
            Log.d("TAG", "p1: " + p1)
            holder = p1.tag as ViewHolder
            view = p1

        }
        holder.carName?.setText(carForList[p0].name)
        holder.carEngine?.setText(carForList[p0].engine)

        return view
    }
}

class ViewHolder{
    var carName: TextView? = null
    var carEngine: TextView? = null
}