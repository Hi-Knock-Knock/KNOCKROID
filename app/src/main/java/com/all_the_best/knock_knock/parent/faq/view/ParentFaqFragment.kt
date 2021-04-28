package com.all_the_best.knock_knock.parent.faq.view


import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.observe
import com.all_the_best.knock_knock.R
import com.all_the_best.knock_knock.databinding.FragmentParentFaqBinding
import com.all_the_best.knock_knock.databinding.ItemParentFaqBinding
import com.all_the_best.knock_knock.parent.alarm.view.ParentNoticeActivity
import com.all_the_best.knock_knock.util.FragmentOnBackPressed
import com.all_the_best.knock_knock.parent.faq.adapter.ParentFaqRcvAdapter
import com.all_the_best.knock_knock.parent.faq.viewmodel.ParentFaqViewModel
import com.all_the_best.knock_knock.parent.mypage.view.ParentMyPageActivity
import com.all_the_best.knock_knock.parent.setting.view.ParentSettingActivity
import com.google.android.material.navigation.NavigationView


class ParentFaqFragment : Fragment(), FragmentOnBackPressed,
    NavigationView.OnNavigationItemSelectedListener {
    private lateinit var binding: FragmentParentFaqBinding
    private val parentFaqViewModel: ParentFaqViewModel by activityViewModels()

    override fun onBackPressed(): Boolean {
        return if (binding.faqDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            Log.d("프래그먼트", "FAQ if")
            binding.faqDrawerLayout.closeDrawers()
            true
        } else {
            false
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_parent_faq, container, false)
        binding.faqNavigationView.setNavigationItemSelectedListener(this)
        binding.lifecycleOwner = this
        parentFaqViewModel.setFaqList()
        setOnClickListenerForBtnHamburger()
        setFaqRcvAdapter()
        setFaqListObserve()
        return binding.root
    }

    private fun setOnClickListenerForBtnHamburger() {
        //햄버거바 클릭시 DrawerLayout 열림
        binding.faqBtnHamburger.setOnClickListener {
            binding.faqDrawerLayout.openDrawer(GravityCompat.START) //네비게이션 드로어 열기
        }
    }

    private fun setFaqRcvAdapter() {
        binding.parentFaqRcv.adapter =
            ParentFaqRcvAdapter<ItemParentFaqBinding>(R.layout.item_parent_faq, requireContext(), parentFaqViewModel)
    }

    private fun setFaqListObserve() {
        parentFaqViewModel.faqList.observe(viewLifecycleOwner) { faqList ->
            faqList.let {
                if (binding.parentFaqRcv.adapter != null) with(binding.parentFaqRcv.adapter as ParentFaqRcvAdapter<*>) {
                    submitList(faqList)
                }
            }
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        lateinit var intent: Intent
        when (item.itemId) {
            R.id.item_mypage -> intent = Intent(context, ParentMyPageActivity::class.java)
            R.id.item_alarm -> intent = Intent(context, ParentNoticeActivity::class.java)
            R.id.item_settings -> intent = Intent(context, ParentSettingActivity::class.java)
        }
        startActivity(intent)
        return false
    }
}