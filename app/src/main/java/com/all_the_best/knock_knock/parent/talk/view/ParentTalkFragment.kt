package com.all_the_best.knock_knock.parent.talk.view

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.all_the_best.knock_knock.R
import com.all_the_best.knock_knock.util.FragmentOnBackPressed
import kotlinx.android.synthetic.main.fragment_parent_talk.*

class ParentTalkFragment : Fragment(), FragmentOnBackPressed {
    override fun onBackPressed(): Boolean {
        if (talk_drawer_layout.isDrawerOpen(GravityCompat.START)) {
            Log.d("프래그먼트","대화하기 if")
            talk_drawer_layout.closeDrawers()
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

        return inflater.inflate(R.layout.fragment_parent_talk, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        //저장하기 버튼 클릭시 선택한 질문 텍스트 벼경
        talk_btn_submit.setOnClickListener{
            when(talk_radiogroup.checkedRadioButtonId){
                R.id.rb1 -> talk_txt_selected_question.text = "없음"
                R.id.rb2 -> talk_txt_selected_question.text = "엄마랑 아빠랑 싸우면 기분이 어때?"
                R.id.rb3 -> talk_txt_selected_question.text = "동생은 어떤 존재야?"
                R.id.rb4 -> talk_txt_selected_question.text = "동생이랑 노는 거 즐거워?"
                R.id.rb5 -> talk_txt_selected_question.text = "엄마가 어떻게 해줬으면 좋겠어?"
                R.id.rb6 -> talk_txt_selected_question.text = "아빠한테 속상한 거 있어?"
                R.id.rb7 -> talk_txt_selected_question.text = "엄마한테 속상한 거 있어?"
                R.id.rb8 -> talk_txt_selected_question.text = "엄마랑 아빠랑 싸우면 기분이 어때?"
                R.id.rb9 -> talk_txt_selected_question.text = "동생은 어떤 존재야?"
                R.id.rb10 -> talk_txt_selected_question.text = "동생이랑 노는 거 즐거워?"
                R.id.rb11 -> talk_txt_selected_question.text = "엄마가 어떻게 해줬으면 좋겠어?"
                R.id.rb12 -> talk_txt_selected_question.text = "아빠한테 속상한 거 있어?"
            }
        }
        //라디오 버튼 클릭시 선택한 질문 텍스트 벼경
//        talk_radiogroup.setOnCheckedChangeListener(object :
//                RadioGroup.OnCheckedChangeListener{
//            override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
//                when(checkedId){
//                    R.id.rb1 -> talk_txt_selected_question.text = "없음"
//                    R.id.rb2 -> talk_txt_selected_question.text = "엄마랑 아빠랑 싸우면 기분이 어때?"
//                    R.id.rb3 -> talk_txt_selected_question.text = "동생은 어떤 존재야?"
//                    R.id.rb4 -> talk_txt_selected_question.text = "동생이랑 노는 거 즐거워?"
//                    R.id.rb5 -> talk_txt_selected_question.text = "엄마가 어떻게 해줬으면 좋겠어?"
//                    R.id.rb6 -> talk_txt_selected_question.text = "아빠한테 속상한 거 있어?"
//                    R.id.rb7 -> talk_txt_selected_question.text = "엄마한테 속상한 거 있어?"
//                    R.id.rb8 -> talk_txt_selected_question.text = "엄마랑 아빠랑 싸우면 기분이 어때?"
//                    R.id.rb9 -> talk_txt_selected_question.text = "동생은 어떤 존재야?"
//                    R.id.rb10 -> talk_txt_selected_question.text = "동생이랑 노는 거 즐거워?"
//                    R.id.rb11 -> talk_txt_selected_question.text = "엄마가 어떻게 해줬으면 좋겠어?"
//                    R.id.rb12 -> talk_txt_selected_question.text = "아빠한테 속상한 거 있어?"
//                }
//            }
//        })

        //도움말 버튼(물음표 버튼) 누르면 도움말 창 띄우기
        talk_btn_help.setOnClickListener {
            val builder = AlertDialog.Builder(view.context)
            val dialogView = layoutInflater.inflate(R.layout.help_dialog, null)
            //val dialogText = dialogView.findViewById<EditText>(R.id.dialogEt)
            //val dialogRatingBar = dialogView.findViewById<RatingBar>(R.id.dialogRb)

            builder.setView(dialogView)
                    .setPositiveButton("확인") { dialogInterface, i ->

                        /* 확인일 때 main의 View의 값에 dialog View에 있는 값을 적용 */

                    }
                    .show()
        }




        //햄버거바 클릭시 DrawerLayout 열림
        talk_btn_hamburger.setOnClickListener {
            talk_drawer_layout.openDrawer(GravityCompat.START) //네비게이션 드로어 열기
        }

        super.onViewCreated(view, savedInstanceState)
    }

}