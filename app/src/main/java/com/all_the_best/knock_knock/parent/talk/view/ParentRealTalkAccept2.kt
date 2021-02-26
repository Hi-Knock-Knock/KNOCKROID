package com.all_the_best.knock_knock.parent.talk.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.all_the_best.knock_knock.R
import com.all_the_best.knock_knock.util.StatusBarUtil
import kotlinx.android.synthetic.main.parent_real_talk_accept_2.*

class ParentRealTalkAccept2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.parent_real_talk_accept_2)

        StatusBarUtil.setStatusBar(this, R.color.blue_status_bar)

        btn_voice.setOnClickListener {
            btn_voice.setImageResource(R.drawable.ic_parent_voice_mute)
        }
    }
}