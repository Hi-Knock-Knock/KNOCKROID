package com.all_the_best.knock_knock.infant.change.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.all_the_best.knock_knock.R
import com.all_the_best.knock_knock.util.FragmentOnBackPressed

class InfantSwitchTimiFragment : Fragment(), FragmentOnBackPressed {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_infant_switch_timi, container, false)
    }
    override fun onBackPressed(): Boolean {
        return if(this is InfantSwitchTimiFragment){
            Log.d("fragment 백버튼", "프래그먼트 백버튼 실행")
            true
        }else{
            false
        }
    }
}