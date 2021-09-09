package org.techtown.kotlinpractice

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_phone_book_with_recycler_view.*

class PhoneBookWithRecyclerViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phone_book_with_recycler_view)


//        val phoneBookRecyclerAdapter = PhoneBookRecyclerAdapter(
//            createFakePhoneBook(30),
//            LayoutInflater.from(this), this
//        )
//
//        phonebook_recycler_view.adapter = phoneBookRecyclerAdapter
//        phonebook_recycler_view.layoutManager = LinearLayoutManager(this)

        //with 이용하여 최대한 간편하게 표현
        with(phonebook_recycler_view) {
            this.adapter = PhoneBookRecyclerAdapter(
                createFakePhoneBook(30),
                LayoutInflater.from(this@PhoneBookWithRecyclerViewActivity),
                this@PhoneBookWithRecyclerViewActivity
            )
            this.layoutManager = LinearLayoutManager(this@PhoneBookWithRecyclerViewActivity)
        }
    }

    private fun createFakePhoneBook(
        fakeNumber: Int = 10,
        phoneBook: PhoneBook = PhoneBook()
    ): PhoneBook {
        for (i in 0 until fakeNumber) {
            phoneBook.addPerson(Person2("$i 번째 사람", "$i 번째 사람의 전화번호"))
        }
        return phoneBook
    }

}

class PhoneBookRecyclerAdapter(
    val phonebookList: PhoneBook,
    val inflater: LayoutInflater,
    val activity: Activity
) : RecyclerView.Adapter<PhoneBookRecyclerAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val personName: TextView

        init {
            personName = itemView.findViewById(R.id.name)
            itemView.setOnClickListener {
                val intent = Intent(activity, PhoneBookDetailActivity::class.java)
                intent.putExtra("name", phonebookList.personList.get(adapterPosition).name)
                intent.putExtra("number", phonebookList.personList.get(adapterPosition).phoneNumber)
                activity.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflater.inflate(R.layout.contact_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.personName.setText(phonebookList.personList.get(position).name)
    }

    override fun getItemCount(): Int {
        return phonebookList.personList.size
    }
}