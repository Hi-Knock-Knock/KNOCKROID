package com.all_the_best.knock_knock.infant_layout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.all_the_best.knock_knock.R
import kotlinx.android.synthetic.main.activity_infant_gift_end.*
import kotlinx.android.synthetic.main.activity_infant_gift_start.*
import kotlinx.android.synthetic.main.activity_infant_gift_start.infant_cookie_count
import kotlinx.android.synthetic.main.activity_infant_home.*

class InfantGiftStartActivity : AppCompatActivity() {

    //var imageTrue:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_infant_gift_start)
        var count:Int = 5 //보유 쿠키 개수
        var count3:Int = 0 //소비한 쿠키 개

        val intent = Intent(this, InfantHomeActivity::class.java)
        infant_icon_gift_out1.setOnClickListener{
            startActivity(intent)
        }

        val intent1 = Intent(this, InfantGiftBoxActivity::class.java)

        infant_empty_cookie1.setOnClickListener {
            if(count!=2){
                count--
                count3++
            }
            if(count3==3){
                startActivity(intent1)
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
            }
            infant_empty_cookie3.setImageResource(R.drawable.img_infant_full_cookie)
            infant_cookie_count.setText(count.toString() + "개")
        }

    }
}