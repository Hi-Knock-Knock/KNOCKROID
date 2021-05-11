package com.all_the_best.knock_knock.infant.cookie.view

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import com.all_the_best.knock_knock.R
import com.all_the_best.knock_knock.infant.deco.viewmodel.InfantDecoViewModel
import com.all_the_best.knock_knock.infant.home.view.InfantHomeActivity
import kotlinx.android.synthetic.main.activity_infant_cookie_save.*
import kotlinx.android.synthetic.main.activity_infant_deco.*
import kotlinx.android.synthetic.main.activity_infant_home.*

class InfantCookieSaveActivity : AppCompatActivity() {

    private var bgSelect: Int = 1
    private var chSelect: Int = 0
    private var cookieCount: Int = 5
    private var giftSelect:Int=0
    private var musicPlay:Int=0

    //private var i:Int = 1
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
        setTrueCookieCountImage()

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
            overridePendingTransition(0, 0)
        }
    }

    private fun setTrueCookieCountImage(){
        when(cookieCount){
            1 -> ic_cookie_false1.setImageResource(R.drawable.ic_cookies_true)
            2 -> {
                ic_cookie_false1.setImageResource(R.drawable.ic_cookies_true)
                ic_cookie_false2.setImageResource(R.drawable.ic_cookies_true)
            }
            3-> {
                ic_cookie_false1.setImageResource(R.drawable.ic_cookies_true)
                ic_cookie_false2.setImageResource(R.drawable.ic_cookies_true)
                ic_cookie_false3.setImageResource(R.drawable.ic_cookies_true)

            }
            4-> {
                ic_cookie_false1.setImageResource(R.drawable.ic_cookies_true)
                ic_cookie_false2.setImageResource(R.drawable.ic_cookies_true)
                ic_cookie_false3.setImageResource(R.drawable.ic_cookies_true)
                ic_cookie_false4.setImageResource(R.drawable.ic_cookies_true)

            }
            5-> {
                ic_cookie_false1.setImageResource(R.drawable.ic_cookies_true)
                ic_cookie_false2.setImageResource(R.drawable.ic_cookies_true)
                ic_cookie_false3.setImageResource(R.drawable.ic_cookies_true)
                ic_cookie_false4.setImageResource(R.drawable.ic_cookies_true)
                ic_cookie_false5.setImageResource(R.drawable.ic_cookies_true)
            }
            6-> {
                ic_cookie_false1.setImageResource(R.drawable.ic_cookies_true)
                ic_cookie_false2.setImageResource(R.drawable.ic_cookies_true)
                ic_cookie_false3.setImageResource(R.drawable.ic_cookies_true)
                ic_cookie_false4.setImageResource(R.drawable.ic_cookies_true)
                ic_cookie_false5.setImageResource(R.drawable.ic_cookies_true)
                ic_cookie_false6.setImageResource(R.drawable.ic_cookies_true)
            }
            7-> {
                ic_cookie_false1.setImageResource(R.drawable.ic_cookies_true)
                ic_cookie_false2.setImageResource(R.drawable.ic_cookies_true)
                ic_cookie_false3.setImageResource(R.drawable.ic_cookies_true)
                ic_cookie_false4.setImageResource(R.drawable.ic_cookies_true)
                ic_cookie_false5.setImageResource(R.drawable.ic_cookies_true)
                ic_cookie_false6.setImageResource(R.drawable.ic_cookies_true)
                ic_cookie_false7.setImageResource(R.drawable.ic_cookies_true)

            }
            8-> {
                ic_cookie_false1.setImageResource(R.drawable.ic_cookies_true)
                ic_cookie_false2.setImageResource(R.drawable.ic_cookies_true)
                ic_cookie_false3.setImageResource(R.drawable.ic_cookies_true)
                ic_cookie_false4.setImageResource(R.drawable.ic_cookies_true)
                ic_cookie_false5.setImageResource(R.drawable.ic_cookies_true)
                ic_cookie_false6.setImageResource(R.drawable.ic_cookies_true)
                ic_cookie_false7.setImageResource(R.drawable.ic_cookies_true)
                ic_cookie_false8.setImageResource(R.drawable.ic_cookies_true)

            }
            9-> {
                ic_cookie_false1.setImageResource(R.drawable.ic_cookies_true)
                ic_cookie_false2.setImageResource(R.drawable.ic_cookies_true)
                ic_cookie_false3.setImageResource(R.drawable.ic_cookies_true)
                ic_cookie_false4.setImageResource(R.drawable.ic_cookies_true)
                ic_cookie_false5.setImageResource(R.drawable.ic_cookies_true)
                ic_cookie_false6.setImageResource(R.drawable.ic_cookies_true)
                ic_cookie_false7.setImageResource(R.drawable.ic_cookies_true)
                ic_cookie_false8.setImageResource(R.drawable.ic_cookies_true)
                ic_cookie_false9.setImageResource(R.drawable.ic_cookies_true)
            }
            10-> {
                ic_cookie_false1.setImageResource(R.drawable.ic_cookies_true)
                ic_cookie_false2.setImageResource(R.drawable.ic_cookies_true)
                ic_cookie_false3.setImageResource(R.drawable.ic_cookies_true)
                ic_cookie_false4.setImageResource(R.drawable.ic_cookies_true)
                ic_cookie_false5.setImageResource(R.drawable.ic_cookies_true)
                ic_cookie_false6.setImageResource(R.drawable.ic_cookies_true)
                ic_cookie_false7.setImageResource(R.drawable.ic_cookies_true)
                ic_cookie_false8.setImageResource(R.drawable.ic_cookies_true)
                ic_cookie_false9.setImageResource(R.drawable.ic_cookies_true)
                ic_cookie_false10.setImageResource(R.drawable.ic_cookies_true)
            }
            11-> {
                ic_cookie_false1.setImageResource(R.drawable.ic_cookies_true)
                ic_cookie_false2.setImageResource(R.drawable.ic_cookies_true)
                ic_cookie_false3.setImageResource(R.drawable.ic_cookies_true)
                ic_cookie_false4.setImageResource(R.drawable.ic_cookies_true)
                ic_cookie_false5.setImageResource(R.drawable.ic_cookies_true)
                ic_cookie_false6.setImageResource(R.drawable.ic_cookies_true)
                ic_cookie_false7.setImageResource(R.drawable.ic_cookies_true)
                ic_cookie_false8.setImageResource(R.drawable.ic_cookies_true)
                ic_cookie_false9.setImageResource(R.drawable.ic_cookies_true)
                ic_cookie_false10.setImageResource(R.drawable.ic_cookies_true)
                ic_cookie_false11.setImageResource(R.drawable.ic_cookies_true)
            }
            12-> {
                ic_cookie_false1.setImageResource(R.drawable.ic_cookies_true)
                ic_cookie_false2.setImageResource(R.drawable.ic_cookies_true)
                ic_cookie_false3.setImageResource(R.drawable.ic_cookies_true)
                ic_cookie_false4.setImageResource(R.drawable.ic_cookies_true)
                ic_cookie_false5.setImageResource(R.drawable.ic_cookies_true)
                ic_cookie_false6.setImageResource(R.drawable.ic_cookies_true)
                ic_cookie_false7.setImageResource(R.drawable.ic_cookies_true)
                ic_cookie_false8.setImageResource(R.drawable.ic_cookies_true)
                ic_cookie_false9.setImageResource(R.drawable.ic_cookies_true)
                ic_cookie_false10.setImageResource(R.drawable.ic_cookies_true)
                ic_cookie_false11.setImageResource(R.drawable.ic_cookies_true)
                ic_cookie_false12.setImageResource(R.drawable.ic_cookies_true)
            }
        }
    }

}