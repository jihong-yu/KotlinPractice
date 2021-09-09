package org.techtown.kotlinpractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitLibraryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrofit_library)

        val retrofit = Retrofit.Builder()
            .baseUrl("http://mellowcode.org")  // "http://mellowcode.org/json/students" , "http://mellowcode.org/text/students"
            .addConverterFactory(GsonConverterFactory.create()) //원하는 데이터 타입으로 바꾸는것을 Gson으로 처리하였는데 이것을 converFactory에만 등록하면 알아서 해준다.
            .build()

        val service = retrofit.create(RetrofitService::class.java)

        //GET 요청
        service.getStudentsList().enqueue(object : Callback<ArrayList<PersonFromServer>>{  //대기줄에 올려놓는다는 의미
            override fun onResponse( //성공했을때 호출
                call: Call<ArrayList<PersonFromServer>>,
                response: Response<ArrayList<PersonFromServer>>
            ) {
                if (response.isSuccessful){
                    val personList = response.body()
                    Log.d("TAG", "response.body(): "+ personList?.get(0)?.age)
                    val code = response.code() //응답코드
                    Log.d("TAG", "response.code(): "+ code)

                    val error = response.errorBody() //200번대만 여기 들어오는데 200번대에도 인증 오류과 같은 에러가날수있기 때문에 여기서 처리를 해줘야한다.
                    val header = response.headers() //header에는 인증정보나 요약본같은것들이 담겨있음
                    Log.d("TAG", "response.errorBody(): "+ error)
                    Log.d("TAG", "response.headers(): "+ header)


                }
            }

            override fun onFailure(call: Call<ArrayList<PersonFromServer>>, t: Throwable) { //실패했을때 호출

                call.isCanceled //cancel된건지?
                call.isExecuted //실행은 된건지?
                call.cancel() // 실패해도 레트로핏은 몇번더 리트라이를 하는데 실패했을 경우 바로 call을 취소할 수 있다.

                Log.d("TAG", "ERROR") //사실상 여기서는 거의 에러메세지를 보여주는 정도만 사용한다.


            }
        })

        //Post 요청
//        val params = HashMap<String,Any>()
//        params.put("name","김개똥")
//        params.put("age",20)
//        params.put("intro","안녕하세요")
//        service.createStudent(params).enqueue(object : Callback<PersonFromServer>{
//            override fun onResponse(
//                call: Call<PersonFromServer>,
//                response: Response<PersonFromServer>
//            ) {
//                if (response.isSuccessful) {
//                    val person = response.body()
//                    Log.d("TAG2", "name: " + person?.name)
//                }
//            }
//
//            override fun onFailure(call: Call<PersonFromServer>, t: Throwable) {
//                Log.d("TAG2", "ERROR")
//            }
//        })

        //Post요청2
        val person = PersonFromServer(name = "안녕하세요" , age = 30 , intro = "반갑습니다.지광")
        service.createStudentEasy(person).enqueue(object : Callback<PersonFromServer>{
            override fun onResponse(
                call: Call<PersonFromServer>,
                response: Response<PersonFromServer>
            ) {
                if(response.isSuccessful){
                    val person = response.body()
                    Log.d("TAG2", "name: " + person?.name)
                }
            }

            override fun onFailure(call: Call<PersonFromServer>, t: Throwable) {

            }
        })
    }
}