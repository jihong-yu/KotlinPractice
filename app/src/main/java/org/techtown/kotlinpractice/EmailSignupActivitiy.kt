package org.techtown.kotlinpractice

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EmailSignupActivitiy : AppCompatActivity() {

    //뷰들 최상단에 선언
    lateinit var usernameView: EditText
    lateinit var userPassword1View: EditText
    lateinit var userPassword2View: EditText
    lateinit var registerBtn: TextView
    lateinit var loginBtn: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        //만약 로그인이 되어있다면 회원가입페이지는 보여지면 안되기 때문에 바로 홈 화면으로 이동시킨다.
        if ((application as MasterApplication).checkIsLogin()) {
            finish() //해당 액티비티를 종료시킨다.
            startActivity(Intent(this, OutStagramPostListActivity::class.java))
        } else {

            setContentView(R.layout.activity_email_signup_activitiy)

            initView(this@EmailSignupActivitiy)
            setupListner(this@EmailSignupActivitiy)
        }

    }

    //뷰 그릴때 id값 가져오는 함수
    fun initView(activitiy: Activity) {
        usernameView = activitiy.findViewById(R.id.username_inputbox)
        userPassword1View = activitiy.findViewById(R.id.password1_inputbox)
        userPassword2View = activitiy.findViewById(R.id.password2_inputbox)
        registerBtn = activitiy.findViewById(R.id.register)
        loginBtn = activitiy.findViewById(R.id.login)
    }


    //뷰 클릭 리스너 등록 함수
    fun setupListner(activitiy: Activity) {
        registerBtn.setOnClickListener {
            register(activitiy)
        }

        loginBtn.setOnClickListener {
            startActivity(Intent(activitiy, LoginActivity::class.java))
        }

    }

    //가입 적용하는 레트로핏 함수 등록
    fun register(activitiy: Activity) {
        val username = getUserName()
        val password1 = getUserPassword1()
        val password2 = getUserPassword2()
        //val register = Register(username,password1,password2)

        (application as MasterApplication).service.register(username, password1, password2)
            .enqueue(object : Callback<User> {

                override fun onResponse(call: Call<User>, response: Response<User>) {
                    if (response.isSuccessful) {
                        Toast.makeText(activitiy, "가입에 성공하였습니다.", Toast.LENGTH_SHORT).show()
                        val user = response.body()
                        val token = user!!.token!!
                        saveUserToken(token, activitiy)
                        (application as MasterApplication).createRetrofit() // 로그인을 했다면 해더에 token값을 실어서 보내야 하기 때문에
                        //다시한번 호출해 준다.

                        //회원가입 후에 홈화면으로 이동
                        activitiy.startActivity(Intent(activitiy, OutStagramPostListActivity::class.java))
                    }
                }

                override fun onFailure(call: Call<User>, t: Throwable) {
                    Toast.makeText(activitiy, "가입에 실패하였습니다.", Toast.LENGTH_SHORT).show()
                }
            })
    }

    //유저 토큰을 저장하는 함수
    fun saveUserToken(token: String, activitiy: Activity) {
        val sp = activitiy.getSharedPreferences("login_sp", MODE_PRIVATE)
        val edior = sp.edit()
        edior.putString("login_sp", token)
        edior.commit()
    }


    fun getUserName(): String {
        return usernameView.text.toString()
    }

    fun getUserPassword1(): String {
        return userPassword1View.text.toString()
    }

    fun getUserPassword2(): String {
        return userPassword2View.text.toString()
    }
}