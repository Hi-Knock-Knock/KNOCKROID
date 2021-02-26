package com.all_the_best.knock_knock.parent.talk.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.all_the_best.knock_knock.R
import com.all_the_best.knock_knock.util.StatusBarUtil
import kotlinx.android.synthetic.main.activity_parent_real_talk.*

class ParentRealTalkActivity : AppCompatActivity() {
    //    var refusal_dlg1: Dialog? = null
    override fun onCreate(savedInstanceState: Bundle?) {

        val red = ContextCompat.getColor(applicationContext, R.color.orange_red)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parent_real_talk)

        StatusBarUtil.setStatusBar(this, R.color.light_blue_status_bar)

        alarm_layout.setOnClickListener {
            ic_alarm.setImageResource(R.drawable.ic_parent_alarm_finish)
            time.setTextColor(red)
        }

        //거절
        val intent = Intent(this, ParentRealTalkRefDialog1Activity::class.java)
        btn_real_talk_false.setOnClickListener {
            startActivity(intent)
        }

        //수락
        val intentaccept1 = Intent(this, ParentRealTalkAccept1::class.java)
        btn_real_talk_true.setOnClickListener {
            startActivity(intentaccept1)
        }


/*
        //버튼: 거절 -> 질문 선택 다이얼로그 띄우기
        refusal_dlg1?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        refusal_dlg1?.setContentView(R.layout.parent_refusal_dialog1)
        btn_real_talk_false.setOnClickListener {
            showSelectRefusalDialog()
        }
    }

    private fun showSelectRefusalDialog() {
        refusal_dlg1?.show()
        refusal_dlg1?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        //수정하기
        modify_select_q_home.setOnClickListener {
//            // 수정화면으로 이동
//            val intent1 = Intent(this, ParentRealTalkRefusalActivity::class.java)
//            startActivity(intent1)
        }

        //취소
        btn_select_false.setOnClickListener {
            refusal_dlg1?.dismiss()
        }

        //확인
        btn_select_true.setOnClickListener {

        }
*/

    }
}