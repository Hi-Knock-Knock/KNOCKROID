package com.all_the_best.knock_knock.parent_layout

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import com.all_the_best.knock_knock.R
import kotlinx.android.synthetic.main.parent_refusal_dialog1.*
import kotlinx.android.synthetic.main.parent_refusal_dialog2.*
import kotlinx.android.synthetic.main.parent_refusal_dialog2.select_q_txt

class ParentRealTalkRefDialog2Activity : AppCompatActivity() {
//    //거절 dlg 시작
//    lateinit var refusal_dlg1: Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 수정하기 dialog
        setContentView(R.layout.parent_refusal_dialog2)

        //라디오 버튼 클릭시 선택한 질문 텍스트 벼경
        select_q_radiogroup.setOnCheckedChangeListener { p0, checkedId ->
            when(checkedId){
                R.id.qrb1 -> select_q_txt.text = "없음"
                R.id.qrb2 -> select_q_txt.text = "엄마랑 아빠랑 싸우면 기분이 어때?"
                R.id.qrb3 -> select_q_txt.text = "동생은 어떤 존재야?"
                R.id.qrb4 -> select_q_txt.text = "동생이랑 노는 거 즐거워?"
                R.id.qrb5 -> select_q_txt.text = "엄마가 어떻게 해줬으면 좋겠어?"
                R.id.qrb6 -> select_q_txt.text = "아빠한테 속상한 거 있어?"
                R.id.qrb7 -> select_q_txt.text = "엄마한테 속상한 거 있어?"
                R.id.qrb8 -> select_q_txt.text = "엄마랑 아빠랑 싸우면 기분이 어때?"
                R.id.qrb9 -> select_q_txt.text = "동생은 어떤 존재야?"
                R.id.qrb10 -> select_q_txt.text = "동생이랑 노는 거 즐거워?"
                R.id.qrb11 -> select_q_txt.text = "엄마가 어떻게 해줬으면 좋겠어?"
                R.id.qrb12 -> select_q_txt.text = "아빠한테 속상한 거 있어?"
            }
        }
        val intentdlg1 = Intent(this, ParentRealTalkRefDialog1Activity::class.java)

        //확인버튼: dialog1 띄우기(질문 수정 된거)
        btn_select_true2.setOnClickListener {
//            select_q_txt_dlg1.setText(select_q_txt.text)
            startActivity(intentdlg1)
        }

        //취소 -> dlg1으로 돌아가기(질문 수정x)
        btn_select_false2.setOnClickListener {
            startActivity(intentdlg1)
        }

//        refusal_dlg1.requestWindowFeature(Window.FEATURE_NO_TITLE)
//        refusal_dlg1.setContentView(R.layout.parent_refusal_dialog1)
//
//
//        //확인버튼: dialog1 띄우기(질문 수정 된거)
//        btn_select_true2.setOnClickListener {
//            refusal_dlg1.show()
//            refusal_dlg1.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
//            refusal_dlg1.select_q_txt_dlg1.setText(select_q_txt.text)
//        }
//
//        //취소버튼: dialog1 띄우기(질문 수정 안된거)
//        btn_select_false2.setOnClickListener {
//            refusal_dlg1.show()
//            refusal_dlg1.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
//        }

    }
}