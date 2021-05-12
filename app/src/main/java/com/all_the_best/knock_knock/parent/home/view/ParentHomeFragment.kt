package com.all_the_best.knock_knock.parent.home.view

import androidx.fragment.app.activityViewModels

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.PagerSnapHelper
import com.all_the_best.knock_knock.R
import androidx.lifecycle.observe
import com.all_the_best.knock_knock.databinding.FragmentParentHomeBinding
import com.all_the_best.knock_knock.parent.alarm.view.ParentNoticeActivity
import com.all_the_best.knock_knock.util.FragmentOnBackPressed
import com.all_the_best.knock_knock.parent.home.adapter.ParentHomeRcvAdapter
import com.all_the_best.knock_knock.parent.home.viewmodel.ParentHomeViewModel
import com.all_the_best.knock_knock.parent.mypage.view.ParentMyPageActivity
import com.all_the_best.knock_knock.parent.setting.view.ParentSettingActivity
import com.google.android.material.navigation.NavigationView

class ParentHomeFragment : Fragment(), FragmentOnBackPressed,
    NavigationView.OnNavigationItemSelectedListener {
    private lateinit var binding: FragmentParentHomeBinding
    private val parentHomeViewModel: ParentHomeViewModel by activityViewModels()

    override fun onBackPressed(): Boolean {
        return if (binding.homeDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            Log.d("프래그먼트", "홈화면 if")
            binding.homeDrawerLayout.closeDrawers()
            true
        } else {
            false
        }
    }

    override fun onResume() {
        super.onResume()
        Log.d("tag_img", "onresume")
        parentHomeViewModel.getProfileImgFromStorage()
        parentHomeViewModel.getAnswerFromFirebase()
        binding.parentHomeRcv.adapter?.notifyDataSetChanged()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_parent_home, container, false)
        binding.lifecycleOwner = this
        binding.homeNavigationView.setNavigationItemSelectedListener(this)
        parentHomeViewModel.getDefaultUri()
        parentHomeViewModel.getProfileImgFromStorage()
        parentHomeViewModel.getAnswerFromFirebase()
        setParentHomeRecordRcvAdapter()
        setParentHomeRecordObserve()
        setSnapHelper()
        setOnClickListenerForHamburger()
        return binding.root
    }

    private fun setOnClickListenerForHamburger() {
        //햄버거바 클릭시 DrawerLayout 열림
        binding.homeBtnHamburger.setOnClickListener {
            binding.homeDrawerLayout.openDrawer(GravityCompat.START) //네비게이션 드로어 열기
        }
    }

    private fun setParentHomeRecordRcvAdapter() {
        val parentHomeRecordAdapter = ParentHomeRcvAdapter(requireActivity())
        binding.parentHomeRcv.adapter = parentHomeRecordAdapter
    }

    private fun setParentHomeRecordObserve() {
        parentHomeViewModel.parentHomeRecordList.observe(viewLifecycleOwner) { parentHomeRecordList ->
            parentHomeRecordList.let {
                if (binding.parentHomeRcv.adapter != null) with(binding.parentHomeRcv.adapter as ParentHomeRcvAdapter) {
                    submitList(parentHomeRecordList)
                }
            }
        }
    }

    private fun setSnapHelper() {
        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(binding.parentHomeRcv)
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