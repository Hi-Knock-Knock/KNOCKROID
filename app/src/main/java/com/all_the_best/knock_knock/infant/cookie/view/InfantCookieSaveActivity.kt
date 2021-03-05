package com.all_the_best.knock_knock.infant.cookie.view

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.all_the_best.knock_knock.R
import com.all_the_best.knock_knock.infant.home.view.InfantHomeActivity
import kotlinx.android.synthetic.main.activity_infant_cookie_save.*
import kotlinx.android.synthetic.main.activity_infant_deco.*

class InfantCookieSaveActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_infant_cookie_save)
        window.statusBarColor = Color.parseColor("#FCC364")

        val intent = Intent(this, InfantHomeActivity::class.java)
        infant_icon_out_cookie.setOnClickListener{
            startActivity(intent)
            overridePendingTransition(0, 0)
        }
    }
}