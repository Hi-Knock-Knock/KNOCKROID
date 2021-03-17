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
           var bg_select : Int? = null

           deco_item_bg1.setOnClickListener {
               bg_select = 1
               deco_item_bg1.setBackgroundResource(R.drawable.bg_deco_select_item)
               deco_item_bg2.setBackgroundResource(R.drawable.bg_deco_unselect_item)
               deco_item_bg3.setBackgroundResource(R.drawable.bg_deco_unselect_item)
           }
           deco_item_bg2.setOnClickListener {
               bg_select = 2
               deco_item_bg1.setBackgroundResource(R.drawable.bg_deco_unselect_item)
               deco_item_bg2.setBackgroundResource(R.drawable.bg_deco_select_item)
               deco_item_bg3.setBackgroundResource(R.drawable.bg_deco_unselect_item)
           }
           deco_item_bg3.setOnClickListener {
               bg_select = 3
               deco_item_bg1.setBackgroundResource(R.drawable.bg_deco_unselect_item)
               deco_item_bg2.setBackgroundResource(R.drawable.bg_deco_unselect_item)
               deco_item_bg3.setBackgroundResource(R.drawable.bg_deco_select_item)
           }

           when(formatted) {
               in "08:00:000".."13:59:999" -> {
                   window.statusBarColor = Color.parseColor("#57DDFF")
                   when(bg_select){
                       1 -> {infant_deco.setBackgroundResource(R.drawable.img_infant_deco_morning_bg)}
                       2 -> {infant_deco.setBackgroundResource(R.drawable.img_infant_deco_bg_flower1)}
                       3 -> {infant_deco.setBackgroundResource(R.drawable.img_infant_deco_bg_sea1)}
                   }
               }
               in "14:00:000".."19:59:999" -> {
                   window.statusBarColor = Color.parseColor("#FF6471")
                   when(bg_select){
                       1 -> {infant_deco.setBackgroundResource(R.drawable.img_infant_deco_after_bg)}
                       2 -> {infant_deco.setBackgroundResource(R.drawable.img_infant_deco_bg_flower2)}
                       3 -> {infant_deco.setBackgroundResource(R.drawable.img_infant_deco_bg_sea2)}
                   }
               }
               in "20:00:00".."23:59:999" -> {
                   window.statusBarColor = Color.parseColor("#0F0E15")
                   when(bg_select){
                       1 -> {infant_deco.setBackgroundResource(R.drawable.img_infant_deco_night_bg)}
                       2 -> {infant_deco.setBackgroundResource(R.drawable.img_infant_deco_bg_flower3)}
                       3 -> {infant_deco.setBackgroundResource(R.drawable.img_infant_deco_bg_sea3)}
                   }
               }
               !in "08:00:00".."23:59:999" -> {
                   window.statusBarColor = Color.parseColor("#0F0E15")
                   when(bg_select){
                       1 -> {infant_deco.setBackgroundResource(R.drawable.img_infant_deco_night_bg)}
                       2 -> {infant_deco.setBackgroundResource(R.drawable.img_infant_deco_bg_flower3)}
                       3 -> {infant_deco.setBackgroundResource(R.drawable.img_infant_deco_bg_sea3)}
                   }
               }
           }


           val intent = Intent(this, InfantHomeActivity::class.java)
           infant_icon_deco_out1.setOnClickListener{
               startActivity(intent)
               overridePendingTransition(0, 0)
           }
       }
}