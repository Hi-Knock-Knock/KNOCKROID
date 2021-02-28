package com.all_the_best.knock_knock.parent.setting.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.all_the_best.knock_knock.R
import com.all_the_best.knock_knock.util.StatusBarUtil

class ParentAccountActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parent_account)

        StatusBarUtil.setStatusBar(this, resources.getColor(R.color.white_status_bar, null))
    }
}