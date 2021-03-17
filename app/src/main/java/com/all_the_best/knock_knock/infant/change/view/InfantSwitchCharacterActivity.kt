package com.all_the_best.knock_knock.infant.change.view

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.all_the_best.knock_knock.R
import com.all_the_best.knock_knock.infant.home.view.InfantHomeActivity
import com.all_the_best.knock_knock.infant.change.adapter.InfantSwitchViewPagerAdapter
import kotlinx.android.synthetic.main.activity_infant_select_feel.*

import kotlinx.android.synthetic.main.activity_infant_switch_character.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class InfantSwitchCharacterActivity : AppCompatActivity() {
    private var bgSelect: Int = 1
    private lateinit var switchViewPagerAdapter: InfantSwitchViewPagerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_infant_switch_character)
        bgSelect = intent.getIntExtra("bgSelect",1)
        val intent = Intent(this, InfantHomeActivity::class.java)
        switch_btn_back.setOnClickListener {
            intent.putExtra("bgSelect", bgSelect)
            startActivity(intent)
            overridePendingTransition(0, 0)
        }

        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ISO_LOCAL_TIME
        val formatted = current.format(formatter)


        when(formatted){
            in "08:00:000".."13:59:999" -> {
                when (bgSelect) {
                    1 -> infant_switch_character.setBackgroundResource(R.drawable.img_infant_home_morning_bg)
                    2 -> infant_switch_character.setBackgroundResource(R.drawable.img_infant_home_bg_flower1)
                    3 -> infant_switch_character.setBackgroundResource(R.drawable.img_infant_home_bg_sea1)
                }
                window.statusBarColor = Color.parseColor("#57DDFF")
            }
            in "14:00:000".."19:59:999" -> {
                when (bgSelect) {
                    1 -> infant_switch_character.setBackgroundResource(R.drawable.img_infant_home_after_bg)
                    2 -> infant_switch_character.setBackgroundResource(R.drawable.img_infant_home_bg_flower1)
                    3 -> infant_switch_character.setBackgroundResource(R.drawable.img_infant_home_bg_sea1)
                }
                window.statusBarColor = Color.parseColor("#FF6471")
            }
            in "20:00:00".."23:59:999" -> {
                when (bgSelect) {
                    1 -> infant_switch_character.setBackgroundResource(R.drawable.img_infant_home_night_bg)
                    2 -> infant_switch_character.setBackgroundResource(R.drawable.img_infant_home_bg_flower3)
                    3 -> infant_switch_character.setBackgroundResource(R.drawable.img_infant_home_bg_sea3)
                }
                window.statusBarColor = Color.parseColor("#0F0E15")
            }
            !in "08:00:00".."23:59:999" -> {
                when (bgSelect) {
                    1 -> infant_switch_character.setBackgroundResource(R.drawable.img_infant_home_night_bg)
                    2 -> infant_switch_character.setBackgroundResource(R.drawable.img_infant_home_bg_flower3)
                    3 -> infant_switch_character.setBackgroundResource(R.drawable.img_infant_home_bg_sea3)
                }
                window.statusBarColor = Color.parseColor("#0F0E15")
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