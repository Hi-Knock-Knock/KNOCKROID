package com.all_the_best.knock_knock.infant.deco.view

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.all_the_best.knock_knock.R
import com.all_the_best.knock_knock.infant.home.view.InfantHomeActivity
import kotlinx.android.synthetic.main.activity_infant_deco.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class InfantDecoActivity : AppCompatActivity() {

       override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_infant_deco)

        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ISO_LOCAL_TIME
        val formatted = current.format(formatter)

        when(formatted) {
            in "08:00:000".."13:59:999" -> {
                infant_deco.setBackgroundResource(R.drawable.img_infant_deco_morning_bg)
                window.statusBarColor = Color.parseColor("#57DDFF")
            }
            in "14:00:000".."19:59:999" -> {
                infant_deco.setBackgroundResource(R.drawable.img_infant_deco_after_bg)
                window.statusBarColor = Color.parseColor("#FF6471")
            }
            in "20:00:00".."23:59:999" -> {
                infant_deco.setBackgroundResource(R.drawable.img_infant_deco_night_bg)
                window.statusBarColor = Color.parseColor("#0F0E15")
            }
            !in "08:00:00".."23:59:999" -> {
                infant_deco.setBackgroundResource(R.drawable.img_infant_deco_night_bg)
                window.statusBarColor = Color.parseColor("#0F0E15")
            }
        }


        val intent = Intent(this, InfantHomeActivity::class.java)
        infant_icon_deco_out1.setOnClickListener{
            startActivity(intent)
            overridePendingTransition(0, 0)
        }

        }
 }