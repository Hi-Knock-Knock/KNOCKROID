package com.all_the_best.knock_knock.infant_layout

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.all_the_best.knock_knock.R
import kotlinx.android.synthetic.main.activity_infant_select_feel.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class InfantSelectFeelActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_infant_select_feel)
        val change_bg:ConstraintLayout = findViewById(R.id.infant_select_feel)

        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ISO_LOCAL_TIME
        val formatted = current.format(formatter)


        when(formatted){
            in "08:00:000".."13:59:999" -> change_bg.setBackgroundResource(R.drawable.infant_home_bg1)
            in "14:00:000".."19:59:999" -> change_bg.setBackgroundResource(R.drawable.infant_home_bg2)
            in "20:00:00".."23:59:999" -> change_bg.setBackgroundResource(R.drawable.infant_home_bg3)
            !in "08:00:00".."23:59:999" -> change_bg.setBackgroundResource(R.drawable.infant_home_bg3)
        }

        val intent = Intent(this, InfantSelectPersonActivity::class.java)
        infant_emj_feel_3.setOnClickListener{
            startActivity(intent)
        }

    }
}