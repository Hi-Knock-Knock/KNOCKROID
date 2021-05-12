package com.all_the_best.knock_knock.infant.gift.view

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.WindowManager
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import com.airbnb.lottie.LottieDrawable
import com.all_the_best.knock_knock.R
import com.all_the_best.knock_knock.infant.gift.viewmodel.InfantGiftBgViewModel
import com.all_the_best.knock_knock.infant.home.view.InfantHomeActivity
import kotlinx.android.synthetic.main.activity_infant_gift_box.*
import kotlinx.android.synthetic.main.activity_infant_gift_box.infant_gift_char
import kotlinx.android.synthetic.main.activity_infant_gift_start.*
import kotlinx.android.synthetic.main.infant_gift_get_popup.*

class InfantGiftBoxActivity : AppCompatActivity() {
    //var mediaPlayer: MediaPlayer? = null
    private var bgSelect: Int = 1
    private var chSelect: Int = 0
    private var cookieCount: Int = 5
    private var giftSelect: Int = 0
    private var musicPlay:Int=0
    private lateinit var getBgPopup: Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_infant_gift_box)
        setOnLottieStart()
//        mediaPlayer = MediaPlayer.create(this, R.raw.bgm2);
//        mediaPlayer!!.setVolume(5f,5f)
//        mediaPlayer!!.start()
        val cookieGiftCount2: TextView = findViewById(R.id.infant_cookie_count_box)

        bgSelect = intent.getIntExtra("bgSelect", 1)
        chSelect = intent.getIntExtra("chSelect", 0)
        cookieCount = intent.getIntExtra("cookieCount", 5)
        giftSelect = intent.getIntExtra("giftSelect", 0)
        musicPlay = intent.getIntExtra("musicPlay",0)

        cookieGiftCount2.text = cookieCount.toString()

        window.statusBarColor = Color.parseColor("#8A2A6C")

        // 홈으로 돌아가기
        val intentGoHome = Intent(this, InfantHomeActivity::class.java)
        infant_icon_gift_out2.setOnClickListener {
            intentGoHome.putExtra("bgSelect", bgSelect)
            intentGoHome.putExtra("chSelect", chSelect)
            intentGoHome.putExtra("cookieCount", cookieCount)
            intentGoHome.putExtra("giftSelect", giftSelect)
            intentGoHome.putExtra("musicPlay",musicPlay)
            //mediaPlayer!!.stop()
            startActivity(intentGoHome)
            overridePendingTransition(0, 0)
        }

        // 선물 받는 팝업창
        infant_gift_box.setOnClickListener {
            // 배경 얻는 팝업창
            getBgPopup = Dialog(this)
            // Dialog 사이즈 조절 하기
            //LayoutParams params = getBgPopup.getWindow().getAttributes();

            getBgPopup?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            //getBgPopup.getWindow().setAttributes((android.view.WindowManager.LayoutParams) params);
            getBgPopup.setContentView(R.layout.activity_infant_get_gift_popup)
            getBgPopup.window!!.attributes.width = WindowManager.LayoutParams.MATCH_PARENT
            getBgPopup.window!!.attributes.height = WindowManager.LayoutParams.MATCH_PARENT
            when (giftSelect) {
                0 -> {
                    giftSelect = 1
                }
                1 -> {
                    giftSelect = 2
//                    img_get_item.setImageResource(R.drawable.img_infant_deco_item_space)
//                    txt_get_bg.text = "우주를 받았어!!"
                }
            }
            getBgPopup.show()
            intentGoHome.putExtra("bgSelect", bgSelect)
            intentGoHome.putExtra("chSelect", chSelect)
            intentGoHome.putExtra("cookieCount", cookieCount)
            intentGoHome.putExtra("giftSelect", giftSelect)
            intentGoHome.putExtra("musicPlay",musicPlay)
            Handler(Looper.getMainLooper()).postDelayed({
                finish()
                startActivity(intentGoHome)
                overridePendingTransition(0, 0)
            }, 2000)

        }
    }

    // 로티 적용
    private fun setOnLottieStart() {
        infant_gift_char.repeatMode = LottieDrawable.REVERSE
        infant_gift_char.repeatCount = LottieDrawable.INFINITE
        infant_gift_box.repeatMode = LottieDrawable.REVERSE
        infant_gift_box.repeatCount = 0
        infant_gift_box.playAnimation()
        infant_gift_char.playAnimation()
    }

//    override fun onDestroy() {
//        super.onDestroy()
//        getBgPopup.dismiss()
//    }
}