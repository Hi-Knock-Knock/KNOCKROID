package com.all_the_best.knock_knock.parent.mypage.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.all_the_best.knock_knock.R
import com.all_the_best.knock_knock.databinding.ActivityParentMyPageBinding

class ParentMyPageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityParentMyPageBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_parent_my_page)
        binding.txtEdit = "프로필 수정"
        binding.txtShowMore = "더보"
    }
}