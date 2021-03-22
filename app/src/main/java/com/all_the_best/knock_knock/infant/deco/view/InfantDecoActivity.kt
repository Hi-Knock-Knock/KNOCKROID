package com.all_the_best.knock_knock.infant.deco.view

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.observe
import com.all_the_best.knock_knock.R
import com.all_the_best.knock_knock.infant.deco.viewmodel.InfantDecoViewModel
import com.all_the_best.knock_knock.infant.home.view.InfantHomeActivity
import kotlinx.android.synthetic.main.activity_infant_deco.*
import kotlinx.android.synthetic.main.activity_infant_deco.char_dam
import kotlinx.android.synthetic.main.activity_infant_select_feel.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class InfantDecoActivity : AppCompatActivity() {
    private val infantDecoViewModel: InfantDecoViewModel by viewModels()
    private var bgSelect: Int = 1
    private var chSelect: Int = 0
    private var cookieCount: Int = 5

    private val current = LocalDateTime.now()
    private val formatter = DateTimeFormatter.ISO_LOCAL_TIME
    private val formatted = current.format(formatter)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_infant_deco)
        bgSelect = intent.getIntExtra("bgSelect",1)
        chSelect = intent.getIntExtra("chSelect",0)
        cookieCount = intent.getIntExtra("cookieCount",5)
        setBgSelectObserve()
        setSelectCharacter()
        setDecoItemSelectedListener()
        setOnClickListenerForGoBack()
    }

    private fun setOnClickListenerForGoBack() {
        infant_icon_deco_out1. setOnClickListener {
            val intent = Intent()
            intent.putExtra("bgSelect", infantDecoViewModel.bgSelect.value)
            intent.putExtra("chSelect",chSelect)
            intent.putExtra("cookieCount",cookieCount)
            setResult(Activity.RESULT_OK, intent)
            finish()
            overridePendingTransition(0, 0)
        }
    }


    private fun setSelectCharacter(){
        when(chSelect){
            0 -> char_dam.setImageResource(R.drawable.img_char_dam)
            1 -> char_dam.setImageResource(R.drawable.img_char_knock)
            2 -> char_dam.setImageResource(R.drawable.img_char_timi)
        }
    }

    private fun setDecoItemSelectedListener() {
        deco_item_bg1.setOnClickListener {
            infantDecoViewModel.setBgSelect(1)
            deco_item_bg1.setBackgroundResource(R.drawable.bg_deco_select_item)
            deco_item_bg2.setBackgroundResource(R.drawable.bg_deco_unselect_item)
            deco_item_bg3.setBackgroundResource(R.drawable.bg_deco_unselect_item)
        }
        deco_item_bg2.setOnClickListener {
            infantDecoViewModel.setBgSelect(2)
            deco_item_bg1.setBackgroundResource(R.drawable.bg_deco_unselect_item)
            deco_item_bg2.setBackgroundResource(R.drawable.bg_deco_select_item)
            deco_item_bg3.setBackgroundResource(R.drawable.bg_deco_unselect_item)
        }
        deco_item_bg3.setOnClickListener {
            infantDecoViewModel.setBgSelect(3)
            deco_item_bg1.setBackgroundResource(R.drawable.bg_deco_unselect_item)
            deco_item_bg2.setBackgroundResource(R.drawable.bg_deco_unselect_item)
            deco_item_bg3.setBackgroundResource(R.drawable.bg_deco_select_item)
        }
    }

    private fun setBgSelectObserve() {
        infantDecoViewModel.bgSelect.observe(this) { bgSelect ->
            bgSelect.let {
                Log.d("home_deco", "$bgSelect")
                when (formatted) {
                    in "08:00:000".."13:59:999" -> {
                        window.statusBarColor = Color.parseColor("#57DDFF")
                        when (bgSelect) {
                            1 -> infant_deco.setBackgroundResource(R.drawable.img_infant_deco_morning_bg)
                            2 -> infant_deco.setBackgroundResource(R.drawable.img_infant_deco_bg_flower1)
                            3 -> infant_deco.setBackgroundResource(R.drawable.img_infant_deco_bg_sea1)
                        }
                    }
                    in "14:00:000".."19:59:999" -> {
                        window.statusBarColor = Color.parseColor("#FF6471")
                        when (bgSelect) {
                            1 -> infant_deco.setBackgroundResource(R.drawable.img_infant_deco_after_bg)
                            2 -> infant_deco.setBackgroundResource(R.drawable.img_infant_deco_bg_flower2)
                            3 -> infant_deco.setBackgroundResource(R.drawable.img_infant_deco_bg_sea2)
                        }
                    }
                    in "20:00:00".."23:59:999" -> {
                        window.statusBarColor = Color.parseColor("#0F0E15")
                        when (bgSelect) {
                            1 -> infant_deco.setBackgroundResource(R.drawable.img_infant_deco_night_bg)
                            2 -> infant_deco.setBackgroundResource(R.drawable.img_infant_deco_bg_flower3)
                            3 -> infant_deco.setBackgroundResource(R.drawable.img_infant_deco_bg_sea3)
                        }
                    }
                    !in "08:00:00".."23:59:999" -> {
                        window.statusBarColor = Color.parseColor("#0F0E15")
                        when (bgSelect) {
                            1 -> infant_deco.setBackgroundResource(R.drawable.img_infant_deco_night_bg)
                            2 -> infant_deco.setBackgroundResource(R.drawable.img_infant_deco_bg_flower3)
                            3 -> infant_deco.setBackgroundResource(R.drawable.img_infant_deco_bg_sea3)
                        }
                    }
                }
            }
        }
    }
}