
package com.all_the_best.knock_knock.infant.home.view

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.all_the_best.knock_knock.R
import com.all_the_best.knock_knock.infant.change.view.InfantSwitchCharacterActivity
import com.all_the_best.knock_knock.infant.cookie.view.InfantCookieSaveActivity
import com.all_the_best.knock_knock.infant.deco.view.InfantDecoActivity
import com.all_the_best.knock_knock.infant.gift.view.InfantGiftStartActivity
import com.all_the_best.knock_knock.infant.talk.view.InfantSelectFeelActivity
import kotlinx.android.synthetic.main.activity_infant_home.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class InfantHomeActivity : AppCompatActivity() {
    private var bgSelect: Int = 1
    private val current = LocalDateTime.now()
    private val formatter = DateTimeFormatter.ISO_LOCAL_TIME
    private val formatted = current.format(formatter)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_infant_home)
        bgSelect = intent.getIntExtra("bgSelect",1)
        setBackgroundForTime()
        //상태바 색상 지정

        infant_talk1.setOnClickListener{
            Log.d("time", formatted)
        }
// lottie
//        char_dam.repeatMode = LottieDrawable.REVERSE
//        char_dam.repeatCount = LottieDrawable.INFINITE
//        char_dam.playAnimation()

        val intent1 = Intent(this, InfantSelectFeelActivity::class.java)
        char_talk_btn.setOnClickListener{
            intent1.putExtra("bgSelect", bgSelect)
            startActivity(intent1)
        }
        val intent2 = Intent(this, InfantSwitchCharacterActivity::class.java)
        char_change_btn.setOnClickListener{
            intent2.putExtra("bgSelect",bgSelect)
            startActivity(intent2)
        }

        val intent3 = Intent(this, InfantGiftStartActivity::class.java)
        infant_icon_gift.setOnClickListener{
            intent3.putExtra("bgSelect",bgSelect)
            startActivity(intent3)
        }

        val intent4 = Intent(this, InfantDecoActivity::class.java)
        char_deco_btn.setOnClickListener{
            startActivityForResult(intent4,1)
        }

        val intent5 = Intent(this, InfantCookieSaveActivity::class.java)
        infant_cookie_view.setOnClickListener{
            intent5.putExtra("bgSelect",bgSelect)
            startActivity(intent5)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 1){
            if (resultCode==RESULT_OK) {
                bgSelect = data!!.getIntExtra("bgSelect",1)
                setBackgroundForTime()
                Log.d("home", "$bgSelect")
            }else{
            }
        }
    }

    private fun setBackgroundForTime(){
        when(formatted){
            in "08:00:000".."13:59:999" -> {
                when (bgSelect) {
                    1 -> infant_home.setBackgroundResource(R.drawable.img_infant_home_morning_bg)
                    2 -> infant_home.setBackgroundResource(R.drawable.img_infant_home_bg_flower1)
                    3 -> infant_home.setBackgroundResource(R.drawable.img_infant_home_bg_sea1)
                }
                char_deco_btn.setImageResource(R.drawable.ic_infant_deco_btn_1)
                char_talk_btn.setImageResource(R.drawable.ic_infant_chat_btn_1)
                char_change_btn.setImageResource(R.drawable.ic_infant_change_btn_1)
                window.statusBarColor = Color.parseColor("#57DDFF")
            }
            in "14:00:000".."19:59:999" -> {
                when (bgSelect) {
                    1 -> infant_home.setBackgroundResource(R.drawable.img_infant_home_after_bg)
                    2 -> infant_home.setBackgroundResource(R.drawable.img_infant_home_bg_flower2)
                    3 -> infant_home.setBackgroundResource(R.drawable.img_infant_home_bg_sea2)
                }
                char_deco_btn.setImageResource(R.drawable.ic_infant_deco_btn_2)
                char_talk_btn.setImageResource(R.drawable.ic_infant_chat_btn_2)
                char_change_btn.setImageResource(R.drawable.ic_infant_change_btn_2)
                window.statusBarColor = Color.parseColor("#FF6471")
            }
            in "20:00:00".."23:59:999" -> {
                when(bgSelect){
                    1 -> infant_home.setBackgroundResource(R.drawable.img_infant_home_night_bg)
                    2 -> infant_home.setBackgroundResource(R.drawable.img_infant_home_bg_flower3)
                    3 -> infant_home.setBackgroundResource(R.drawable.img_infant_home_bg_sea3)
                }
                char_deco_btn.setImageResource(R.drawable.ic_infant_deco_btn_3)
                char_talk_btn.setImageResource(R.drawable.ic_infant_chat_btn_3)
                char_change_btn.setImageResource(R.drawable.ic_infant_change_btn_3)
                window.statusBarColor = Color.parseColor("#0F0E15")
            }
            !in "08:00:00".."23:59:999" -> {
                when(bgSelect){
                    1 -> infant_home.setBackgroundResource(R.drawable.img_infant_home_night_bg)
                    2 -> infant_home.setBackgroundResource(R.drawable.img_infant_home_bg_flower3)
                    3 -> infant_home.setBackgroundResource(R.drawable.img_infant_home_bg_sea3)
                }
                char_deco_btn.setImageResource(R.drawable.ic_infant_deco_btn_3)
                char_talk_btn.setImageResource(R.drawable.ic_infant_chat_btn_3)
                char_change_btn.setImageResource(R.drawable.ic_infant_change_btn_3)
                window.statusBarColor = Color.parseColor("#0F0E15")
            }
        }
    }
}
