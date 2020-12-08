package com.all_the_best.knock_knock.base

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.all_the_best.knock_knock.R
import kotlinx.android.synthetic.main.activity_signup.*

class SignupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        val intent = Intent(this, LoginActivity::class.java)
        signup_btn_signup.setOnClickListener{
            val id = signup_edt_id.text.toString()
            val pw = signup_edt_pw.text.toString()

            if (id != "" && pw != ""){
                Toast.makeText(this@SignupActivity, "반갑습니다, "+id+"님! 회원가입이 완료되었습니다.", Toast.LENGTH_SHORT).show()
                intent.putExtra("id", id)
                intent.putExtra("pw", pw)
                setResult(Activity.RESULT_OK,intent)
                finish()
            }
        }
    }
}