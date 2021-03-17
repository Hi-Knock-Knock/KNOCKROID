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
import androidx.recyclerview.widget.GridLayoutManager
import com.all_the_best.knock_knock.R
import com.all_the_best.knock_knock.databinding.FragmentParentFaqBinding
import com.all_the_best.knock_knock.parent.alarm.view.ParentNoticeActivity
import com.all_the_best.knock_knock.util.FragmentOnBackPressed
import com.all_the_best.knock_knock.parent.faq.adapter.ParentFaqAdapter
import com.all_the_best.knock_knock.parent.faq.adapter.ParentFaqData
import com.all_the_best.knock_knock.parent.faq.adapter.ParentFaqItemDeco
import com.all_the_best.knock_knock.parent.mypage.view.ParentMyPageActivity
import com.all_the_best.knock_knock.parent.setting.view.ParentSettingActivity
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.fragment_parent_faq.*


class ParentFaqFragment : Fragment(), FragmentOnBackPressed,
    NavigationView.OnNavigationItemSelectedListener {
    private lateinit var binding: FragmentParentFaqBinding
    private lateinit var parentFaqAdapter: ParentFaqAdapter
    private lateinit var rcvLayoutManager: GridLayoutManager

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
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_parent_faq, container, false)
        binding.faqNavigationView.setNavigationItemSelectedListener(this)

        setOnClickListenerForBtnHamburger()
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //setRcvFaqAdapter()
        parentFaqAdapter = ParentFaqAdapter(view.context)
        rcvLayoutManager = GridLayoutManager(view.context, 2)
        parent_faq_rcv.apply {
            adapter = parentFaqAdapter
            layoutManager = GridLayoutManager(view.context, 2)
        }

        var faqList = mutableListOf(
            ParentFaqData(0, "자존감이\n" + "낮은 아이", false),
            ParentFaqData(1, "아이를\n" + "바르게\n" + "대하는 방법", false),
            ParentFaqData(2, "자존감이\n" + "낮은 아이", false),
            ParentFaqData(3, "자존감이\n" + "낮은 아이", false),
            ParentFaqData(4, "아이가\n" + "싫어하는 행동을\n" + "표현한 경우", false),
            ParentFaqData(5, "아이가\n" + "싫어하는 행동을\n" + "표현한 경우", false),
            ParentFaqData(6, "참는 성향이\n" + "있는 아이", false),
            ParentFaqData(7, "참는 성향이\n" + "있는 아이", false),
            ParentFaqData(8, "아이를\n" + "바르게\n" + "대하는 방법", false)
        )

        parentFaqAdapter.data = faqList

        parentFaqAdapter.notifyDataSetChanged()

        super.onViewCreated(view, savedInstanceState)
    }

    private fun setOnClickListenerForBtnHamburger() {
        //햄버거바 클릭시 DrawerLayout 열림
        binding.faqBtnHamburger.setOnClickListener {
            binding.faqDrawerLayout.openDrawer(GravityCompat.START) //네비게이션 드로어 열기
        }
    }

    private fun setRcvFaqAdapter() {

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