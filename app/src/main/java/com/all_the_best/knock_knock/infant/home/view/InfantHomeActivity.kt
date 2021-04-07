
package com.all_the_best.knock_knock.infant.home.view

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.lottie.LottieDrawable
import com.all_the_best.knock_knock.R
import com.all_the_best.knock_knock.infant.change.view.InfantSwitchCharacterActivity
import com.all_the_best.knock_knock.infant.cookie.view.InfantCookieSaveActivity
import com.all_the_best.knock_knock.infant.deco.view.InfantDecoActivity
import com.all_the_best.knock_knock.infant.gift.view.InfantGiftStartActivity
import com.all_the_best.knock_knock.infant.talk.view.InfantSelectFeelActivity
import kotlinx.android.synthetic.main.activity_infant_deco.*
import kotlinx.android.synthetic.main.activity_infant_home.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class InfantHomeActivity : AppCompatActivity() {

    private var chSelect: Int = 0
    private var bgSelect: Int = 1
    private var cookieCount: Int = 5
    private var giftSelect:Int=0
    private var lottieSelect:Int=0

    var time3: Long = 0
    private val current = LocalDateTime.now()
    private val formatter = DateTimeFormatter.ISO_LOCAL_TIME
    private val formatted = current.format(formatter)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_infant_home)

        bgSelect = intent.getIntExtra("bgSelect",1)
        chSelect = intent.getIntExtra("chSelect",0)
        cookieCount = intent.getIntExtra("cookieCount",5)
        giftSelect = intent.getIntExtra("giftSelect",0)
        lottieSelect = intent.getIntExtra("lottieSelect",0)

        setBackgroundForTime()
        setSelectCharacter()

        infant_talk1.setOnClickListener{
            Log.d("time", formatted)
        }

        //선물상자에서 줄어든 쿠키 갯수 반영
        val cookieCountHome: TextView = findViewById(R.id.infant_home_cookie_count_txt)
        cookieCountHome.text = cookieCount.toString()

        //대화하기 버튼
        val intent1 = Intent(this, InfantSelectFeelActivity::class.java)
        char_talk_btn.setOnClickListener{
            intent1.putExtra("bgSelect", bgSelect)
            intent1.putExtra("cookieCount",cookieCount)
            intent1.putExtra("lottieSelect",lottieSelect)
            intent1.putExtra("chSelect",chSelect)
            intent1.putExtra("giftSelect",giftSelect)
            startActivity(intent1)
        }

        // 캐릭터 바꾸기 버튼
        val intent2 = Intent(this, InfantSwitchCharacterActivity::class.java)
        char_change_btn.setOnClickListener{
            intent2.putExtra("bgSelect",bgSelect)
            intent2.putExtra("cookieCount",cookieCount)
            intent2.putExtra("giftSelect",giftSelect)
            intent2.putExtra("lottieSelect",lottieSelect)
            startActivityForResult(intent2,0)
        }

        // 선물상자 버튼
        val intent3 = Intent(this, InfantGiftStartActivity::class.java)
        infant_icon_gift.setOnClickListener{
            intent3.putExtra("bgSelect",bgSelect)
            intent3.putExtra("cookieCount",cookieCount)
            intent3.putExtra("chSelect",chSelect)
            intent3.putExtra("giftSelect",giftSelect)
            intent3.putExtra("lottieSelect",lottieSelect)
            startActivity(intent3)
        }

        // 배경 꾸미기 버튼
        val intent4 = Intent(this, InfantDecoActivity::class.java)
        char_deco_btn.setOnClickListener{
            intent4.putExtra("cookieCount",cookieCount)
            intent4.putExtra("chSelect",chSelect)
            intent4.putExtra("giftSelect",giftSelect)
            intent4.putExtra("lottieSelect",lottieSelect)
            startActivityForResult(intent4,1)
        }

        // 쿠키 저장 다람이 버튼
        val intent5 = Intent(this, InfantCookieSaveActivity::class.java)
        infant_cookie_view.setOnClickListener{
            intent5.putExtra("bgSelect",bgSelect)
            intent5.putExtra("cookieCount",cookieCount)
            intent5.putExtra("chSelect",chSelect)
            intent5.putExtra("giftSelect",giftSelect)
            intent5.putExtra("lottieSelect",lottieSelect)
            startActivity(intent5)
        }

    }

