package org.techtown.kotlinpractice

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_per_mission.*

class PerMissionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_per_mission)

        ask.setOnClickListener {
            val cameraPermissionCheck = ContextCompat.checkSelfPermission(
                this@PerMissionActivity,
                android.Manifest.permission.CAMERA
            )

            if ( cameraPermissionCheck != PackageManager.PERMISSION_GRANTED){
                //현재 권한이 없는 경우
                Log.d("Permissions", "권한 생성")
                //권한 요청
                ActivityCompat.requestPermissions( //2번째인자 권한의 종류는 배열에 여러개 담아서 보낸다.
                    this, arrayOf(android.Manifest.permission.CAMERA),1000
                )
            } else{
                //권한이 이미 있는 경우
                Log.d("Permissions", "권한이 이미 있음")
            }
        }
        }

        //권한 요청에 대한 응답을 확인하는 코드
        override fun onRequestPermissionsResult(
            requestCode: Int,
            permissions: Array<out String>, 
            //out은 해당 배열에서 꺼내 읽을 수만 있고 해당 배열에 어떠한 값을 변경하거나 집어 넣을 수 없다. out은 해당 클래스와 자식클래스만 대입가능
            //in은 해당배열에 값을 수정만 할 수 있고 읽을 수 없다. in은 해당 클래스와 부모 클래스만 들어갈 수 있다.
            grantResults: IntArray
        ) {

            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
            permissions.forEach { Log.d("permission", "permission: " + it) }
            grantResults.forEach { Log.d("grantResults", "grantResults: " + it) }
            if(requestCode == 1000){
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Log.d("Permissions", "승낙: ")
            }else{
                Log.d("Permissions", "거부: ")
            }
        }
    }
}