package com.all_the_best.knock_knock.parent.mypage.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.all_the_best.knock_knock.R
import com.all_the_best.knock_knock.databinding.ActivityParentEditProfileBinding
import com.all_the_best.knock_knock.util.StatusBarUtil

class ParentEditProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityParentEditProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(0, 0)
        StatusBarUtil.setStatusBar(this, resources.getColor(R.color.white_status_bar, null))
        binding = DataBindingUtil.setContentView(this, R.layout.activity_parent_edit_profile)
        binding.txtOk = "확인"
        setOnClickListenerForBtnGoBack()
        setOnClickListenerForBtnOk()
    }

    private fun setOnClickListenerForBtnGoBack() {
        binding.parentEditProfileBtnBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun setOnClickListenerForBtnOk() {
        binding.parentEditProfileTxtOk.setOnClickListener {
            Toast.makeText(this, "별명이 변경되었습니다.", Toast.LENGTH_SHORT).show()
            //프로필 별명 수정 코드 작성하기
        }
    }
}