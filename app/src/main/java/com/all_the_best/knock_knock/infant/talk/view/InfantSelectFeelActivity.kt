package com.all_the_best.knock_knock.infant.talk.view

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.all_the_best.knock_knock.R
import kotlinx.android.synthetic.main.activity_infant_select_feel.*
import kotlinx.android.synthetic.main.activity_infant_switch_character.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class InfantSelectFeelActivity : AppCompatActivity() {
    private var bgSelect: Int = 1
    private var chSelect: Int = 0
    private var cookieCount: Int = 5

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_infant_select_feel)
        bgSelect = intent.getIntExtra("bgSelect",1)
        chSelect = intent.getIntExtra("chSelect",0)
        cookieCount = intent.getIntExtra("cookieCount",5)

        setSelectCharacter()
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ISO_LOCAL_TIME
        val formatted = current.format(formatter)


        when(formatted){
            in "08:00:000".."13:59:999" -> {
                when (bgSelect) {
                    1 -> infant_select_feel.setBackgroundResource(R.drawable.img_infant_home_morning_bg)
                    2 -> infant_select_feel.setBackgroundResource(R.drawable.img_infant_home_bg_flower1)
                    3 -> infant_select_feel.setBackgroundResource(R.drawable.img_infant_home_bg_sea1)
                    4 -> infant_select_feel.setBackgroundResource(R.drawable.img_infant_home_bg_space1) // 우주

                }
                window.statusBarColor = Color.parseColor("#57DDFF")
            }
            in "14:00:000".."19:59:999" -> {
                when (bgSelect) {
                    1 -> infant_select_feel.setBackgroundResource(R.drawable.img_infant_home_after_bg)
                    2 -> infant_select_feel.setBackgroundResource(R.drawable.img_infant_home_bg_flower2)
                    3 -> infant_select_feel.setBackgroundResource(R.drawable.img_infant_home_bg_sea2)
                    4 -> infant_select_feel.setBackgroundResource(R.drawable.img_infant_home_bg_space2) // 우주

                }
                window.statusBarColor = Color.parseColor("#FF6471")
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
            intent.putExtra("bgSelect",bgSelect)
            intent.putExtra("chSelect",chSelect)
            intent.putExtra("cookieCount",cookieCount)
            startActivity(intent)
            overridePendingTransition(0, 0)
        }
        infant_emj_feel_2.setOnClickListener{
            intent.putExtra("bgSelect",bgSelect)
            intent.putExtra("chSelect",chSelect)
            intent.putExtra("cookieCount",cookieCount)
            startActivity(intent)
            overridePendingTransition(0, 0)
        }
        infant_emj_feel_3.setOnClickListener{
            intent.putExtra("bgSelect",bgSelect)
            intent.putExtra("chSelect",chSelect)
            intent.putExtra("cookieCount",cookieCount)
            startActivity(intent)
            overridePendingTransition(0, 0)
        }
        infant_emj_feel_4.setOnClickListener{
            intent.putExtra("bgSelect",bgSelect)
            intent.putExtra("chSelect",chSelect)
            intent.putExtra("cookieCount",cookieCount)
            startActivity(intent)
            overridePendingTransition(0, 0)
        }
        infant_emj_feel_5.setOnClickListener{
            intent.putExtra("bgSelect",bgSelect)
            intent.putExtra("chSelect",chSelect)
            intent.putExtra("cookieCount",cookieCount)
            startActivity(intent)
            overridePendingTransition(0, 0)
        }
        infant_emj_feel_6.setOnClickListener{
            intent.putExtra("bgSelect",bgSelect)
            intent.putExtra("chSelect",chSelect)
            intent.putExtra("cookieCount",cookieCount)
            startActivity(intent)
            overridePendingTransition(0, 0)
        }
    }
    private fun setSelectCharacter(){
        when(chSelect){
            0 -> char_dam.setImageResource(R.drawable.img_char_dam)
            1 -> char_dam.setImageResource(R.drawable.img_char_knock)
            2 -> char_dam.setImageResource(R.drawable.img_char_timi)
        }
    }

}