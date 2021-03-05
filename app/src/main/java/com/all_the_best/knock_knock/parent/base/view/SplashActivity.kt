package com.all_the_best.knock_knock.parent.base.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.all_the_best.knock_knock.R
import com.all_the_best.knock_knock.util.StatusBarUtil
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        
        StatusBarUtil.setStatusBar(this, resources.getColor(R.color.light_blue_status_bar, null))

        setSplashHandler()
    }

    private fun setSplashHandler(){
        Handler(Looper.getMainLooper()).postDelayed ({
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }, 2000)
    }
}