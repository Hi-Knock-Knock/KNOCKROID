package com.all_the_best.knock_knock.parent.mypage.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.observe
import com.all_the_best.knock_knock.R
import com.all_the_best.knock_knock.databinding.ActivityParentMyScrapBinding
import com.all_the_best.knock_knock.databinding.ItemParentMyScrapBinding
import com.all_the_best.knock_knock.parent.faq.adapter.ParentFaqRcvAdapter
import com.all_the_best.knock_knock.parent.faq.viewmodel.ParentFaqViewModel
import com.all_the_best.knock_knock.parent.home.view.ParentHomeActivity
import com.all_the_best.knock_knock.util.StatusBarUtil

class ParentMyScrapActivity : AppCompatActivity() {
    private lateinit var binding: ActivityParentMyScrapBinding
    private val parentMyScrapViewmodel: ParentFaqViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        StatusBarUtil.setStatusBar(this, resources.getColor(R.color.white_status_bar, null))
        binding = DataBindingUtil.setContentView(this, R.layout.activity_parent_my_scrap)
        binding.txtGoFaq = "FAQ 바로가기"
        binding.lifecycleOwner = this
        parentMyScrapViewmodel.setMyScrapList()
        setOnClickListenerForGoBack()
        setOnClickListenerForGoFaq()
        setMyScrapRcvAdapter()
        setMyScrapListObserve()
    }

    private fun setOnClickListenerForGoBack() {
        binding.parentMyScrapBtnBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun setOnClickListenerForGoFaq() {
        binding.parentMyScrapTxtGoFaq.setOnClickListener {
            val intent = Intent(this, ParentHomeActivity::class.java)
            intent.putExtra("goFaq", true)
            startActivity(intent)
            finish()
        }
    }

    private fun setMyScrapRcvAdapter() {
        binding.parentMyScrapRcv.adapter =
            ParentFaqRcvAdapter<ItemParentMyScrapBinding>(R.layout.item_parent_my_scrap, this)
    }

    private fun setMyScrapListObserve() {
        parentMyScrapViewmodel.myScrapList.observe(this) { myScrapList ->
            myScrapList.let {
                if (binding.parentMyScrapRcv.adapter != null) with(binding.parentMyScrapRcv.adapter as ParentFaqRcvAdapter<*>) {
                    submitList(myScrapList)
                }
            }

        }
    }
}