package com.all_the_best.knock_knock.infant.gift.view

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import com.all_the_best.knock_knock.R
import com.all_the_best.knock_knock.infant.gift.viewmodel.InfantGiftBgViewModel
import com.all_the_best.knock_knock.infant.home.view.InfantHomeActivity
import kotlinx.android.synthetic.main.activity_infant_gift_box.*
import kotlinx.android.synthetic.main.infant_gift_get_popup.*

class InfantGiftBoxActivity : AppCompatActivity() {

    private var bgSelect: Int = 1
    private var chSelect: Int = 0
    private var cookieCount: Int = 5
    private var giftSelect:Int=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_infant_gift_box)
        val cookieGiftCount2:TextView=findViewById(R.id.infant_cookie_count_box)

        bgSelect = intent.getIntExtra("bgSelect",1)
        chSelect = intent.getIntExtra("chSelect",0)
        cookieCount = intent.getIntExtra("cookieCount",5)
        giftSelect = intent.getIntExtra("giftSelect",0)

        cookieGiftCount2.text = cookieCount.toString()

        window.statusBarColor = Color.parseColor("#8A2A6C")

        // 홈으로 돌아가기
        val intentGoHome = Intent(this, InfantHomeActivity::class.java)
        infant_icon_gift_out2.setOnClickListener{
            intentGoHome.putExtra("bgSelect",bgSelect)
            intentGoHome.putExtra("chSelect",chSelect)
            intentGoHome.putExtra("cookieCount",cookieCount)
            intentGoHome.putExtra("giftSelect",giftSelect)

            startActivity(intentGoHome)
            overridePendingTransition(0, 0)
        }

        // 선물 받는 팝업창
        infant_gift_box.setOnClickListener {
            // 배경 얻는 팝업창
            val getBgPopup = Dialog(this)
            // Dialog 사이즈 조절 하기
            //LayoutParams params = getBgPopup.getWindow().getAttributes();

            getBgPopup?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            //getBgPopup.getWindow().setAttributes((android.view.WindowManager.LayoutParams) params);
            getBgPopup.setContentView(R.layout.activity_infant_get_gift_popup)
            getBgPopup.window!!.attributes.width = WindowManager.LayoutParams.MATCH_PARENT
            getBgPopup.window!!.attributes.height = WindowManager.LayoutParams.MATCH_PARENT
            when(giftSelect){
                0->giftSelect=1
                1-> {
                    giftSelect = 2
                    //img_get_item.setImageResource(R.drawable.img_infant_deco_item_space)
                    //txt_get_bg.text = "우주를 받았어!!"
                }
            }
            getBgPopup.show()
            intentGoHome.putExtra("bgSelect",bgSelect)
            intentGoHome.putExtra("chSelect",chSelect)
            intentGoHome.putExtra("cookieCount",cookieCount)
            intentGoHome.putExtra("giftSelect",giftSelect)
            Handler(Looper.getMainLooper()).postDelayed ({
                startActivity(intentGoHome)
                overridePendingTransition(0, 0)
                finish()
            }, 2000)

        }
    }
}