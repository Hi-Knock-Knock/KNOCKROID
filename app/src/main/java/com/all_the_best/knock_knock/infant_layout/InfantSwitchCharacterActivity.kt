package com.all_the_best.knock_knock.infant_layout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.all_the_best.knock_knock.R
import kotlinx.android.synthetic.main.activity_infant_select_character.dots_indicator
import kotlinx.android.synthetic.main.activity_infant_switch_character.*

class InfantSwitchCharacterActivity : AppCompatActivity() {
    private lateinit var switchViewPagerAdapter: InfantViewPagerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_infant_switch_character)

        val intent = Intent(this, InfantHomeActivity::class.java)
        switch_btn_back.setOnClickListener {
            startActivity(intent)
        }

        switchViewPagerAdapter = InfantViewPagerAdapter(supportFragmentManager)

        infant_viewpager_switch.adapter = switchViewPagerAdapter
        dots_indicator.setViewPager(infant_viewpager_switch)
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