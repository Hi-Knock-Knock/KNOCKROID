package com.all_the_best.knock_knock.infant.setting.view

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.viewpager.widget.ViewPager
import com.all_the_best.knock_knock.R
import com.all_the_best.knock_knock.infant.cookie.viewmodel.InfantCookieViewModel
import com.all_the_best.knock_knock.infant.gift.viewmodel.InfantGiftBgViewModel
import com.all_the_best.knock_knock.infant.home.view.InfantHomeActivity
import com.all_the_best.knock_knock.infant.setting.adapter.InfantViewPagerAdapter
import com.all_the_best.knock_knock.infant.setting.viewmodel.InfantSelectChViewModel
import kotlinx.android.synthetic.main.activity_infant_deco.*
import kotlinx.android.synthetic.main.activity_infant_select_character.*


class InfantSelectCharacterActivity : AppCompatActivity() {
    private lateinit var selectViewPagerAdapter: InfantViewPagerAdapter
    private val infantSelectChViewModel: InfantSelectChViewModel by viewModels()
    private val infantCookieViewModel: InfantCookieViewModel by viewModels()
    private val infantGiftBgViewModel: InfantGiftBgViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_infant_select_character)

        //상태바 색상 지정
        window.statusBarColor = Color.parseColor("#74DAFF")

        selectViewPagerAdapter =
            InfantViewPagerAdapter(
                supportFragmentManager
            )

        infant_viewpager_select.adapter = selectViewPagerAdapter


        // 아이 계정 선택 화면
        val intent2 = Intent(this, InfantSelectIdActivity::class.java)
        select_btn_back.setOnClickListener {
            intent2.putExtra("chSelect", infantSelectChViewModel.chSelect.value)
            intent2.putExtra("cookieCount", infantCookieViewModel.cookieCount.value)
            intent2.putExtra("giftSelect", infantGiftBgViewModel.giftSelect.value)
            startActivity(intent2)
            overridePendingTransition(0, 0)
        }

        infant_viewpager_select.addOnPageChangeListener(object: ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                if(position == 0){  // if you want the second page, for example
                    //Your code here
                    infantSelectChViewModel.setchSelect(0)
                }else if(position == 1){
                    infantSelectChViewModel.setchSelect(1)
                }else if(position == 2){
                    infantSelectChViewModel.setchSelect(2)
                }
            }
        })

        // 홈화면
        val intent1 = Intent(this, InfantHomeActivity::class.java)
        select_btn_ok.setOnClickListener{
            intent1.putExtra("chSelect", infantSelectChViewModel.chSelect.value)
            intent1.putExtra("giftSelect", infantGiftBgViewModel.giftSelect.value)
            startActivity(intent1)
            overridePendingTransition(0, 0)
        }


    }

}