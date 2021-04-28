package com.all_the_best.knock_knock.parent.faq.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.all_the_best.knock_knock.R
import com.all_the_best.knock_knock.databinding.ActivityParentFaqDetailBinding
import com.all_the_best.knock_knock.parent.faq.model.ParentFaqData
import com.all_the_best.knock_knock.parent.faq.viewmodel.ParentFaqViewModel
import com.all_the_best.knock_knock.util.StatusBarUtil

class ParentFaqDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityParentFaqDetailBinding
    private val faqDetailViewModel: ParentFaqViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_parent_faq_detail)
        StatusBarUtil.setStatusBar(this, resources.getColor(R.color.light_blue_status_bar, null))
        setFaqData()
        setOnBackBtnClick()
        //setOnBookmarkClick()
    }

    private fun setFaqData() {
        intent.getParcelableExtra<ParentFaqData>("faqData")?.let{
            faqDetailViewModel.faqDetailList[it.index].isScrapped = it.isScrapped
            binding.faqDetailData = faqDetailViewModel.faqDetailList[it.index]
        }
    }

    private fun setOnBackBtnClick() {
        binding.faqdetailBtnBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun setOnBookmarkClick() {
        binding.faqdetailBookmark.setOnClickListener {
            binding.faqDetailData.let {
                it!!.isScrapped = !it.isScrapped
                faqDetailViewModel.faqDetailList[it.index].isScrapped = it.isScrapped
                faqDetailViewModel.tempFaqList[it.index].isScrapped = it.isScrapped
                faqDetailViewModel.setFaqList()
                if (it.isScrapped) {
                    binding.faqdetailBookmark.setBackgroundResource(R.drawable.ic_bookmark_checked)
                } else {
                    binding.faqdetailBookmark.setBackgroundResource(R.drawable.ic_bookmark_unchecked)
                }
            }
        }
    }
}