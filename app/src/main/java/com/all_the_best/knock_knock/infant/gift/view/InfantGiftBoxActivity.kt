package com.all_the_best.knock_knock.infant.gift.view

import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.InsetDrawable
import android.opengl.Visibility
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.databinding.DataBindingUtil
import com.airbnb.lottie.LottieDrawable
import com.all_the_best.knock_knock.R
import com.all_the_best.knock_knock.databinding.ActivityInfantGetGiftPopupBinding
import com.all_the_best.knock_knock.infant.home.view.InfantHomeActivity
import kotlinx.android.synthetic.main.activity_infant_get_gift_popup.*
import kotlinx.android.synthetic.main.activity_infant_gift_box.*


class InfantGiftBoxActivity : AppCompatActivity() {
    private var bgSelect: Int = 1
    private var chSelect: Int = 0
    private var cookieCount: Int = 5
    private var giftSelect: Int = 0
    private var musicPlay:Int=0
   // private lateinit var getBgPopup: Dialog
   // private lateinit var getBgPopup2: Dialog
    //private lateinit var getGiftPopupBinding: ActivityInfantGetGiftPopupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_infant_gift_box)
        setOnLottieStart()
        val cookieGiftCount2: TextView = findViewById(R.id.infant_cookie_count_box)
        bgSelect = intent.getIntExtra("bgSelect", 1)
        chSelect = intent.getIntExtra("chSelect", 0)
        cookieCount = intent.getIntExtra("cookieCount", 5)
        giftSelect = intent.getIntExtra("giftSelect", 0)
        musicPlay = intent.getIntExtra("musicPlay", 0)

        cookieGiftCount2.text = cookieCount.toString()

        window.statusBarColor = Color.parseColor("#8A2A6C")
        //getGiftPopupBinding = ActivityInfantGetGiftPopupBinding.inflate(layoutInflater)
//        val view = getGiftPopupBinding.root
//        setContentView(view)

        // 홈으로 돌아가기
        val intentGoHome = Intent(this, InfantHomeActivity::class.java)
        infant_icon_gift_out2.setOnClickListener {
            intentGoHome.putExtra("bgSelect", bgSelect)
            intentGoHome.putExtra("chSelect", chSelect)
            intentGoHome.putExtra("cookieCount", cookieCount)
            intentGoHome.putExtra("giftSelect", giftSelect)
            intentGoHome.putExtra("musicPlay", musicPlay)
            startActivity(intentGoHome)
            finish()
            overridePendingTransition(0, 0)
        }

        // 선물 받는 팝업창
        infant_gift_box.setOnClickListener {
            when (giftSelect) {
                0 -> {
                    giftSelect = 1
                    // 배경 얻는 팝업창
                    var getBgPopup: Dialog = Dialog(this)
                    getBgPopup?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                    getBgPopup.setContentView(R.layout.activity_infant_get_gift_popup)
                    getBgPopup.window!!.attributes.width = WindowManager.LayoutParams.MATCH_PARENT
                    getBgPopup.window!!.attributes.height = WindowManager.LayoutParams.MATCH_PARENT
                    getBgPopup.show()
                    intentGoHome.putExtra("bgSelect", bgSelect)
                    intentGoHome.putExtra("chSelect", chSelect)
                    intentGoHome.putExtra("cookieCount", cookieCount)
                    intentGoHome.putExtra("giftSelect", giftSelect)
                    intentGoHome.putExtra("musicPlay",musicPlay)
                    Handler(Looper.getMainLooper()).postDelayed({
                        startActivity(intentGoHome)
                        getBgPopup.dismiss()
                        overridePendingTransition(0, 0)
                    }, 2000)
                }
                1 -> {
                    giftSelect = 2
                    // 배경 얻는 팝업창
                    var getBgPopup2: Dialog = Dialog(this)
                    getBgPopup2?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                    getBgPopup2.setContentView(R.layout.activity_infant_get_gift_popup_two)
                    getBgPopup2.window!!.attributes.width = WindowManager.LayoutParams.MATCH_PARENT
                    getBgPopup2.window!!.attributes.height = WindowManager.LayoutParams.MATCH_PARENT
                    getBgPopup2.show()
                    intentGoHome.putExtra("bgSelect", bgSelect)
                    intentGoHome.putExtra("chSelect", chSelect)
                    intentGoHome.putExtra("cookieCount", cookieCount)
                    intentGoHome.putExtra("giftSelect", giftSelect)
                    intentGoHome.putExtra("musicPlay",musicPlay)
                    Handler(Looper.getMainLooper()).postDelayed({
                        startActivity(intentGoHome)
                        getBgPopup2.dismiss()
                        overridePendingTransition(0, 0)
                    }, 2000)
                }
            }
        }
    }

    override fun onBackPressed(){
        Log.d("backpress","막음")
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