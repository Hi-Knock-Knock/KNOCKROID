package com.all_the_best.knock_knock.base

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.all_the_best.knock_knock.R
import com.all_the_best.knock_knock.infant_layout.InfantSelectIdActivity
import com.all_the_best.knock_knock.parent_layout.ParentHomeActivity
import kotlinx.android.synthetic.main.activity_select_mode.*

class SelectModeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_mode)

        val intent1 = Intent(this, ParentHomeActivity::class.java)
        val intent2 = Intent(this, InfantSelectIdActivity::class.java)
        select_btn_infantmode.setOnClickListener{
            startActivity(intent2)
        }
        select_btn_parentmode.setOnClickListener {
            startActivity(intent1)
        }
    }
}