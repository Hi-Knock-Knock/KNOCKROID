package com.all_the_best.knock_knock.parent.record.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.all_the_best.knock_knock.R
import com.all_the_best.knock_knock.databinding.ActivityParentRecordMemoBinding

class ParentRecordMemoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityParentRecordMemoBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_parent_record_memo)
        binding.txtDate = "2020.10.08"
    }
}