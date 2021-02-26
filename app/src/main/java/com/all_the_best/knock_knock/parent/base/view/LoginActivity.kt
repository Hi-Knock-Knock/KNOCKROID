package com.all_the_best.knock_knock.parent.base.view

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.all_the_best.knock_knock.R
import com.all_the_best.knock_knock.databinding.ActivityLoginBinding
import com.all_the_best.knock_knock.util.StatusBarUtil
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        StatusBarUtil.setStatusBar(this, R.color.light_blue_status_bar)

        val binding: ActivityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.txtSignUp = "회원가입"

        val intent1 = Intent(this, SelectModeActivity::class.java)
        val intent2 = Intent(this, SignupActivity::class.java)


        login_btn_login.setOnClickListener {
            if(login_edt_id.text.toString() == "" || login_edt_pw.text.toString() == ""){
                Toast.makeText(this, "아이디와 비밀번호를 모두 입력해주세요.", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this, "로그인이 완료되었습니다.", Toast.LENGTH_SHORT).show()
                startActivity(intent1)
            }

        }
        login_txt_signup.setOnClickListener {
            startActivityForResult(intent2, 1)
        }

    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when(requestCode){
                1 -> {
                    login_edt_id.setText(data!!.getStringExtra("id").toString())
                    login_edt_pw.setText(data!!.getStringExtra("pw").toString())
                }
            }
        }
    }
}