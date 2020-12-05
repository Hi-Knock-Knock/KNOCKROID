package com.all_the_best.knock_knock.parent_layout

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.viewpager.widget.ViewPager
import com.all_the_best.knock_knock.R
import kotlinx.android.synthetic.main.activity_parent_home.*
import kotlinx.android.synthetic.main.fragment_parent_faq.*
import kotlinx.android.synthetic.main.fragment_parent_home.*
import kotlinx.android.synthetic.main.fragment_parent_talk.*
import kotlin.properties.Delegates

class ParentHomeActivity : AppCompatActivity() {
    private lateinit var viewPagerAdapter: ParentViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parent_home)


        viewPagerAdapter = ParentViewPagerAdapter(supportFragmentManager)

        parent_viewpager.adapter = viewPagerAdapter

        parent_viewpager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {}

            override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                parent_bottom_navi.menu.getItem(position).isChecked = true
            }
        })

        parent_bottom_navi.setOnNavigationItemSelectedListener {
            var index by Delegates.notNull<Int>()
            when(it.itemId){
                R.id.menu_home -> index = 0
                R.id.menu_talk -> index = 1
                R.id.menu_faq -> index = 2
            }
            parent_viewpager.currentItem = index
            true
        }

    }
    var time3: Long = 0 //백버튼 두번 누를 때 종료 동작에서 첫번째 누른시간
    override fun onBackPressed() {
        var cutPlace = 0    //문자열 끊을 위치 -> fragmentList[i].toString().substring(0, cutPlace) 여기서 사용됨
        var fragmentName: String = ""   //문자열 비교할 프래그먼트 이름
        when(parent_viewpager.currentItem){
            0 -> {
                cutPlace = 18
                fragmentName = "ParentHomeFragment"
            }
            1 -> {
                cutPlace = 18
                fragmentName = "ParentTalkFragment"
            }
            2 -> {
                cutPlace = 17
                fragmentName = "ParentFaqFragment"
            }
        }
        //프래그먼트 별로 백버튼 메소드 실행 시 동작 다르게 구현하기 위한 작업.
        val fragmentList = supportFragmentManager.fragments
        for (fragment in fragmentList) {
            for(i in 0 until fragmentList.size){
                if(fragmentList[i].toString().substring(0, cutPlace) == fragmentName){
                    if (fragmentList[i] is FragmentOnBackPressed) { //프래그먼트가 FragmentOnBackPressed 인터페이스 타입인지 확인
                        //코틀린에서는 특정 변수를 자신이 원하는 자료형으로 변환하기 위해서 as 연산자를 사용
                        if(!(fragmentList[i] as FragmentOnBackPressed).onBackPressed()){
                            //true 일 경우  : 각 프래그먼트들의 메소드 동작
                            //false 일 경우 : 백버튼 2번 누르면 앱 종료
                            val time1 = System.currentTimeMillis()
                            val time2 = time1 - time3
                            if (time2 in 0..2000) {
                                ActivityCompat.finishAffinity(this) //해당 앱의 루트 액티비티를 종료시킨다.

                                System.runFinalization() //현재 작업중인 쓰레드가 다 종료되면, 종료 시키라는 명령어이다.

                                System.exit(0) // 현재 액티비티를 종료시킨다.
                            } else {
                                time3 = time1
                                Toast.makeText(applicationContext, "한번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT).show()
                            }
                        }
                        return
                    }
                }
            }
        }
    }
}