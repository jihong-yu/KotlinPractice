package org.techtown.kotlinpractice

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        register.setOnClickListener {
            val intent = Intent(this,EmailSignupActivitiy::class.java)
            startActivity(intent)
        }

        login.setOnClickListener {
            val username = username_inputbox.text.toString()
            val password = password_inputbox.text.toString()
            (application as MasterApplication).service.login(username,password).enqueue(object : Callback<User>{
                override fun onResponse(call: Call<User>, response: Response<User>) {

                    if(response.isSuccessful){ //만약 서버에서 응답이 성공한다면
                        val user = response.body() //서버에서 토큰을 반환해준다.(그것을 user class로 변환)
                        val token = user!!.token!! //그 토큰을 변수에 저장하여
                        saveUserToken(token,this@LoginActivity) //토큰을 SharedPreference에 저장한다.
                        (application as MasterApplication).createRetrofit()

                        Toast.makeText(this@LoginActivity, "로그인 되었습니다.", Toast.LENGTH_SHORT).show()
                        
                        //로그인한 뒤에 홈화면으로이동
                        startActivity(Intent(this@LoginActivity,OutStagramPostListActivity::class.java))

                    } else{
                        Toast.makeText(this@LoginActivity, "로그인에 실패 하였습니다. 아이디와 비밀번호를 확인해주세요", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<User>, t: Throwable) {
                    Toast.makeText(this@LoginActivity, "서버와 통신에 실패 하였습니다.", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }

    fun saveUserToken(token:String,activity:Activity){
        val sp = activity.getSharedPreferences("login_sp", MODE_PRIVATE)
        val editor = sp.edit()
        editor.putString("login_sp",token)
        editor.commit()
    }
}