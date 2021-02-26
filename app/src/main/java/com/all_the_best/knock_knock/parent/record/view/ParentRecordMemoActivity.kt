package com.all_the_best.knock_knock.parent.record.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.all_the_best.knock_knock.R
import com.all_the_best.knock_knock.databinding.ActivityParentRecordMemoBinding
import com.all_the_best.knock_knock.util.StatusBarUtil

class ParentRecordMemoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        StatusBarUtil.setStatusBar(this, R.color.semi_black_status_bar)

        val binding: ActivityParentRecordMemoBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_parent_record_memo)
        binding.txtDate = "2020.10.08"


    }
}