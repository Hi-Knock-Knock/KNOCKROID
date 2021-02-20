package com.all_the_best.knock_knock.parent_layout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.all_the_best.knock_knock.R
import kotlinx.android.synthetic.main.parent_real_talk_accept_1.*
import kotlinx.android.synthetic.main.parent_refusal_dialog1.*

class ParentRealTalkAccept1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.parent_real_talk_accept_1)

        //로딩후 화면
        val intentaccept2 = Intent(this, ParentRealTalkAccept2::class.java)
        bg_guide_box.setOnClickListener {
            startActivity(intentaccept2)
        }
    }
}