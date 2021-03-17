package com.all_the_best.knock_knock.infant.gift.view

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.all_the_best.knock_knock.R
import com.all_the_best.knock_knock.infant.home.view.InfantHomeActivity
import kotlinx.android.synthetic.main.activity_infant_gift_end.*


class InfantGiftEndActivity : AppCompatActivity() {
    private var bgSelect: Int = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_infant_gift_end)
        bgSelect = intent.getIntExtra("bgSelect",1)
        window.statusBarColor = Color.parseColor("#8A2A6C")

        val intent = Intent(this, InfantHomeActivity::class.java)
        infant_icon_gift_out3.setOnClickListener{
            intent.putExtra("bgSelect",bgSelect)
            startActivity(intent)
            overridePendingTransition(0, 0)
        }
    }
}