package com.all_the_best.knock_knock.infant_layout.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.all_the_best.knock_knock.infant_layout.InfantDamiFragment
import com.all_the_best.knock_knock.infant_layout.InfantKnockknockFragment
import com.all_the_best.knock_knock.infant_layout.InfantTimiFragment

class InfantViewPagerAdapter(fm: FragmentManager):
    FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){

    override fun getItem(position: Int): Fragment = when(position){
        0 -> InfantDamiFragment()
        1 -> InfantKnockknockFragment()
        2 -> InfantTimiFragment()

        else -> throw IllegalStateException("Unexpected position $position")
    }

    override fun getCount(): Int = 3
}