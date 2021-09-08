package org.techtown.kotlinpractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import kotlinx.android.synthetic.main.activity_tab_pager.*

class TabPagerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tab_pager)

        tab_layout.addTab(tab_layout.newTab().setText("ONE")) //탭을 하나씩 더해줌
        tab_layout.addTab(tab_layout.newTab().setText("TWO")) //탭을 하나씩 더해줌
        tab_layout.addTab(tab_layout.newTab().setText("THREE")) //탭을 하나씩 더해줌

    }

}

class PagerAdapter(
    val fragmentManager:FragmentManager,
    val tabCount : Int
): FragmentStatePagerAdapter(fragmentManager){

    override fun getCount(): Int {
        return tabCount
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                Fragment1()
            }
            1 -> {
                Fragment2()
            }
            2 -> {
                Fragment3()
            }
            else -> Fragment1()
        }
    }
}


