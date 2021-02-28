package com.all_the_best.knock_knock.parent.talk.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.all_the_best.knock_knock.R
import com.all_the_best.knock_knock.util.StatusBarUtil
import kotlinx.android.synthetic.main.parent_real_talk_accept_1.*

class ParentRealTalkAccept1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.parent_real_talk_accept_1)

        StatusBarUtil.setStatusBar(this, resources.getColor(R.color.blue_status_bar, null))

        //로딩후 화면
        val intentaccept2 = Intent(this, ParentRealTalkAccept2::class.java)
        bg_guide_box.setOnClickListener {
            startActivity(intentaccept2)
        }
    }
}