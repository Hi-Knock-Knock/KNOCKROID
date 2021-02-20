package com.all_the_best.knock_knock.parent.mypage.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.all_the_best.knock_knock.R
import com.all_the_best.knock_knock.databinding.ActivityParentMyScrapBinding

class ParentMyScrapActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityParentMyScrapBinding = DataBindingUtil.setContentView(this, R.layout.activity_parent_my_scrap)
        binding.txtGoFaq = "FAQ 바로가기"
    }
}