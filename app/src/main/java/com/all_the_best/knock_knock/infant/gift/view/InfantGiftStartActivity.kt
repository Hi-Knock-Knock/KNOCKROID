package com.all_the_best.knock_knock.infant.gift.view

import android.content.Intent
import android.graphics.Color
import android.media.MediaPlayer
import android.media.SoundPool
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import com.airbnb.lottie.LottieDrawable
import com.all_the_best.knock_knock.R
import com.all_the_best.knock_knock.infant.cookie.viewmodel.InfantCookieViewModel
import com.all_the_best.knock_knock.infant.gift.viewmodel.InfantGiftBgViewModel
import com.all_the_best.knock_knock.infant.home.view.InfantHomeActivity
import kotlinx.android.synthetic.main.activity_infant_gift_start.*
import kotlinx.android.synthetic.main.activity_infant_talk_start.*

class InfantGiftStartActivity : AppCompatActivity() {

    //var mediaPlayer: MediaPlayer? = null

    private var bgSelect: Int = 1
    private var chSelect: Int = 0
    private var cookieCount: Int = 5
    private var giftSelect:Int=0
    private var musicPlay:Int=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_infant_gift_start)
        setOnLottieStart()
//        if(mediaPlayer==null){
//            mediaPlayer = MediaPlayer.create(this, R.raw.bgm2);
//            mediaPlayer!!.setVolume(0.1f,0.1f)
//            mediaPlayer!!.start()
//        }else{
//            Log.d("media","존재한다. ")
//        }
        val cookieGiftCount1: TextView = findViewById(R.id.infant_gift_start_cookie_count)
        bgSelect = intent.getIntExtra("bgSelect",1)
        chSelect = intent.getIntExtra("chSelect",0)
        cookieCount = intent.getIntExtra("cookieCount",5)
        giftSelect = intent.getIntExtra("giftSelect",0)
        musicPlay = intent.getIntExtra("musicPlay",0)

        val intentGoHome = Intent(this, InfantHomeActivity::class.java)
        cookieGiftCount1.text = cookieCount.toString()

        //var count:Int = cookieCount //보유 쿠키 개수
        var count3:Int = 0 //소비한 쿠키 개

        window.statusBarColor = Color.parseColor("#8A2A6C")

        // 선물 상자 받는 화면으로 이동
        val intent1 = Intent(this, InfantGiftBoxActivity::class.java)
        intent1.putExtra("bgSelect",bgSelect)
        intent1.putExtra("chSelect",chSelect)
        intent1.putExtra("cookieCount",cookieCount)
        intent1.putExtra("giftSelect",giftSelect)
        intent1.putExtra("musicPlay",musicPlay)

        infant_empty_cookie1.setOnClickListener {
            if(cookieCount<=2){
                Toast.makeText(this, "쿠키가 부족해요!ㅠㅠ", Toast.LENGTH_SHORT).show()
            }else{
                if(cookieCount==0){
                    Toast.makeText(this, "쿠키가 부족해요!ㅠㅠ", Toast.LENGTH_SHORT).show()
                    cookieCount++
                    intentGoHome.putExtra("bgSelect",bgSelect)
                    intentGoHome.putExtra("chSelect",chSelect)
                    intentGoHome.putExtra("cookieCount",cookieCount)
                    intentGoHome.putExtra("giftSelect",giftSelect)
                    //mediaPlayer!!.stop()
                    startActivity(intentGoHome)
                    overridePendingTransition(0, 0)
                }else{
                    cookieCount--
                    count3++
                    infant_empty_cookie1.setImageResource(R.drawable.img_infant_full_cookie)
                }
                if(count3==3){
                    infant_empty_cookie1.setImageResource(R.drawable.img_infant_full_cookie)
                    //mediaPlayer!!.stop()
                    startActivity(intent1)
                    overridePendingTransition(0, 0)
                }
            }
            cookieGiftCount1.text = cookieCount.toString()
        }

        infant_empty_cookie2.setOnClickListener {
            if(cookieCount==0){
                Toast.makeText(this, "쿠키가 부족해요!ㅠㅠ", Toast.LENGTH_SHORT).show()
                cookieCount++
                intentGoHome.putExtra("bgSelect",bgSelect)
                intentGoHome.putExtra("chSelect",chSelect)
                intentGoHome.putExtra("cookieCount",cookieCount)
                intentGoHome.putExtra("giftSelect",giftSelect)
                //mediaPlayer!!.stop()
                startActivity(intentGoHome)
                overridePendingTransition(0, 0)
            }else{
                cookieCount--
                count3++
                infant_empty_cookie2.setImageResource(R.drawable.img_infant_full_cookie)
            }
            if(count3==3){
                infant_empty_cookie2.setImageResource(R.drawable.img_infant_full_cookie)
                //mediaPlayer!!.stop()
                startActivity(intent1)
                overridePendingTransition(0, 0)
            }
            cookieGiftCount1.text = cookieCount.toString()
        }

        infant_empty_cookie3.setOnClickListener {
            if(cookieCount==0){
                Toast.makeText(this, "쿠키가 부족해요!ㅠㅠ", Toast.LENGTH_SHORT).show()
                cookieCount++
                intentGoHome.putExtra("bgSelect",bgSelect)
                intentGoHome.putExtra("chSelect",chSelect)
                intentGoHome.putExtra("cookieCount",cookieCount)
                intentGoHome.putExtra("giftSelect",giftSelect)
                intentGoHome.putExtra("musicPlay",musicPlay)
                //mediaPlayer!!.stop()
                startActivity(intentGoHome)
                overridePendingTransition(0, 0)
            }else{
                cookieCount--
                count3++
                infant_empty_cookie3.setImageResource(R.drawable.img_infant_full_cookie)
            }
            if(count3==3){
                infant_empty_cookie3.setImageResource(R.drawable.img_infant_full_cookie)
                cookieGiftCount1.text = cookieCount.toString()
                intent1.putExtra("cookieCount",cookieCount) //줄어든 쿠키갯수 받기
                //mediaPlayer!!.stop()
                startActivity(intent1)
                overridePendingTransition(0, 0)
            }
        }

        // 홈화면으로 돌아가기
        //val intentGoHome = Intent(this, InfantHomeActivity::class.java)
        infant_icon_gift_out1.setOnClickListener{
            intentGoHome.putExtra("bgSelect",bgSelect)
            intentGoHome.putExtra("chSelect",chSelect)
            intentGoHome.putExtra("cookieCount",cookieCount)
            intentGoHome.putExtra("giftSelect",giftSelect)
            intentGoHome.putExtra("musicPlay",musicPlay)
            //mediaPlayer!!.stop()
            startActivity(intentGoHome)
            overridePendingTransition(0, 0)
        }

    }

    // 로티 적용
    private fun setOnLottieStart() {
        infant_gift_char.repeatMode = LottieDrawable.REVERSE
        infant_gift_char.repeatCount = LottieDrawable.INFINITE
        infant_gift_char.playAnimation()
    }

}