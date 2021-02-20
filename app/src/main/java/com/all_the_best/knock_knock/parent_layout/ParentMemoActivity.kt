package com.all_the_best.knock_knock.parent_layout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.all_the_best.knock_knock.R
import com.all_the_best.knock_knock.databinding.ActivityParentMemoBinding

class ParentMemoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityParentMemoBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_parent_memo)
        binding.txtDate = "2020.10.08"
    }
}