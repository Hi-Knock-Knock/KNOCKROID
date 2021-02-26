package com.all_the_best.knock_knock.parent.base.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.all_the_best.knock_knock.R
import com.all_the_best.knock_knock.infant.setting.view.InfantSelectIdActivity
import com.all_the_best.knock_knock.parent.home.view.ParentHomeActivity
import com.all_the_best.knock_knock.util.StatusBarUtil
import kotlinx.android.synthetic.main.activity_select_mode.*

class SelectModeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_mode)

        StatusBarUtil.setStatusBar(this, R.color.light_blue_status_bar)

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