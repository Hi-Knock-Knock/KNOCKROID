package com.all_the_best.knock_knock.infant.gift.view

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.all_the_best.knock_knock.R
import com.all_the_best.knock_knock.infant.home.view.InfantHomeActivity
import kotlinx.android.synthetic.main.activity_infant_gift_start.*

class InfantGiftStartActivity : AppCompatActivity() {
    private var bgSelect: Int = 1
    private var chSelect: Int = 0
    //var imageTrue:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_infant_gift_start)

        val cookieCount: TextView = findViewById(R.id.infant_gift_start_cookie_count)
        bgSelect = intent.getIntExtra("bgSelect",1)
        chSelect = intent.getIntExtra("chSelect",0)

        var count:Int = 5 //보유 쿠키 개수
        var count3:Int = 0 //소비한 쿠키 개

        window.statusBarColor = Color.parseColor("#8A2A6C")

        if(intent.hasExtra("cookieCount")){
            cookieCount.text = intent.getStringExtra("cookieCount")
        }else{
            Toast.makeText(this, "잘못 전달되었습니다.", Toast.LENGTH_SHORT).show()
        }

        // 선물 상자 받는 화면으로 이동
        val intent1 = Intent(this, InfantGiftBoxActivity::class.java)
        intent1.putExtra("bgSelect",bgSelect)
        intent1.putExtra("chSelect",chSelect)


        infant_empty_cookie1.setOnClickListener {
            if(count!=2){
                count--
                count3++
            }
            if(count3==3){
                startActivity(intent1)
                overridePendingTransition(0, 0)
            }
            infant_empty_cookie1.setImageResource(R.drawable.img_infant_full_cookie)
            infant_gift_start_cookie_count.setText(count.toString() + "개")
        }

        infant_empty_cookie2.setOnClickListener {
            if(count!=2){
                count--
                count3++
            }
            if(count3==3){
                startActivity(intent1)
                overridePendingTransition(0, 0)
            }
            infant_empty_cookie2.setImageResource(R.drawable.img_infant_full_cookie)
            infant_gift_start_cookie_count.setText(count.toString() + "개")


        }

        infant_empty_cookie3.setOnClickListener {
            if(count!=2){
                count--
                count3++
            }
            if(count3==3){
                infant_gift_start_cookie_count.setText(count.toString() + "개")
                intent1.putExtra("cookieCount",cookieCount.text) //줄어든 쿠키갯수 받기
                startActivity(intent1)
                overridePendingTransition(0, 0)
            }
            infant_empty_cookie3.setImageResource(R.drawable.img_infant_full_cookie)
        }

        // 홈화면으로 돌아가기
        val intentGoHome = Intent(this, InfantHomeActivity::class.java)
        infant_icon_gift_out1.setOnClickListener{
            intentGoHome.putExtra("bgSelect",bgSelect)
            intentGoHome.putExtra("chSelect",chSelect)
            intentGoHome.putExtra("cookieCount",cookieCount.text)
            startActivity(intentGoHome)
            overridePendingTransition(0, 0)
        }

    }
}