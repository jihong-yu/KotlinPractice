package org.techtown.kotlinpractice

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_outstagram_upload.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class OutstagramUploadActivity : AppCompatActivity() {

    var filePath: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_outstagram_upload)

        //사진 선택 버튼 리스너
        view_pictures.setOnClickListener {
            getPicture()
        }

        //업로드 버튼 리스너
        upload_post.setOnClickListener {
            if (filePath == "") {
                Toast.makeText(this@OutstagramUploadActivity, "이미지를 선택해 주세요", Toast.LENGTH_SHORT)
                    .show()
            } else {
                uploadPost()
            }
        }

        all_list.setOnClickListener { startActivity(Intent(this,OutStagramPostListActivity::class.java)) }
        my_list.setOnClickListener { startActivity(Intent(this,OutStagramMyPostListActivity::class.java)) }
        user_info.setOnClickListener { startActivity(Intent(this,OutStargramUserInfo::class.java)) }
    }

    fun getPicture() {
        //휴대폰 내부의 사진을 가져오는 인텐트 설정 + 권한 설정(<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />)
        val intent = Intent(Intent.ACTION_PICK)
        intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        intent.setType("image/*")
        startActivityForResult(intent, 1000)
    }

    //위의 암묵적 인텐트에 대한 결과를 반환해주는 함수
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1000) {
            val uri = data?.data!! //이미지 파일의 상대경로를 제공

            filePath = getImageFilePath(uri)
            Log.d("TAG", "getImageFilePath: " + filePath)
        }
    }

    //실제 이미지 파일의 절대 경로(full path)를 얻는 함수
    fun getImageFilePath(contentUri: Uri): String {
        var columnIndex = 0 //인덱스를 위한 변수
        val projection = arrayOf(MediaStore.Images.Media.DATA) //어떤것을 걸러내기 위한 틀
        val cursor = contentResolver.query(contentUri, projection, null, null, null)
        // cursor -> 리스트의 원소를 가리키는 화살표라고 생각 contentResolver -> content를 관리한다. query -> 검색
        if (cursor!!.moveToFirst()) { //화살표를 리스트의 첫번째로 이동
            columnIndex = cursor.getColumnIndexOrThrow((MediaStore.Images.Media.DATA)) //해당 커서가 가리키고 있는 리스트의 첫번째 인덱스를 얻는다
        }
        return cursor.getString(columnIndex) //해당 커서가 가리키는 인덱스의 주소 String값 반환
    }

    fun uploadPost() {
        val file = File(filePath) //파일의 주소를 이용해서 실제로 찾은 파일을 대입
        val fileRequestBody = RequestBody.create(MediaType.parse("image/*"), file)
        //찾은 파일을 이용하여 RequestBody(요청)를 만드는데 미디어 타입을 파싱,설정(이미지타입으로)
        val part = MultipartBody.Part.createFormData("image", file.name, fileRequestBody)
        //이미지를 서버에 보낼때 10mb가 있으면 쪼개서 보내기 때문에 MultipartBody에다가 서버에 보낼 이름은 image로 그리고 파일이름과 해당 파일요청body를 인자로보낸다.
        val content = RequestBody.create(MediaType.parse("text/plain"), getContent())
        //content_input 의 입력 텍스트를 text/plain형식으로 파싱
        (application as MasterApplication).service.uploadPost(part, content)
            .enqueue(object : Callback<Post> {
                override fun onResponse(call: Call<Post>, response: Response<Post>) {
                    if (response.isSuccessful) {
                        finish()
                        startActivity(Intent(this@OutstagramUploadActivity,OutStagramMyPostListActivity::class.java))
                    } else {
                        Toast.makeText(this@OutstagramUploadActivity, "응답에 실패 하였습니다.", Toast.LENGTH_SHORT).show()
                    }

                }

                override fun onFailure(call: Call<Post>, t: Throwable) {
                    //서버를 닫아놔서 통신에 실패도 그냥 넘어가는 걸로 설정!
                    finish()
                    startActivity(Intent(this@OutstagramUploadActivity,OutStagramMyPostListActivity::class.java))
                    Toast.makeText(this@OutstagramUploadActivity, "통신에 실패 하였습니다.", Toast.LENGTH_SHORT).show()
                }
            })
    }

    //이미지를 업로드 하기 위해 설명을 입력하는 content_input의 텍스트를 얻어오는 함수
    fun getContent(): String {
        return content_input.text.toString()
    }
}