package org.techtown.kotlinpractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_shared_preference.*

class SharedPreferenceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shared_preference)

        save_btn.setOnClickListener {
            //SharedPreference 에 저장하는 방법
            val sharedPreference = getSharedPreferences("sp1", MODE_PRIVATE)
            // Mode
            // - MODE_PRIVATE : 생성한 application 에서만 사용가능
            // - MODE_WORLD_READABLE : 다른 application 사용 가능 -> 읽을수만 있다.
            // - MODE_WORLD_WRITEABLE : 다른 application 사용 가능 -> 기록도 가능
            // - MODE_MULTI_PROCESS : 이미 호출되어 사용중인지 체크
            // - MODE_APPEND : 기존 preference에 신규로 추가

            val editor = sharedPreference.edit()
            editor.putString("hello", "안녕하세요")
            editor.putString("goodbye", "안녕히가세요")
            editor.commit()
        }

        load_button.setOnClickListener {
            //sharedPreference 에 값을 불러오는 방법
            val sharedPreference = getSharedPreferences("sp1", MODE_PRIVATE)
            val value1 = sharedPreference.getString("hello", "데이터 없음1") //2번째 인자는 default 값
            val value2 = sharedPreference.getString("goodbye", "데이터 없음2") //2번째 인자는 default 값
            Log.d("TAG", "value1: " + value1)
            Log.d("TAG", "value2: " + value2)
        }

        delete_btn.setOnClickListener {
            val sharedPreference = getSharedPreferences("sp1", MODE_PRIVATE)
            val editor = sharedPreference.edit()
            editor.remove("hello")
            editor.commit()
        }

        all_delete_btn.setOnClickListener {
            val sharedPreference = getSharedPreferences("sp1", MODE_PRIVATE)
            val editor = sharedPreference.edit()
            editor.clear()
            editor.commit()
        }
    }
}