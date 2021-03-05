package com.all_the_best.knock_knock.infant.setting.view

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.all_the_best.knock_knock.R
import com.all_the_best.knock_knock.parent.talk.view.ParentRealTalkActivity
import kotlinx.android.synthetic.main.activity_infant_select_id.*


class InfantSelectIdActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_infant_select_id)
        
        //상태바 색상 지정
        window.statusBarColor = Color.parseColor("#74DAFF")

        val intent = Intent(this, InfantSelectCharacterActivity::class.java)
        button1.setOnClickListener{
            startActivity(intent)
            overridePendingTransition(0, 0)
        }

        val intent2 = Intent(this, ParentRealTalkActivity::class.java)
        button2.setOnClickListener {
            startActivity(intent2)
            overridePendingTransition(0, 0)
        }
    }
}
