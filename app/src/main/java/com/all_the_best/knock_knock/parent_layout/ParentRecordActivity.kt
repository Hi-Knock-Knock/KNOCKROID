package com.all_the_best.knock_knock.parent_layout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.all_the_best.knock_knock.R
import com.all_the_best.knock_knock.databinding.ActivityParentRecordBinding

class ParentRecordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityParentRecordBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_parent_record)
        binding.txtDelete = "삭제하기"
    }

}