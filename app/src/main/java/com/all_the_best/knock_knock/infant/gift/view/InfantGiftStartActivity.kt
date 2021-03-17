package com.all_the_best.knock_knock.infant.gift.view

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.all_the_best.knock_knock.R
import com.all_the_best.knock_knock.infant.home.view.InfantHomeActivity
import kotlinx.android.synthetic.main.activity_infant_gift_start.*
import kotlinx.android.synthetic.main.activity_infant_gift_start.infant_cookie_count

class InfantGiftStartActivity : AppCompatActivity() {
    private var bgSelect: Int = 1
    //var imageTrue:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_infant_gift_start)
        bgSelect = intent.getIntExtra("bgSelect",1)
        var count:Int = 5 //보유 쿠키 개수
        var count3:Int = 0 //소비한 쿠키 개

        window.statusBarColor = Color.parseColor("#8A2A6C")

        val intent = Intent(this, InfantHomeActivity::class.java)
        infant_icon_gift_out1.setOnClickListener{
            intent.putExtra("bgSelect",bgSelect)
            startActivity(intent)
            overridePendingTransition(0, 0)
        }

        val intent1 = Intent(this, InfantGiftBoxActivity::class.java)
        intent1.putExtra("bgSelect",bgSelect)

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
            infant_cookie_count.setText(count.toString() + "개")
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
            infant_cookie_count.setText(count.toString() + "개")

        }

        infant_empty_cookie3.setOnClickListener {
            if(count!=2){
                count--
                count3++
            }
            if(count3==3){
                startActivity(intent1)
                overridePendingTransition(0, 0)
            }
            infant_empty_cookie3.setImageResource(R.drawable.img_infant_full_cookie)
            infant_cookie_count.setText(count.toString() + "개")
        }

    }
}