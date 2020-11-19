package com.all_the_best.knock_knock.base

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.all_the_best.knock_knock.R
import kotlinx.android.synthetic.main.activity_signup.*

class SignupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        val intent = Intent(this, SelectModeActivity::class.java)
        signup_btn_signup.setOnClickListener{
            startActivity(intent)
        }
    }
}