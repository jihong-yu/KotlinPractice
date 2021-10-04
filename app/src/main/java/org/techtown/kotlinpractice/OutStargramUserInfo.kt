package org.techtown.kotlinpractice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_out_stargram_user_info.*

class OutStargramUserInfo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_out_stargram_user_info)

        //버튼 클릭시 해당 액티비티로 이동하는 리스너
        my_list.setOnClickListener { startActivity(Intent(this,OutStagramMyPostListActivity::class.java)) }
        all_list.setOnClickListener { startActivity(Intent(this,OutStagramPostListActivity::class.java)) }
        upload.setOnClickListener { startActivity(Intent(this,OutstagramUploadActivity::class.java)) }
    
        //로그아웃 리스너 구현
        logout.setOnClickListener {
            val sp = getSharedPreferences("login_sp", MODE_PRIVATE)
            val editor = sp.edit()
            editor.putString("login_sp","null") //null값을 주면 checkIsLogin() false를반환하게됨
            editor.commit()
            (application as MasterApplication).createRetrofit()
            finish() // 해당 액티비티 종료
            startActivity(Intent(this,LoginActivity::class.java)) //로그인 액티비티로 이동
        }
    }


}