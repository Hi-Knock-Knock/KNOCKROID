package com.all_the_best.knock_knock.parent.setting.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.all_the_best.knock_knock.R
import com.all_the_best.knock_knock.util.StatusBarUtil

class ParentAlarmSettingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parent_alarm_setting)

        StatusBarUtil.setStatusBar(this, R.color.white_status_bar)
    }
}