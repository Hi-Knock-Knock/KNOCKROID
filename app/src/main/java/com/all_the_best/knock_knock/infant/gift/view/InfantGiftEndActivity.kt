package com.all_the_best.knock_knock.infant.gift.view

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.all_the_best.knock_knock.R
import com.all_the_best.knock_knock.infant.home.view.InfantHomeActivity
import kotlinx.android.synthetic.main.activity_infant_gift_end.*
import kotlinx.android.synthetic.main.activity_infant_home.*


class InfantGiftEndActivity : AppCompatActivity() {
    private var bgSelect: Int = 1
    private var chSelect: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_infant_gift_end)
        val cookieCount: TextView = findViewById(R.id.infant_cookie_count_gift_end_txt)
        bgSelect = intent.getIntExtra("bgSelect",1)
        chSelect = intent.getIntExtra("chSelect",0)

        window.statusBarColor = Color.parseColor("#8A2A6C")
        setSelectCharacter()
        if(intent.hasExtra("cookieCount")){
            cookieCount.text = intent.getStringExtra("cookieCount")
        }else{
            Toast.makeText(this, "잘못 전달되었습니다.", Toast.LENGTH_SHORT).show()
        }

        // 홈화면으로 돌아가기
        val intentGoHome = Intent(this, InfantHomeActivity::class.java)
        infant_icon_gift_out3.setOnClickListener{
            intentGoHome.putExtra("bgSelect",bgSelect)
            intentGoHome.putExtra("chSelect",chSelect)
            intentGoHome.putExtra("cookieCount",cookieCount.text)
            startActivity(intentGoHome)
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