package org.techtown.kotlinpractice

import android.app.Application
import android.content.Context
import com.facebook.stetho.Stetho
import com.facebook.stetho.okhttp3.StethoInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// 액티비티 전역에서 사용할 수 있게 로그인여부를 확인하는 레트로핏 설정
// Application을 상속받게 되면 어떠한 액티비티보다 먼저 불러와 지기 때문에
// MasterApplication클래스 내부의 onCreate에서 처리하면 된다.
// 단, 매니페스트에 따로 등록을 해야됨(application태그 내부에서 android:name=".MasterApplication" 등록)
class MasterApplication : Application() {

    lateinit var service: RetrofitService

    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this@MasterApplication) // 스테토 사용을 위한 설정
        //스테토란 안드로이드 스튜디오 profiler랑 비슷한데 네트워크 통신상태를 확인하게 해주는 라이브러리이다.
        //chrome://inspect/#devices
        createRetrofit()
    }

    fun createRetrofit() {
        //Interceptor -> 원래나가려던 통신을 original변수에 가로챔(잡아둔다.) <- 해더설정을위하여
        //헤더설정
        val header = Interceptor {
            val original = it.request()

            if(checkIsLogin()){
                getUserToken()?.let { token -> //?가 붙으면 null이 아니라면 let을 실행하겠다라는의미(it이 중복되기 때문에 it을 token으로 이름변경)
                    val request = original.newBuilder()
                        .header("Authorization","token " + token) //Authorization과 토큰을 입력
                        .build()
                    //해더설정한것을 담아서 다시 내보낸다.
                    it.proceed(request)
                }

            }else { //로그인이 되어있지 않은 경우 그냥 일반적인 헤더정보를 보낸다.(헤더에 토큰을 보내지 않는다.)
                it.proceed(original)
            }

        }

        //클라이언트 설정
        val client = OkHttpClient.Builder()
            .addInterceptor(header) //헤더인터셉터 설정
            .addNetworkInterceptor(StethoInterceptor()) //모든통신을 낚아채는데 여기서는 스테토인터셉터를 넣어줌
            //스테토인터셉터가 모든통신을 낚아채서 우리에게 화면에 보여준다.
            .build()

        //레트로핏 설정
        val retrofit = Retrofit.Builder()
            .baseUrl("http://mellowcode.org/") //베이스 url설정
            .addConverterFactory(GsonConverterFactory.create()) //Json을 원하는 자바 형식으로 바꿔주는 Gson컨버터등록
            .client(client) //클라이언트 등록
            .build()

        service = retrofit.create(RetrofitService::class.java)
    }

    //로그인했는지 체크하는 함수(토큰값이 있는지 없는지를 토큰값이 있는지 없는지로 구분한다)
    fun checkIsLogin(): Boolean {
        val sp = getSharedPreferences("login_sp", MODE_PRIVATE)
        val token = sp.getString("login_sp", "null")
        if (token != "null") return true
        else return false
    }
    //SharedPreferences 에 있는 토큰을 가져오는 함수
    fun getUserToken():String?{
        val sp = getSharedPreferences("login_sp", MODE_PRIVATE)
        val token = sp.getString("login_sp","null")
        if(token =="null") return null
        else return token
    }

}