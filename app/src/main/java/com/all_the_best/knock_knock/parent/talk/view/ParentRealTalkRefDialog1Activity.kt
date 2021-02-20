package com.all_the_best.knock_knock.parent.talk.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.all_the_best.knock_knock.R
import kotlinx.android.synthetic.main.parent_refusal_dialog1.*

class ParentRealTalkRefDialog1Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.parent_refusal_dialog1)
        //수정하기
        val intentselect_q = Intent(this, ParentRealTalkRefDialog2Activity::class.java)
        modify_select_q_home.setOnClickListener {
            startActivity(intentselect_q)
        }

        //취소
        val intentrealtalkhome = Intent(this, ParentRealTalkActivity::class.java)
        btn_select_false.setOnClickListener {
            startActivity(intentrealtalkhome)
        }

        //확인
        val intentsendrefusal = Intent(this, ParentRealTalkRefDialog3Activity::class.java)
        btn_select_true.setOnClickListener {
            startActivity(intentsendrefusal)
        }
    }
}
