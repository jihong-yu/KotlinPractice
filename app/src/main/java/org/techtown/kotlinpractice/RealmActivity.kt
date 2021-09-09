package org.techtown.kotlinpractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.realm.Realm
import io.realm.RealmConfiguration
import kotlinx.android.synthetic.main.activity_realm.*
import kotlinx.android.synthetic.main.contact_view.*
import kotlin.concurrent.thread

class RealmActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_realm)


        Realm.init(this@RealmActivity)
        val config: RealmConfiguration =
            RealmConfiguration.Builder().deleteRealmIfMigrationNeeded()
                .allowWritesOnUiThread(true)
                .build() ///만약 테이블끼리의 동기화가 필요하면 램을 삭한다는의미
        Realm.setDefaultConfiguration(config) //램 초기 설정

        val realm = Realm.getDefaultInstance() //램을 얻었음

         button_save.setOnClickListener {
             realm.executeTransaction {
                //Transction시작(중괄호 안이 작업단위)
                with(it.createObject(School::class.java)){
                    this.name = "어떤 대학교"
                    this.location = "서울"
                }
            }
        }
        button_load.setOnClickListener {
            realm.executeTransaction {
                val data = it.where(School::class.java).findAll() //데이터찾기(findfirst는 테이블의 한개의 인스턴스를 찾는다.)
                Log.d("TAG", "data: "+data)
            }
        }
        button_delete.setOnClickListener {
            realm.executeTransaction {
                it.where(School::class.java).findAll().deleteAllFromRealm()
                //it.where(School::class.java).findFirst()?.deleteFromRealm()
            }
        }
    }
}