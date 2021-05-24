package com.all_the_best.knock_knock.infant.cookie.view

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.all_the_best.knock_knock.R
import com.all_the_best.knock_knock.infant.home.view.InfantHomeActivity
import com.google.android.material.internal.ContextUtils.getActivity
import kotlinx.android.synthetic.main.activity_infant_cookie_save.*

class InfantCookieSaveActivity : AppCompatActivity() {

    private var bgSelect: Int = 1
    private var chSelect: Int = 0
    private var cookieCount: Int = 5
    private var giftSelect:Int=0
    private var musicPlay:Int=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_infant_cookie_save)

        val cookieSaveCount: TextView =findViewById(R.id.cookie_save_count_txt)

        bgSelect = intent.getIntExtra("bgSelect",1)
        chSelect = intent.getIntExtra("chSelect",0)
        cookieCount = intent.getIntExtra("cookieCount",5)
        giftSelect = intent.getIntExtra("giftSelect",0)
        musicPlay = intent.getIntExtra("musicPlay",0)
        cookieSaveCount.text = cookieCount.toString()
        setCookieCountImage()

        window.statusBarColor = Color.parseColor("#FCC364")

        //홈 화면으로 돌아가기
        val intent = Intent(this, InfantHomeActivity::class.java)
        infant_icon_out_cookie.setOnClickListener{
            intent.putExtra("bgSelect",bgSelect)
            intent.putExtra("chSelect",chSelect)
            intent.putExtra("cookieCount",cookieCount)
            intent.putExtra("giftSelect",giftSelect)
            intent.putExtra("musicPlay",musicPlay)
            startActivity(intent)
            finish()
            overridePendingTransition(0, 0)
        }
    }

    override fun onBackPressed(){
        Log.d("backpress","막음")
    }

    private fun setCookieCountImage(){
        val cookieId =
            arrayOfNulls<ImageView>(cookieCount+1)

        for(i in 1 until cookieCount+1){
            var id: Int= resources.getIdentifier("ic_cookie_false$i", "id", packageName)
            if(i>30){
                Toast.makeText(this, "쿠키가 꽉찼어요!", Toast.LENGTH_SHORT).show()
                break
            }
            cookieId[i] = findViewById(id)
            cookieId[i]?.setImageResource(R.drawable.ic_cookies_true)
        }
    }
}
