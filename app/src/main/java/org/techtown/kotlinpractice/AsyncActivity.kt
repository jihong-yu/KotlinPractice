package org.techtown.kotlinpractice

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_async.*
import org.w3c.dom.Text
import java.lang.Exception

class AsyncActivity : AppCompatActivity() {
    var task : BackgroundAsyncTask ? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_async)


        start.setOnClickListener {
            task = BackgroundAsyncTask(progressbar,ment)
            task?.execute()
        }
        stop.setOnClickListener {
            task?.cancel(true)
        }
    }

    override fun onPause() {
        task?.cancel(true)
        super.onPause()
    }
}


class BackgroundAsyncTask(
    val progressbar : ProgressBar,
    val progressText : TextView
) : AsyncTask<Int,Int,Int>() {
    // params -> doInBackground 에서 사용할 타입
    // progress -> onProgressUpdate에서 사용할 타입
    // result -> onPostExecute 에서 사용할 타입

    var percent = 0
    override fun onPreExecute() { //작업실행전
        percent = 0
        progressbar.setProgress(percent)
    }

    override fun doInBackground(vararg p0: Int?): Int { //vararg -> 인자가 여려개 올수 있음
        while (isCancelled() == false){
            percent += 1
            if(percent > 100){
                break
            }else {
                publishProgress(percent) //percent값을 메인 쓰레드로 가지고 들어감(밑에 values 값으로 들어감)
            }
            try{
                Thread.sleep(100) //작업속도가 너무 빠르기 때문에 속도를 일부러 늦춘다.
            } catch (e:Exception){
                e.printStackTrace()
            }
        }
        return percent
    }

    override fun onProgressUpdate(vararg values: Int?) {
        progressbar.setProgress(values[0] ?: 0)
        progressText.setText("퍼센트: " + values[0])

    }

    override fun onPostExecute(result: Int?) {
        progressText.setText("작업이 완료 되었습니다.")
    }

    override fun onCancelled() { //작업 중 취소 버튼이 눌렸을 때
        progressbar.setProgress(0)
        progressText.setText("작업이 취소되었습니다.")
    }
}