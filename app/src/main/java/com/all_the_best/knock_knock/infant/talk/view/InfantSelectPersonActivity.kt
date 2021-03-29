package com.all_the_best.knock_knock.infant.talk.view

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.all_the_best.knock_knock.R
import kotlinx.android.synthetic.main.activity_infant_select_feel.*
import kotlinx.android.synthetic.main.activity_infant_select_person.*
import kotlinx.android.synthetic.main.activity_infant_select_person.char_dam
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class InfantSelectPersonActivity : AppCompatActivity() {
    private var bgSelect: Int = 1
    private var chSelect: Int = 0
    private var cookieCount: Int = 5

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_infant_select_person)
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
                    1 -> infant_select_person.setBackgroundResource(R.drawable.img_infant_home_morning_bg)
                    2 -> infant_select_person.setBackgroundResource(R.drawable.img_infant_home_bg_flower1)
                    3 -> infant_select_person.setBackgroundResource(R.drawable.img_infant_home_bg_sea1)
                    4 -> infant_select_person.setBackgroundResource(R.drawable.img_infant_home_bg_space1) // 우주
                }
                window.statusBarColor = Color.parseColor("#57DDFF")
            }
            in "14:00:000".."19:59:999" -> {
                when (bgSelect) {
                    1 -> infant_select_person.setBackgroundResource(R.drawable.img_infant_home_after_bg)
                    2 -> infant_select_person.setBackgroundResource(R.drawable.img_infant_home_bg_flower2)
                    3 -> infant_select_person.setBackgroundResource(R.drawable.img_infant_home_bg_sea2)
                    4 -> infant_select_person.setBackgroundResource(R.drawable.img_infant_home_bg_space2) // 우주
                }
                window.statusBarColor = Color.parseColor("#FF6471")
            }
            in "20:00:00".."23:59:999" -> {
                when (bgSelect) {
                    1 -> infant_select_person.setBackgroundResource(R.drawable.img_infant_home_night_bg)
                    2 -> infant_select_person.setBackgroundResource(R.drawable.img_infant_home_bg_flower3)
                    3 -> infant_select_person.setBackgroundResource(R.drawable.img_infant_home_bg_sea3)
                    4 -> infant_select_person.setBackgroundResource(R.drawable.img_infant_home_bg_space3) // 우주
                }
                window.statusBarColor = Color.parseColor("#0F0E15")
            }
            !in "08:00:00".."23:59:999" -> {
                when (bgSelect) {
                    1 -> infant_select_person.setBackgroundResource(R.drawable.img_infant_home_night_bg)
                    2 -> infant_select_person.setBackgroundResource(R.drawable.img_infant_home_bg_flower3)
                    3 -> infant_select_person.setBackgroundResource(R.drawable.img_infant_home_bg_sea3)
                    4 -> infant_select_person.setBackgroundResource(R.drawable.img_infant_home_bg_space3) // 우주
                }
                window.statusBarColor = Color.parseColor("#0F0E15")
            }
        }

        val intent = Intent(this, InfantTalkStartActivity::class.java)
        // 대상 선택 버튼
        infant_emj_person_1.setOnClickListener{
            intent.putExtra("bgSelect",bgSelect)
            intent.putExtra("chSelect",chSelect)
            intent.putExtra("cookieCount",cookieCount)
            startActivity(intent)
            overridePendingTransition(0, 0)
        }
        infant_emj_person_2.setOnClickListener{
            intent.putExtra("bgSelect",bgSelect)
            intent.putExtra("chSelect",chSelect)
            intent.putExtra("cookieCount",cookieCount)
            startActivity(intent)
            overridePendingTransition(0, 0)
        }
        infant_emj_person_3.setOnClickListener{
            intent.putExtra("bgSelect",bgSelect)
            intent.putExtra("chSelect",chSelect)
            intent.putExtra("cookieCount",cookieCount)
            startActivity(intent)
            overridePendingTransition(0, 0)
        }
        infant_emj_person_4.setOnClickListener{
            intent.putExtra("bgSelect",bgSelect)
            intent.putExtra("chSelect",chSelect)
            intent.putExtra("cookieCount",cookieCount)
            startActivity(intent)
            overridePendingTransition(0, 0)
        }
        infant_emj_person_5.setOnClickListener{
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