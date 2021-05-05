package com.all_the_best.knock_knock.parent.setting.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import com.all_the_best.knock_knock.R
import com.all_the_best.knock_knock.databinding.ActivityParentAlarmSettingBinding
import com.all_the_best.knock_knock.util.StatusBarUtil

class ParentAlarmSettingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityParentAlarmSettingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(0, 0)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_parent_alarm_setting)
        StatusBarUtil.setStatusBar(this, resources.getColor(R.color.white_status_bar, null))
        setOnBackBtnClick()
    }

    private fun setOnBackBtnClick() {
        binding.parentAlarmSettingBtnBack.setOnClickListener {
            onBackPressed()
        }
    }
}