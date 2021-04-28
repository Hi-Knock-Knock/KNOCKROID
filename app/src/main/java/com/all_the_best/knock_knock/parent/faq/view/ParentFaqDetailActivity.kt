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
        setOnBookmarkClick()
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
                if (it!!.isScrapped) {
                    //버튼 클릭했을 시, 체크되어있는 북마크일 경우 -> 클릭하면 체크 해제 되도록
                    //상세화면에서 북마크 변경하면 리사이클러뷰에도 적용되도록 해야함...(추가 코딩 필요)
                    it.isScrapped = false
                    binding.faqdetailBookmark.setBackgroundResource(R.drawable.ic_bookmark_unchecked)
                } else {
                    //버튼 클릭했을 시, 체크되어있지 않은 북마크일 경우 -> 클릭하면 체크 되도록
                    //상세화면에서 북마크 변경하면 리사이클러뷰에도 적용되도록 해야함...(추가 코딩 필요)
                    it.isScrapped = true
                    binding.faqdetailBookmark.setBackgroundResource(R.drawable.ic_bookmark_checked)
                }
            }
        }
    }
}