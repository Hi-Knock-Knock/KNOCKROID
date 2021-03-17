package com.all_the_best.knock_knock.parent.talk.view

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.all_the_best.knock_knock.R
import com.all_the_best.knock_knock.databinding.FragmentParentFaqBinding
import com.all_the_best.knock_knock.databinding.FragmentParentTalkBinding
import com.all_the_best.knock_knock.parent.alarm.view.ParentNoticeActivity
import com.all_the_best.knock_knock.parent.mypage.view.ParentMyPageActivity
import com.all_the_best.knock_knock.parent.setting.view.ParentSettingActivity
import com.all_the_best.knock_knock.util.FragmentOnBackPressed
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.fragment_parent_faq.*
import kotlinx.android.synthetic.main.fragment_parent_talk.*

class ParentTalkFragment : Fragment(), FragmentOnBackPressed,
    NavigationView.OnNavigationItemSelectedListener {
    private lateinit var binding: FragmentParentTalkBinding

    override fun onBackPressed(): Boolean {
        return if (binding.talkDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            Log.d("프래그먼트", "대화하기 if")
            binding.talkDrawerLayout.closeDrawers()
            true
        } else {
            false
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_parent_talk, container, false)
        binding.talkNavigationView.setNavigationItemSelectedListener(this)
        setOnClickListenerForBtnHamburger()
        setOnClickListenerForBtnSubmit()
        setOnClickListenerForBtnHelp()
        return binding.root
    }

    private fun setOnClickListenerForBtnHelp() {
        //도움말 버튼(물음표 버튼) 누르면 도움말 창 띄우기
        binding.talkBtnHelp.setOnClickListener {
            val builder = AlertDialog.Builder(requireContext())
            val dialogView = layoutInflater.inflate(R.layout.help_dialog, null)
            //val dialogText = dialogView.findViewById<EditText>(R.id.dialogEt)
            //val dialogRatingBar = dialogView.findViewById<RatingBar>(R.id.dialogRb)

            builder.setView(dialogView)
                .setPositiveButton("확인") { dialogInterface, i ->

                    /* 확인일 때 main의 View의 값에 dialog View에 있는 값을 적용 */

                }
                .show()
        }
    }

    private fun setOnClickListenerForBtnSubmit() {
        //저장하기 버튼 클릭시 선택한 질문 텍스트 벼경
        binding.talkBtnSubmit.setOnClickListener {
            binding.talkTxtSelectedQuestion.text = when (binding.talkRadiogroup.checkedRadioButtonId) {
                R.id.rb1 -> "없음"
                R.id.rb2 -> "엄마랑 아빠랑 싸우면 기분이 어때?"
                R.id.rb3 -> "동생은 어떤 존재야?"
                R.id.rb4 -> "동생이랑 노는 거 즐거워?"
                R.id.rb5 -> "엄마가 어떻게 해줬으면 좋겠어?"
                R.id.rb6 -> "아빠한테 속상한 거 있어?"
                R.id.rb7 -> "엄마한테 속상한 거 있어?"
                R.id.rb8 -> "엄마랑 아빠랑 싸우면 기분이 어때?"
                R.id.rb9 -> "동생은 어떤 존재야?"
                R.id.rb10 -> "동생이랑 노는 거 즐거워?"
                R.id.rb11 -> "엄마가 어떻게 해줬으면 좋겠어?"
                R.id.rb12 -> "동생이랑 노는 거 즐거워?"
                R.id.rb13 -> "엄마가 어떻게 해줬으면 좋겠어?"
                R.id.rb14 -> "동생이랑 노는 거 즐거워?"
                R.id.rb15 -> "엄마가 어떻게 해줬으면 좋겠어?"
                else -> "없음"
            }
        }
    }
    private fun setOnClickListenerForBtnHamburger() {
        //햄버거바 클릭시 DrawerLayout 열림
        binding.talkBtnHamburger.setOnClickListener {
            talk_drawer_layout.openDrawer(GravityCompat.START) //네비게이션 드로어 열기
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