package com.all_the_best.knock_knock.parent.setting.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.all_the_best.knock_knock.R
import com.all_the_best.knock_knock.databinding.ActivityParentSettingBinding
import com.all_the_best.knock_knock.util.StatusBarUtil

class ParentSettingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityParentSettingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_parent_setting)
        StatusBarUtil.setStatusBar(this, resources.getColor(R.color.blue_status_bar, null))
        setOnBackBtnClick()
        setOnAlarmSettingBtnClick()
        setOnAccountBtnClick()
    }

    private fun setOnBackBtnClick() {
        binding.parentSettingBtnBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun setOnAlarmSettingBtnClick() {
        val intent = Intent(this, ParentAlarmSettingActivity::class.java)
        binding.parentSettingBtnAlarm.setOnClickListener {
            startActivity(intent)
        }
        binding.parentSettingTxtAlarm.setOnClickListener {
            startActivity(intent)
        }

    }

    private fun setOnAccountBtnClick() {
        val intent = Intent(this, ParentAccountActivity::class.java)
        binding.parentSettingBtnAccount.setOnClickListener {
            startActivity(intent)
        }
        binding.parentSettingTxtAccount.setOnClickListener {
            startActivity(intent)
        }
    }
}