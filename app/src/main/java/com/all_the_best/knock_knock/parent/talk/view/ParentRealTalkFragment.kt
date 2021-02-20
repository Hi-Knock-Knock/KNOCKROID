package com.all_the_best.knock_knock.parent.talk.view

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.all_the_best.knock_knock.R
import kotlinx.android.synthetic.main.fragment_parent_real_talk.*
import kotlinx.android.synthetic.main.fragment_parent_talk.*
import kotlinx.android.synthetic.main.fragment_parent_talk.talk_btn_hamburger

class ParentRealTalkFragment /*: Fragment(), FragmentOnBackPressed*/ {
/*
    override fun onBackPressed(): Boolean {
        if (real_talk_drawer_layout.isDrawerOpen(GravityCompat.START)) {
            Log.d("프래그먼트","대화하기 if")
            real_talk_drawer_layout.closeDrawers()
            return true
        } else {
            return false
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_parent_real_talk, container, false)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        //햄버거바 클릭시 DrawerLayout 열림
        real_talk_btn_hamburger.setOnClickListener {
            real_talk_drawer_layout.openDrawer(GravityCompat.START) //네비게이션 드로어 열기
        }

        super.onViewCreated(view, savedInstanceState)
    }*/

}