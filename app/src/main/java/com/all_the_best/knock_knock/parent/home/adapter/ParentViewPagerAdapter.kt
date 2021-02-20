package com.all_the_best.knock_knock.parent.home.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.all_the_best.knock_knock.parent.faq.view.ParentFaqFragment
import com.all_the_best.knock_knock.parent.home.view.ParentHomeFragment
import com.all_the_best.knock_knock.parent.talk.view.ParentTalkFragment


class ParentViewPagerAdapter(fm: FragmentManager):
    FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){

    override fun getItem(position: Int): Fragment = when(position){
        0 -> ParentHomeFragment()
        1 -> ParentTalkFragment()
        2 -> ParentFaqFragment()
        else -> throw IllegalStateException("Unexpected position $position")
    }

    override fun getCount(): Int = 3
}