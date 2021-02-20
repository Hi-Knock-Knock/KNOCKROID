package com.all_the_best.knock_knock.infant_layout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.all_the_best.knock_knock.R
import com.all_the_best.knock_knock.infant_layout.adapters.InfantSwitchViewPagerAdapter

import kotlinx.android.synthetic.main.activity_infant_switch_character.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class InfantSwitchCharacterActivity : AppCompatActivity() {
    private lateinit var switchViewPagerAdapter: InfantSwitchViewPagerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_infant_switch_character)

        val intent = Intent(this, InfantHomeActivity::class.java)
        switch_btn_back.setOnClickListener {
            startActivity(intent)
        }

        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ISO_LOCAL_TIME
        val formatted = current.format(formatter)


        when(formatted){
            in "08:00:000".."13:59:999" -> {
                infant_switch_character.setBackgroundResource(R.drawable.img_infant_home_morning_bg)
            }
            in "14:00:000".."19:59:999" -> {
                infant_switch_character.setBackgroundResource(R.drawable.img_infant_home_sunset_bg)
            }
            in "20:00:00".."23:59:999" -> {
                infant_switch_character.setBackgroundResource(R.drawable.img_infant_home_night_bg)
            }
            !in "08:00:00".."23:59:999" -> {
                infant_switch_character.setBackgroundResource(R.drawable.img_infant_home_night_bg)
            }
        }

        switchViewPagerAdapter =
            InfantSwitchViewPagerAdapter(
                supportFragmentManager
            )

        infant_viewpager_switch.adapter = switchViewPagerAdapter
//        dots_indicator.setViewPager(infant_viewpager_switch)
        infant_viewpager_switch.addOnPageChangeListener(object: ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {}

            override fun onPageSelected(position: Int) {
                if(position == 2){

                }
            }
        })
    }
}