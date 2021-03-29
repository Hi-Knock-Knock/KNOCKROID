package com.all_the_best.knock_knock.infant.cookie.view

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.all_the_best.knock_knock.R


class InfantGetCookiePopupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_infant_get_cookie_popup)
        window.statusBarColor = Color.parseColor("#000000")
    }
}