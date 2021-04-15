package com.all_the_best.knock_knock.parent.alarm.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.all_the_best.knock_knock.R
import com.all_the_best.knock_knock.databinding.ActivityParentNoticeBinding
import com.all_the_best.knock_knock.util.StatusBarUtil

class ParentNoticeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityParentNoticeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_parent_notice)
        StatusBarUtil.setStatusBar(this, resources.getColor(R.color.white_status_bar, null))
        setOnBackBtnClick()
    }

    private fun setOnBackBtnClick() {
        binding.parentNoticeBtnBack.setOnClickListener {
            onBackPressed()
        }
    }
}