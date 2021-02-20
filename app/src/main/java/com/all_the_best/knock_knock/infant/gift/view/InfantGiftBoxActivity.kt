package com.all_the_best.knock_knock.infant.gift.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.all_the_best.knock_knock.R
import com.all_the_best.knock_knock.infant.home.view.InfantHomeActivity
import kotlinx.android.synthetic.main.activity_infant_gift_box.*

class InfantGiftBoxActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_infant_gift_box)

        val intent = Intent(this, InfantHomeActivity::class.java)
        infant_icon_gift_out2.setOnClickListener{
            startActivity(intent)
        }

        val intent1 = Intent(this, InfantGiftEndActivity::class.java)
        infant_gift_box.setOnClickListener {
            startActivity(intent1)
        }
    }
}