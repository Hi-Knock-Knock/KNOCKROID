package com.all_the_best.knock_knock.parent.record.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.all_the_best.knock_knock.R
import com.all_the_best.knock_knock.databinding.ActivityParentRecordDetailBinding
import com.all_the_best.knock_knock.util.StatusBarUtil

class ParentRecordDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        StatusBarUtil.setStatusBar(this, resources.getColor(R.color.white_status_bar, null))

        val binding: ActivityParentRecordDetailBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_parent_record_detail)
        binding.txtDelete = "삭제하기"
    }
}