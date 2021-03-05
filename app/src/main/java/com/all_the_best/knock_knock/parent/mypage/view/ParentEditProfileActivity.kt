package com.all_the_best.knock_knock.parent.mypage.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.all_the_best.knock_knock.R
import com.all_the_best.knock_knock.databinding.ActivityParentEditProfileBinding
import com.all_the_best.knock_knock.util.StatusBarUtil

class ParentEditProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        StatusBarUtil.setStatusBar(this, resources.getColor(R.color.white_status_bar, null))

        val binding: ActivityParentEditProfileBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_parent_edit_profile)
        binding.txtOk = "확인"
    }
}