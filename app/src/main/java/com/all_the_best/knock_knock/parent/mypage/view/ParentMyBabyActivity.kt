package com.all_the_best.knock_knock.parent.mypage.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.all_the_best.knock_knock.R
import com.all_the_best.knock_knock.databinding.ActivityParentMyBabyBinding
import com.all_the_best.knock_knock.util.StatusBarUtil

class ParentMyBabyActivity : AppCompatActivity() {
    private lateinit var binding: ActivityParentMyBabyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        StatusBarUtil.setStatusBar(this, resources.getColor(R.color.blue_status_bar, null))
        binding = DataBindingUtil.setContentView(this, R.layout.activity_parent_my_baby)
        setOnClickListenerForBtnGoBack()
    }

    private fun setOnClickListenerForBtnGoBack() {
        binding.parentMyBabyBtnBack.setOnClickListener {
            onBackPressed()
        }
    }
}