//    override fun onBackPressed() {
//        val time1 = System.currentTimeMillis()
//        val time2 = time1 - time3
//        if (time2 in 0..2000) {
//            finish()
//        }
//        else {
//            time3 = time1
//            //Toast.makeText(applicationContext, "한번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT).show()
//        }
//    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 1){
            if (resultCode==RESULT_OK) {
                bgSelect = data!!.getIntExtra("bgSelect",1)
                setBackgroundForTime()
                Log.d("home", "$bgSelect")
            }else{
            }
        }else if(requestCode == 0){
            if (resultCode==RESULT_OK) {
                chSelect = data!!.getIntExtra("chSelect",0)
                setBackgroundForTime()
                Log.d("home", "$chSelect")
            }else{
            }
        }
    }

    private fun setBackgroundForTime(){
        when(formatted){
            in "08:00:000".."13:59:999" -> {
                window.statusBarColor = Color.parseColor("#57DDFF")         //상태바 색상 지정
                when (bgSelect) {
                    1 -> infant_home.setBackgroundResource(R.drawable.img_infant_home_morning_bg)
                    2 -> infant_home.setBackgroundResource(R.drawable.img_infant_home_bg_flower1)
                    3 -> infant_home.setBackgroundResource(R.drawable.img_infant_home_bg_sea1)
                    4 -> infant_home.setBackgroundResource(R.drawable.img_infant_home_bg_space1) // 우주
                }
                char_deco_btn.setImageResource(R.drawable.ic_infant_deco_btn_1)
                char_talk_btn.setImageResource(R.drawable.ic_infant_chat_btn_1)
                char_change_btn.setImageResource(R.drawable.ic_infant_change_btn_1)
                when(bgSelect){
                    4-> {
                        window.statusBarColor = Color.parseColor("#0F0E15")
                        char_deco_btn.setImageResource(R.drawable.ic_infant_deco_btn_3)
                        char_talk_btn.setImageResource(R.drawable.ic_infant_chat_btn_3)
                        char_change_btn.setImageResource(R.drawable.ic_infant_change_btn_3)

                    }
                }

            }
            in "14:00:000".."19:59:999" -> {
                window.statusBarColor = Color.parseColor("#FF6471")
                when (bgSelect) {
                    1 -> infant_home.setBackgroundResource(R.drawable.img_infant_home_after_bg)
                    2 -> infant_home.setBackgroundResource(R.drawable.img_infant_home_bg_flower2)
                    3 -> infant_home.setBackgroundResource(R.drawable.img_infant_home_bg_sea2)
                    4 -> infant_home.setBackgroundResource(R.drawable.img_infant_home_bg_space2) // 우주

                }
                char_deco_btn.setImageResource(R.drawable.ic_infant_deco_btn_2)
                char_talk_btn.setImageResource(R.drawable.ic_infant_chat_btn_2)
                char_change_btn.setImageResource(R.drawable.ic_infant_change_btn_2)
                when(bgSelect){
                    4-> {
                        window.statusBarColor = Color.parseColor("#0F0E15")
                        char_deco_btn.setImageResource(R.drawable.ic_infant_deco_btn_3)
                        char_talk_btn.setImageResource(R.drawable.ic_infant_chat_btn_3)
                        char_change_btn.setImageResource(R.drawable.ic_infant_change_btn_3)
                    }
                }

            }
            in "20:00:00".."23:59:999" -> {
                when(bgSelect){
                    1 -> infant_home.setBackgroundResource(R.drawable.img_infant_home_night_bg)
                    2 -> infant_home.setBackgroundResource(R.drawable.img_infant_home_bg_flower3)
                    3 -> infant_home.setBackgroundResource(R.drawable.img_infant_home_bg_sea3)
                    4 -> infant_home.setBackgroundResource(R.drawable.img_infant_home_bg_space3) // 우주
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
                    4 -> infant_home.setBackgroundResource(R.drawable.img_infant_home_bg_space3) // 우주
                }
                char_deco_btn.setImageResource(R.drawable.ic_infant_deco_btn_3)
                char_talk_btn.setImageResource(R.drawable.ic_infant_chat_btn_3)
                char_change_btn.setImageResource(R.drawable.ic_infant_change_btn_3)
                window.statusBarColor = Color.parseColor("#0F0E15")
            }
        }
    }

    private fun setSelectCharacter(){
        when(chSelect){
            0 -> {char_img.setAnimation("dami_idle.json")
                char_img.setOnClickListener{
                    char_img.setAnimation("dami_hi.json")
                    char_img.repeatMode = LottieDrawable.REVERSE
                    char_img.repeatCount = LottieDrawable.INFINITE
                    char_img.playAnimation()
                }
            } // 담이 idle
            1 -> char_img.setAnimation("dami_ear.json") //녹녹이 idle로 바꾸기
            2 -> char_img.setAnimation("dami_hi.json") // 티미 idle로 바꾸기
        }
        setOnMotionStart()
    }

    // lottie -> 캐릭터 change가 되면 애니메이션이 움직이도록 해야되니까...이미지를 다 애니메이션으로 바꿔야되는ㅋㅋ ..
    private fun setOnMotionStart(){
        char_img.repeatMode = LottieDrawable.REVERSE
        char_img.repeatCount = LottieDrawable.INFINITE
        char_img.playAnimation()
    }
}
