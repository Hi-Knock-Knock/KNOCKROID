package com.all_the_best.knock_knock.infant_layout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.all_the_best.knock_knock.R
import com.all_the_best.knock_knock.parent_layout.ParentRealTalkActivity
import com.all_the_best.knock_knock.parent_layout.ParentRealTalkFragment
import kotlinx.android.synthetic.main.activity_infant_select_id.*

class InfantSelectIdActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_infant_select_id)

        val intent = Intent(this, InfantSelectCharacterActivity::class.java)
        button1.setOnClickListener{
            startActivity(intent)
        }

        val intent2 = Intent(this, ParentRealTalkActivity::class.java)
        button2.setOnClickListener {
            startActivity(intent2)
        }
    }
}