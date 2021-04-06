package com.all_the_best.knock_knock.infant.talk.view

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.lottie.LottieDrawable
import com.all_the_best.knock_knock.R
import kotlinx.android.synthetic.main.activity_infant_select_feel.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class InfantSelectFeelActivity : AppCompatActivity() {
    private var bgSelect: Int = 1
    private var chSelect: Int = 0
    private var cookieCount: Int = 5
    private var giftSelect:Int=0
    private var lottieSelect:Int=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_infant_select_feel)
        bgSelect = intent.getIntExtra("bgSelect",1)
        chSelect = intent.getIntExtra("chSelect",0)
        cookieCount = intent.getIntExtra("cookieCount",5)
        giftSelect = intent.getIntExtra("giftSelect",0)
        lottieSelect = intent.getIntExtra("lottieSelect",0)
        //setSelectFeelMotion()
        setSelectCharacter()


        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ISO_LOCAL_TIME
        val formatted = current.format(formatter)

        when(formatted){
            in "08:00:000".."13:59:999" -> {
                window.statusBarColor = Color.parseColor("#57DDFF")
                when (bgSelect) {
                    1 -> infant_select_feel.setBackgroundResource(R.drawable.img_infant_home_morning_bg)
                    2 -> infant_select_feel.setBackgroundResource(R.drawable.img_infant_home_bg_flower1)
                    3 -> infant_select_feel.setBackgroundResource(R.drawable.img_infant_home_bg_sea1)
                    4 -> {
                        infant_select_feel.setBackgroundResource(R.drawable.img_infant_home_bg_space1)
                        window.statusBarColor = Color.parseColor("#0F0E15")
                    } // 우주

                }

            }
            in "14:00:000".."19:59:999" -> {
                window.statusBarColor = Color.parseColor("#FF6471")
                when (bgSelect) {
                    1 -> infant_select_feel.setBackgroundResource(R.drawable.img_infant_home_after_bg)
                    2 -> infant_select_feel.setBackgroundResource(R.drawable.img_infant_home_bg_flower2)
                    3 -> infant_select_feel.setBackgroundResource(R.drawable.img_infant_home_bg_sea2)
                    4 -> {
                        infant_select_feel.setBackgroundResource(R.drawable.img_infant_home_bg_space2)
                        window.statusBarColor = Color.parseColor("#0F0E15")
                    } // 우주

                }

            }
            in "20:00:00".."23:59:999" -> {
                when (bgSelect) {
                    1 -> infant_select_feel.setBackgroundResource(R.drawable.img_infant_home_night_bg)
                    2 -> infant_select_feel.setBackgroundResource(R.drawable.img_infant_home_bg_flower3)
                    3 -> infant_select_feel.setBackgroundResource(R.drawable.img_infant_home_bg_sea3)
                    4 -> infant_select_feel.setBackgroundResource(R.drawable.img_infant_home_bg_space3) // 우주

                }
                window.statusBarColor = Color.parseColor("#0F0E15")
            }
            !in "08:00:00".."23:59:999" -> {
                when (bgSelect) {
                    1 -> infant_select_feel.setBackgroundResource(R.drawable.img_infant_home_night_bg)
                    2 -> infant_select_feel.setBackgroundResource(R.drawable.img_infant_home_bg_flower3)
                    3 -> infant_select_feel.setBackgroundResource(R.drawable.img_infant_home_bg_sea3)
                    4 -> infant_select_feel.setBackgroundResource(R.drawable.img_infant_home_bg_space3) // 우주

                }
                window.statusBarColor = Color.parseColor("#0F0E15")
            }
        }

        val intent = Intent(this, InfantSelectPersonActivity::class.java)
        //감정선택버튼
        infant_emj_feel_1.setOnClickListener{
            when(lottieSelect)
            {
                0-> lottieSelect = 1
            }
            intent.putExtra("bgSelect",bgSelect)
            intent.putExtra("chSelect",chSelect)
            intent.putExtra("cookieCount",cookieCount)
            intent.putExtra("giftSelect",giftSelect)
            intent.putExtra("lottieSelect",lottieSelect)
            startActivity(intent)
            overridePendingTransition(0, 0)
        }
        infant_emj_feel_2.setOnClickListener{
            when(lottieSelect)
            {
                0-> lottieSelect = 2
            }
            intent.putExtra("bgSelect",bgSelect)
            intent.putExtra("chSelect",chSelect)
            intent.putExtra("cookieCount",cookieCount)
            intent.putExtra("giftSelect",giftSelect)
            intent.putExtra("lottieSelect",lottieSelect)
            startActivity(intent)
            overridePendingTransition(0, 0)
        }
        infant_emj_feel_3.setOnClickListener{
            when(lottieSelect)
            {
                0-> lottieSelect = 3
            }
            intent.putExtra("bgSelect",bgSelect)
            intent.putExtra("chSelect",chSelect)
            intent.putExtra("cookieCount",cookieCount)
            intent.putExtra("giftSelect",giftSelect)
            intent.putExtra("lottieSelect",lottieSelect)
            startActivity(intent)
            overridePendingTransition(0, 0)
        }
        infant_emj_feel_4.setOnClickListener{
            when(lottieSelect)
            {
                0-> lottieSelect = 4
            }
            intent.putExtra("bgSelect",bgSelect)
            intent.putExtra("chSelect",chSelect)
            intent.putExtra("cookieCount",cookieCount)
            intent.putExtra("giftSelect",giftSelect)
            intent.putExtra("lottieSelect",lottieSelect)
            startActivity(intent)
            overridePendingTransition(0, 0)
        }
        infant_emj_feel_5.setOnClickListener{
            when(lottieSelect)
            {
                0-> lottieSelect = 5
            }
            intent.putExtra("bgSelect",bgSelect)
            intent.putExtra("chSelect",chSelect)
            intent.putExtra("cookieCount",cookieCount)
            intent.putExtra("giftSelect",giftSelect)
            intent.putExtra("lottieSelect",lottieSelect)
            startActivity(intent)
            overridePendingTransition(0, 0)
        }
        infant_emj_feel_6.setOnClickListener{
            when(lottieSelect)
            {
                0-> lottieSelect = 6
            }
            intent.putExtra("bgSelect",bgSelect)
            intent.putExtra("chSelect",chSelect)
            intent.putExtra("cookieCount",cookieCount)
            intent.putExtra("giftSelect",giftSelect)
            intent.putExtra("lottieSelect",lottieSelect)
            startActivity(intent)
            overridePendingTransition(0, 0)
        }
    }
    private fun setSelectCharacter(){
        when(chSelect){
            0 -> char_talk_feel.setAnimation("dami_hi.json")
            1 -> char_talk_feel.setAnimation("dami_idle.json")
            2 -> char_talk_feel.setAnimation("dami_idle.json")
        }
        setOnMotionStart()
    }
    private fun setOnMotionStart(){
        char_talk_feel.repeatMode = LottieDrawable.REVERSE
        char_talk_feel.repeatCount = LottieDrawable.INFINITE
        char_talk_feel.playAnimation()
    }

    // 기분 모션 선택
    private fun setSelectFeelMotion(){
        infant_emj_feel_1.setOnClickListener{
            when(lottieSelect)
            {
                0-> lottieSelect = 1
            }
        }
        infant_emj_feel_2.setOnClickListener{
            when(lottieSelect)
            {
                0-> lottieSelect = 2
            }
        }
        infant_emj_feel_3.setOnClickListener{
            when(lottieSelect)
            {
                0-> lottieSelect = 3
            }
        }
        infant_emj_feel_4.setOnClickListener{
            when(lottieSelect)
            {
                0-> lottieSelect = 4
            }
        }
        infant_emj_feel_5.setOnClickListener{
            when(lottieSelect)
            {
                0-> lottieSelect = 5
            }
        }
        infant_emj_feel_6.setOnClickListener{
            when(lottieSelect)
            {
                0-> lottieSelect = 6
            }
        }
    }

}