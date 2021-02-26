package com.all_the_best.knock_knock.parent.base.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.all_the_best.knock_knock.R
import com.all_the_best.knock_knock.util.StatusBarUtil
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        StatusBarUtil.setStatusBar(this, R.color.light_blue_status_bar)

        val intent = Intent(this, LoginActivity::class.java)
        img_dami.setOnClickListener{
            startActivity(intent)
        }
    }
}