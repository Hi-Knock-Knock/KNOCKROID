package com.all_the_best.knock_knock.infant.setting.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.all_the_best.knock_knock.R
import com.all_the_best.knock_knock.infant.home.view.InfantHomeActivity
import com.all_the_best.knock_knock.infant.setting.adapter.InfantViewPagerAdapter
import kotlinx.android.synthetic.main.activity_infant_select_character.*


class InfantSelectCharacterActivity : AppCompatActivity() {
    private lateinit var selectViewPagerAdapter: InfantViewPagerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_infant_select_character)

        val intent1 = Intent(this, InfantHomeActivity::class.java)
        val intent2 = Intent(this, InfantSelectIdActivity::class.java)
        select_btn_ok.setOnClickListener{
            startActivity(intent1)
        }
        select_btn_back.setOnClickListener {
            startActivity(intent2)
        }

        selectViewPagerAdapter =
            InfantViewPagerAdapter(
                supportFragmentManager
            )

        infant_viewpager_select.adapter = selectViewPagerAdapter

        infant_viewpager_select.addOnPageChangeListener(object: ViewPager.OnPageChangeListener{
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