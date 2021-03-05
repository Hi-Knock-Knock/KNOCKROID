package com.all_the_best.knock_knock.parent.talk.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.all_the_best.knock_knock.R
import com.all_the_best.knock_knock.util.StatusBarUtil
import kotlinx.android.synthetic.main.parent_refusal_dialog3.*

class ParentRealTalkRefDialog3Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.parent_refusal_dialog3)

        StatusBarUtil.setStatusBar(this, resources.getColor(R.color.light_blue_status_bar, null))

        refusal_btn_send.setOnClickListener {
            setContentView(R.layout.parent_refusal_talk_char_start)
        }
    }
}