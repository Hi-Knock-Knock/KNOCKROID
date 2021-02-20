package com.all_the_best.knock_knock.infant.deco.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.all_the_best.knock_knock.R
import com.all_the_best.knock_knock.infant.deco.adapter.InfantDecoActivityAdapter
import kotlinx.android.synthetic.main.activity_infant_deco.*

class InfantDecoActivity : AppCompatActivity() {

    private val adapter by lazy{ InfantDecoActivityAdapter(supportFragmentManager) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_infant_deco)

        // 뷰페이저 어댑터 연결
        deco_tab_view.adapter = InfantDecoActivity@adapter

        deco_tab_view.addOnPageChangeListener(object : ViewPager.OnAdapterChangeListener,
            ViewPager.OnPageChangeListener {
            override fun onAdapterChanged(
                viewPager: ViewPager,
                oldAdapter: PagerAdapter?,
                newAdapter: PagerAdapter?
            ) {

            }

            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {
                tab.getTabAt(0)?.setIcon(R.drawable.ic_deco_face_w)
                tab.getTabAt(1)?.setIcon(R.drawable.ic_deco_scarf_w)
                tab.getTabAt(2)?.setIcon(R.drawable.ic_deco_glasses_w)

                when(position){
                    0 -> tab.getTabAt(0)?.setIcon(R.drawable.ic_deco_face_b)
                    1 -> tab.getTabAt(1)?.setIcon(R.drawable.ic_deco_scarf_b)
                    2 -> tab.getTabAt(2)?.setIcon(R.drawable.ic_deco_glasses_b)
                }
            }

        })

        //탭 레이아웃에 뷰페이저 연결
        tab.setupWithViewPager(deco_tab_view)

        // 초기화
        tab.getTabAt(0)?.setIcon(R.drawable.ic_deco_face_b)
        tab.getTabAt(1)?.setIcon(R.drawable.ic_deco_scarf_w)
        tab.getTabAt(2)?.setIcon(R.drawable.ic_deco_glasses_w)


        }
 }