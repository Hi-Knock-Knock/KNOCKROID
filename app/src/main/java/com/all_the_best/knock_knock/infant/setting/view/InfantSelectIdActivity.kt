package com.all_the_best.knock_knock.infant.setting.view

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.all_the_best.knock_knock.R
import com.all_the_best.knock_knock.databinding.ActivityParentRealTalkBinding
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_infant_select_id.*


class InfantSelectIdActivity : AppCompatActivity() {

    private var chSelect: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_infant_select_id)

        chSelect = intent.getIntExtra("chSelect",0)
        //상태바 색상 지정
        window.statusBarColor = Color.parseColor("#74DAFF")

        val intent = Intent(this, InfantSelectCharacterActivity::class.java)
        button1.setOnClickListener{
            intent.putExtra("chSelect", chSelect)
            startActivity(intent)
            overridePendingTransition(0, 0)
        }
    }



}

