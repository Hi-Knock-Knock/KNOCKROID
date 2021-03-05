package com.all_the_best.knock_knock.infant.deco.view

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.all_the_best.knock_knock.R
import com.all_the_best.knock_knock.infant.deco.adapter.InfantDecoActivityAdapter
import com.all_the_best.knock_knock.infant.home.view.InfantHomeActivity
import kotlinx.android.synthetic.main.activity_infant_deco.*
import kotlinx.android.synthetic.main.activity_infant_gift_box.*
import kotlinx.android.synthetic.main.activity_infant_home.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class InfantDecoActivity : AppCompatActivity() {

    private val adapter by lazy{ InfantDecoActivityAdapter(supportFragmentManager) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_infant_deco)

        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ISO_LOCAL_TIME
        val formatted = current.format(formatter)

        when(formatted) {
            in "08:00:000".."13:59:999" -> {
                infant_deco.setBackgroundResource(R.drawable.img_infant_deco_morning_bg)
                window.statusBarColor = Color.parseColor("#57DDFF")
            }
            in "14:00:000".."19:59:999" -> {
                infant_deco.setBackgroundResource(R.drawable.img_infant_deco_after_bg)
                window.statusBarColor = Color.parseColor("#FF6471")
            }
            in "20:00:00".."23:59:999" -> {
                infant_deco.setBackgroundResource(R.drawable.img_infant_deco_night_bg)
                window.statusBarColor = Color.parseColor("#0F0E15")
            }
            !in "08:00:00".."23:59:999" -> {
                infant_deco.setBackgroundResource(R.drawable.img_infant_deco_night_bg)
                window.statusBarColor = Color.parseColor("#0F0E15")
            }
        }

        // 뷰페이저 어댑터 연결
        deco_tab_view.adapter = InfantDecoActivity@adapter

        val intent = Intent(this, InfantHomeActivity::class.java)
        infant_icon_deco_out1.setOnClickListener{
            startActivity(intent)
            overridePendingTransition(0, 0)
        }

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