package com.all_the_best.knock_knock.parent.talk.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.PagerSnapHelper
import com.all_the_best.knock_knock.R
import com.all_the_best.knock_knock.databinding.ActivityParentRealTalkBinding
import com.all_the_best.knock_knock.parent.talk.adapter.ParentTalkAcceptTipRcvAdapter
import com.all_the_best.knock_knock.parent.talk.viewmodel.ParentTalkViewModel
import com.all_the_best.knock_knock.util.StatusBarUtil
import java.text.SimpleDateFormat
import java.util.*

class ParentRealTalkActivity : AppCompatActivity() {
    private lateinit var binding: ActivityParentRealTalkBinding
    private val parentTalkViewModel: ParentTalkViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        StatusBarUtil.setStatusBar(
            this,
            resources.getColor(R.color.blue_status_bar, null)
        )
        binding = DataBindingUtil.setContentView(this, R.layout.activity_parent_real_talk)
        binding.txtDate
        setTipRcvAdapter()
        getToday()
    }

    private fun getToday() {
        val currentTime: Date = Calendar.getInstance().getTime()
        val simpleDate = SimpleDateFormat("yyyy.MM.dd")
        val date = simpleDate.format(currentTime)
        binding.txtDate = date.toString()
    }

    private fun setTipRcvAdapter() {
        val tipRcvAdapter = ParentTalkAcceptTipRcvAdapter()
        tipRcvAdapter.submitList(parentTalkViewModel.tipList)
        binding.acceptTalkRcv.adapter = tipRcvAdapter
        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(binding.acceptTalkRcv)
    }

}