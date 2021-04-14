package com.all_the_best.knock_knock.parent.setting.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.all_the_best.knock_knock.R
import com.all_the_best.knock_knock.databinding.ActivityParentAccountBinding
import com.all_the_best.knock_knock.util.StatusBarUtil

class ParentAccountActivity : AppCompatActivity() {
    private lateinit var binding: ActivityParentAccountBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_parent_account)
        StatusBarUtil.setStatusBar(this, resources.getColor(R.color.white_status_bar, null))
        setOnBackBtnClick()
    }

    private fun setOnBackBtnClick() {
        binding.parentAccountBtnBack.setOnClickListener {
            onBackPressed()
        }
    }
}