package org.techtown.kotlinpractice

import android.content.Intent
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_contacts.*
import kotlinx.android.synthetic.main.contact_view.*

class Contacts : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contacts)

        favorites.setOnClickListener {
            favorites.setTypeface(Typeface.DEFAULT, Typeface.BOLD)
            allcontacts.setTypeface(Typeface.DEFAULT, Typeface.NORMAL)
        }
        allcontacts.setOnClickListener {
            allcontacts.setTypeface(Typeface.DEFAULT, Typeface.BOLD)
            favorites.setTypeface(Typeface.DEFAULT, Typeface.NORMAL)
        }

        val phoneBook = createFakePhoneBook(30,PhoneBook())
        createPhoneBookList(phoneBook)
    }

    fun createFakePhoneBook(fakeNumber: Int = 10,phoneBook: PhoneBook = PhoneBook()):PhoneBook{

        for (i in 0 until fakeNumber){
            phoneBook.addPerson(Person("$i 번째 사람","$i 번째 사람의 전화번호"))
        }
        return phoneBook
    }

    fun createPhoneBookList(phoneBook:PhoneBook){
        val layoutInflater = this@Contacts.layoutInflater
        val container = findViewById<LinearLayout>(R.id.container)
        for ( i in 0 until phoneBook.personList.size){
            val view = layoutInflater.inflate(R.layout.contact_view,null)
            val personName = view.findViewById<TextView>(R.id.name)
            val personPhoneNumber = view.findViewById<TextView>(R.id.profile)
            personName.setText(phoneBook.personList[i].name)
            personPhoneNumber.setText(phoneBook.personList[i].phoneNumber)
            addSetOnclickListener(phoneBook.personList[i],view)
            container.addView(view)
        }
    }

    fun addSetOnclickListener(person:Person, view:View){
        view.setOnClickListener {
            val intent = Intent(this@Contacts,PhoneBookDetailActivity::class.java)
            intent.putExtra("name",person.name)
            intent.putExtra("number",person.phoneNumber)
            startActivity(intent)
        }
    }
}

class PhoneBook() {
    //전화번호부
    val personList = ArrayList<Person>()

    //사람을 전화번호부에 추가하는 기능
    fun addPerson(person: Person){
        personList.add(person)
    }
}

//사람정보
class Person(val name: String, val phoneNumber: String){

}
