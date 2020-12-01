package com.all_the_best.knock_knock.infant_layout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.all_the_best.knock_knock.R
import kotlinx.android.synthetic.main.activity_infant_gift_end.*


class InfantGiftEndActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_infant_gift_end)

        val intent = Intent(this, InfantHomeActivity::class.java)
        infant_icon_gift_out3.setOnClickListener{
            startActivity(intent)
        }
    }
}