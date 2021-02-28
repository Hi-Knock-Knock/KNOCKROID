package com.all_the_best.knock_knock.parent.home.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.all_the_best.knock_knock.R
import com.all_the_best.knock_knock.databinding.FragmentParentHomeBinding
import com.all_the_best.knock_knock.parent.faq.view.ParentFaqDetailActivity
import com.all_the_best.knock_knock.util.FragmentOnBackPressed
import com.all_the_best.knock_knock.parent.home.adapter.ParentHomeRcvAdapter
import com.all_the_best.knock_knock.parent.home.viewmodel.ParentHomeViewModel
import kotlinx.android.synthetic.main.fragment_parent_home.*

class ParentHomeFragment : Fragment(), FragmentOnBackPressed {
    private lateinit var binding: FragmentParentHomeBinding
    private val parentHomeViewModel : ParentHomeViewModel by activityViewModels()

    override fun onBackPressed(): Boolean {
        if (home_drawer_layout.isDrawerOpen(GravityCompat.START)) {
            Log.d("프래그먼트", "홈화면 if")
            home_drawer_layout.closeDrawers()
            return true
        } else {
            return false
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_parent_home, container, false)
        onSelectNavigationMenu()
        parentHomeViewModel.setParentRecordList()
        setParentHomeRecordRcvAdapter()
        setParentHomeRecordObserve()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        //햄버거바 클릭시 DrawerLayout 열림
        home_btn_hamburger.setOnClickListener {
            home_drawer_layout.openDrawer(GravityCompat.START) //네비게이션 드로어 열기
        }

        super.onViewCreated(view, savedInstanceState)
    }

    private fun setParentHomeRecordRcvAdapter(){
        val parentHomeRecordAdapter = ParentHomeRcvAdapter()
        binding.parentHomeRcv.adapter = parentHomeRecordAdapter
    }

    private fun setParentHomeRecordObserve(){
        parentHomeViewModel.parentHomeRecordList.observe(viewLifecycleOwner, Observer{parentHomeRecordList->
            parentHomeRecordList?.let{
                if (binding.parentHomeRcv.adapter != null) with(binding.parentHomeRcv.adapter as ParentHomeRcvAdapter) {
                    submitList(parentHomeRecordList)
                }
            }
        })


    }

    private fun onSelectNavigationMenu() {
//        binding.homeNavigationView.setNavigationItemSelectedListener {
//            when (it.itemId) {
//                R.id.item_mypage -> Toast.makeText(this, "account clicked", Toast.LENGTH_SHORT).show()
//                R.id.item_childrecord -> Toast.makeText(this, "item2 clicked", Toast.LENGTH_SHORT).show()
//                R.id.item_alarm -> Toast.makeText(this, "item3 clicked", Toast.LENGTH_SHORT).show()
//                R.id.item_settings ->
//            }
//            return false
//        }
        var detailIntent= Intent(context, ParentFaqDetailActivity::class.java)
        //startActivity(detailIntent)

    }


}