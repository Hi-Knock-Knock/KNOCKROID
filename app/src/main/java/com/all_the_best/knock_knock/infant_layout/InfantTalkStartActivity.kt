package com.all_the_best.knock_knock.infant_layout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.all_the_best.knock_knock.R
import kotlinx.android.synthetic.main.activity_infant_home.*
import kotlinx.android.synthetic.main.activity_infant_select_person.*
import kotlinx.android.synthetic.main.activity_infant_talk_start.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class InfantTalkStartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_infant_talk_start)

        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ISO_LOCAL_TIME

        when(current.format(formatter)){
            in "08:00:000".."13:59:999" -> {
                infant_talk_start.setBackgroundResource(R.drawable.img_infant_room_morning_bg)
            }
            in "14:00:000".."19:59:999" -> {
                infant_talk_start.setBackgroundResource(R.drawable.img_infant_room_sunset_bg)
            }
            in "20:00:00".."23:59:999" -> {
                infant_talk_start.setBackgroundResource(R.drawable.img_infant_room_night_bg)
            }
            !in "08:00:00".."23:59:999" -> {
                infant_talk_start.setBackgroundResource(R.drawable.img_infant_room_night_bg)
            }
        }


        val intent = Intent(this, InfantHomeActivity::class.java)
        infant_icon_out.setOnClickListener{
            startActivity(intent)
        }
    }
}