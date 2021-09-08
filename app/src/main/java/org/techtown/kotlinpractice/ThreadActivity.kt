package org.techtown.kotlinpractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_thread.*

class ThreadActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thread)

        //Thread생성후 버튼을 눌렀을 때 실행시키는방식1
        val runnable : Runnable = object : Runnable{
            override fun run() {
                Log.d("TAG", "thread-1 "+"Thread is made")
            }
        }
        val thread = Thread(runnable)

        button2.setOnClickListener {
            thread.start()
        }

        //Thread생성후 바로 실행시키는법2
        Thread(object : Runnable{
            override fun run() {
                Log.d("TAG", "thread-2 : "+"Thread is made")
                button2.setBackgroundColor(getColor(R.color.black))
            }
        }).start()

        //Thread생성후 바로 실행시키는법3 람다방식
        Thread(Runnable {
            Thread.sleep(2000) //2초후에 Thread를 실행(즉,2초동안 잠재운다)
            Log.d("TAG", "thread-3 : "+"Thread is made")
            runOnUiThread { //UI쓰레드(메인쓰레드)위에서 작동한다는 말
                button3.setBackgroundColor(getColor(R.color.purple_200))
            }
        }).start()

    }
}