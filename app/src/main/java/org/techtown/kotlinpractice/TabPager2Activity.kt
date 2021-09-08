package org.techtown.kotlinpractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_add_view.view.*
import kotlinx.android.synthetic.main.activity_tab_pager.*
import kotlinx.android.synthetic.main.activity_tab_pager2.*
import kotlinx.android.synthetic.main.activity_tab_pager2.tab_layout
import kotlinx.android.synthetic.main.activity_tab_pager2.view_pager

class TabPager2Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tab_pager2)

        tab_layout.addTab(tab_layout.newTab().setText("ONE")) //탭을 하나씩 더해줌
        tab_layout.addTab(tab_layout.newTab().setText("TWO")) //탭을 하나씩 더해줌
        tab_layout.addTab(tab_layout.newTab().setText("THREE")) //탭을 하나씩 더해줌

        val adapter = ThreePageAdapter(LayoutInflater.from(this@TabPager2Activity))
        view_pager.adapter = adapter
        view_pager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tab_layout))

        tab_layout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                view_pager.currentItem = tab!!.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })
    }

}

class ThreePageAdapter(
    val layoutInflater: LayoutInflater
) : PagerAdapter() {


    //실제적으로 뷰를 그려주는 부분 (Fragment 클래스를 따로 만들지 않고 정적인 레이아웃만 보여주고 싶을때는 다음과 같이 사용)
    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        when (position) {
            0 -> {
                val view = layoutInflater.inflate(R.layout.fragment_two, container, false)
                container.addView(view)
                return view
            }
            1 -> {
                val view = layoutInflater.inflate(R.layout.fragment_one, container, false)
                container.addView(view)
                return view
            }
            2 -> {
                val view = layoutInflater.inflate(R.layout.fragment_three, container, false)
                container.addView(view)
                return view
            }
            else -> {
                val view = layoutInflater.inflate(R.layout.fragment_one, container, false)
                container.addView(view)
                return view
            }
        }
    }

    //뷰가 가려질때 내부적으로 파기되어야 할때 설정
    override fun destroyItem(
        container: ViewGroup,
        position: Int,
        `object`: Any
    ) { //`object`는 위에서 뷰를 만들어준게 들어옴
        container.removeView(`object` as View)
        Log.d("TAG", "destroyItem: "+`object`)
    }

    //페이저의 개수 리턴
    override fun getCount(): Int {
        return 3
    }

    // 화면에 나와있는 뷰가 니가 위에서 만든 뷰와 같냐라는 함수
    override fun isViewFromObject(view: View, `object`: Any): Boolean { //`object`는 위에서 뷰를 만들어준게 들어옴
        return view === `object` as View //두개의 주솟값을 비교(아예 같은지 비교) 페이지어뎁터에서는 이렇게 사용
    }
}