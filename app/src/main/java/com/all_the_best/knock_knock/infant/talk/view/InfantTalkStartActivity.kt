package com.all_the_best.knock_knock.infant.talk.view

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import com.all_the_best.knock_knock.R
import com.all_the_best.knock_knock.infant.cookie.view.InfantGetCookiePopupActivity
import com.all_the_best.knock_knock.infant.home.view.InfantHomeActivity
import com.all_the_best.knock_knock.parent.base.view.LoginActivity
import kotlinx.android.synthetic.main.activity_infant_home.*
import kotlinx.android.synthetic.main.activity_infant_talk_start.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class InfantTalkStartActivity : AppCompatActivity() {

    private var bgSelect: Int = 1
    private var chSelect: Int = 0
    private var cookieCount: Int = 5

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_infant_talk_start)
        bgSelect = intent.getIntExtra("bgSelect",1)
        chSelect = intent.getIntExtra("chSelect",0)
        cookieCount = intent.getIntExtra("cookieCount",5)

        setSelectCharacter()
        window.statusBarColor = Color.parseColor("#FCC364")

        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ISO_LOCAL_TIME

        when(current.format(formatter)){
            in "08:00:000".."13:59:999" -> {
                infant_talk_start.setBackgroundResource(R.drawable.img_infant_room_morning_bg)
            }
            in "14:00:000".."19:59:999" -> {
                infant_talk_start.setBackgroundResource(R.drawable.img_infant_room_after_bg)
            }
            in "20:00:00".."23:59:999" -> {
                infant_talk_start.setBackgroundResource(R.drawable.img_infant_room_night_bg)
            }
            !in "08:00:00".."23:59:999" -> {
                infant_talk_start.setBackgroundResource(R.drawable.img_infant_room_night_bg)
            }
        }

        val intent1 = Intent(this, InfantHomeActivity::class.java)
        infant_icon_out.setOnClickListener{
            // 쿠키 받는 팝업
            val cookiePopUp = Dialog(this)
            cookiePopUp?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            cookiePopUp.setContentView(R.layout.activity_infant_get_cookie_popup)
            cookiePopUp.show()
            cookieCount += 1
            intent1.putExtra("bgSelect", bgSelect)
            intent1.putExtra("chSelect",chSelect)
            intent1.putExtra("cookieCount",cookieCount)
            Handler(Looper.getMainLooper()).postDelayed ({
                startActivity(intent1)
                finish()
            }, 2000)
            overridePendingTransition(0, 0)
        }
    }
    private fun setSelectCharacter(){
        when(chSelect){
            0 -> talk_start_char_dam.setImageResource(R.drawable.img_char_dam)
            1 -> talk_start_char_dam.setImageResource(R.drawable.img_char_knock)
            2 -> talk_start_char_dam.setImageResource(R.drawable.img_char_timi)
        }
    }
}