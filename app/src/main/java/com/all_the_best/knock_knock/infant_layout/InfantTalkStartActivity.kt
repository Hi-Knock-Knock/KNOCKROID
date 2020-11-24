package com.all_the_best.knock_knock.infant_layout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.all_the_best.knock_knock.R
import kotlinx.android.synthetic.main.activity_infant_select_person.*
import kotlinx.android.synthetic.main.activity_infant_talk_start.*

class InfantTalkStartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_infant_talk_start)

        val intent = Intent(this, InfantHomeActivity::class.java)
        infant_icon_out.setOnClickListener{
            startActivity(intent)
        }
    }
}