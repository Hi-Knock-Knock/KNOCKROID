package com.all_the_best.knock_knock.infant_layout.adapters

import android.content.Context
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.recyclerview.widget.RecyclerView
import com.all_the_best.knock_knock.infant_layout.InfantDecoFragment1
import com.all_the_best.knock_knock.infant_layout.InfantDecoFragment2
import com.all_the_best.knock_knock.infant_layout.InfantDecoFragment3
import com.all_the_best.knock_knock.infant_layout.model.InfantDecoItemChar

class InfantDecoActivityAdapter(fm: FragmentManager):
    FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment = when(position){
        0 -> InfantDecoFragment2() //-> 리사이클러뷰 구현 하기.. 하다 말음
        1 -> InfantDecoFragment2()
        2 -> InfantDecoFragment3()

        else -> throw IllegalStateException("Unexpected position $position")
    }

    override fun getCount(): Int = 3

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        super.destroyItem(container, position, `object`)
    }


}