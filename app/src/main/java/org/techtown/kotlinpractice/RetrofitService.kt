package org.techtown.kotlinpractice

import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface RetrofitService { //baseUrl 뒷부분 처리

    @GET("json/students")
    fun getStudentsList(): Call<ArrayList<PersonFromServer>>

    @POST("json/students")
    fun createStudent(
        @Body params: HashMap<String, Any>
    ): Call<PersonFromServer>

    @POST("json/students")
    fun createStudentEasy(@Body person: PersonFromServer): Call<PersonFromServer>

    @POST("user/signup/")
    fun register(@Body register: Register): Call<User> //전달할때는 3개의 인자 username,password1,passoword2를 받고
    //받을 때는 2개의 인자 username,token을 받기 때문이다.(서버가 객체를 받을 경우 사용가능함)

    //서버가 객체를 받지 않을 경우 일일이 필드로 만들어 보내주어야한다.필드 어노테이션을 사용할 경우
    // @FormUrlEncoded를 추가해주어야 한다.
    @POST("user/signup/")
    @FormUrlEncoded
    fun register(
        @Field("username") username: String,
        @Field("password1") password1: String,
        @Field("password2") password2: String,
    ): Call<User>

    @POST("user/login/")
    @FormUrlEncoded
    fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ): Call<User>

    @GET("/instagram/post/list/all/")
    fun getAllPosts(): Call<ArrayList<Post>>

    @Multipart
    @POST("instagram/post/")
    fun uploadPost(
        @Part image: MultipartBody.Part,
        @Part("content") requestBody: RequestBody
    ): Call<Post>

    @GET("instagram/post/list/")
    fun getUserPostList():Call<ArrayList<Post>>

    @GET("youtube/list/")
    fun getYoutubeList():Call<ArrayList<Youtube>>
}