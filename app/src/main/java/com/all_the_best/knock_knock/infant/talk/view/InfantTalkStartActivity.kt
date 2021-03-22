package com.all_the_best.knock_knock.infant.talk.view

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.all_the_best.knock_knock.R
import com.all_the_best.knock_knock.infant.cookie.view.InfantGetCookiePopupActivity
import com.all_the_best.knock_knock.infant.home.view.InfantHomeActivity
import kotlinx.android.synthetic.main.activity_infant_home.*
import kotlinx.android.synthetic.main.activity_infant_talk_start.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class InfantTalkStartActivity : AppCompatActivity() {
    private var bgSelect: Int = 1
    private var chSelect: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_infant_talk_start)
        bgSelect = intent.getIntExtra("bgSelect",1)
        chSelect = intent.getIntExtra("chSelect",0)
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
            intent1.putExtra("bgSelect", bgSelect)
            intent1.putExtra("chSelect",chSelect)
            startActivity(intent1)
            overridePendingTransition(0, 0)
        }

        val intent2 = Intent(this, InfantGetCookiePopupActivity::class.java)
        talk_start_char_dam.setOnClickListener{
            intent2.putExtra("chSelect",chSelect)
            startActivity(intent2)
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