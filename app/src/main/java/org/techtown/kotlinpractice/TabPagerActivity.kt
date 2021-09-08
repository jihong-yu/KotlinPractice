package org.techtown.kotlinpractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_tab_pager.*

class TabPagerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tab_pager)

        tab_layout.addTab(tab_layout.newTab().setText("ONE")) //탭을 하나씩 더해줌
        tab_layout.addTab(tab_layout.newTab().setText("TWO")) //탭을 하나씩 더해줌
        tab_layout.addTab(tab_layout.newTab().setText("THREE")) //탭을 하나씩 더해줌

        val pagerAdapter = PagerAdapter(supportFragmentManager,3) //탭의 개수 입력
        view_pager.adapter = pagerAdapter

        tab_layout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                view_pager.currentItem = tab!!.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })
        view_pager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tab_layout))
        //view_pager와 Tab_layout 연동 ( 페이저가 이동했을때 탭도 자동으로 이동)
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


