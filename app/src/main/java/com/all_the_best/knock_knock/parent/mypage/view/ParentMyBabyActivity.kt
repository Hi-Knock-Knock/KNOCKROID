package com.all_the_best.knock_knock.parent.mypage.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.observe
import com.all_the_best.knock_knock.R
import com.all_the_best.knock_knock.databinding.ActivityParentMyBabyBinding
import com.all_the_best.knock_knock.databinding.ItemParentMyBabyBinding
import com.all_the_best.knock_knock.databinding.ItemParentMyPageBabyBinding
import com.all_the_best.knock_knock.parent.mypage.adapter.ParentMyPageRcvAdapter
import com.all_the_best.knock_knock.parent.mypage.viewmodel.ParentMyPageViewModel
import com.all_the_best.knock_knock.util.StatusBarUtil

class ParentMyBabyActivity : AppCompatActivity() {
    private lateinit var binding: ActivityParentMyBabyBinding
    private val parentMyBabyViewModel: ParentMyPageViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        StatusBarUtil.setStatusBar(this, resources.getColor(R.color.blue_status_bar, null))
        binding = DataBindingUtil.setContentView(this, R.layout.activity_parent_my_baby)
        parentMyBabyViewModel.setParentMyPageBabyList()
        setOnClickListenerForBtnGoBack()
        setParentMyBabyRcvAdapter()
        setParentMyBabyObserve()
    }

    private fun setOnClickListenerForBtnGoBack() {
        binding.parentMyBabyBtnBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun setParentMyBabyRcvAdapter() {
        val parentMyBabyRcvAdapter = ParentMyPageRcvAdapter<ItemParentMyBabyBinding>(R.layout.item_parent_my_baby)
        binding.parentMyBabyRcv.adapter = parentMyBabyRcvAdapter
    }

    private fun setParentMyBabyObserve() {
        parentMyBabyViewModel.parentMyPageBabyList.observe(this) { parentMyBabyList ->
            parentMyBabyList.let {
                if (binding.parentMyBabyRcv.adapter != null) with(binding.parentMyBabyRcv.adapter as ParentMyPageRcvAdapter<*>) {
                    submitList(parentMyBabyList)
                }
            }
        }
    }
}