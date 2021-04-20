package com.all_the_best.knock_knock.infant.talk.view

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.lottie.LottieDrawable
import com.all_the_best.knock_knock.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_infant_select_feel.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class InfantSelectFeelActivity : AppCompatActivity() {
    private var bgSelect: Int = 1
    private var chSelect: Int = 0
    private var cookieCount: Int = 5
    private var giftSelect: Int = 0
    private var lottieSelect: Int = 0

    private val database: FirebaseDatabase = FirebaseDatabase.getInstance()
    // 데이터베이스의 인스턴스를 가져온다고 생각(즉, Root를 가져온다고 이해하면 쉬움)
    private val databaseReference: DatabaseReference = database.reference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_infant_select_feel)
        bgSelect = intent.getIntExtra("bgSelect", 1)
        chSelect = intent.getIntExtra("chSelect", 0)
        cookieCount = intent.getIntExtra("cookieCount", 5)
        giftSelect = intent.getIntExtra("giftSelect", 0)
        lottieSelect = intent.getIntExtra("lottieSelect", 0)
        //setSelectFeelMotion()
        setSelectCharacter()


        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ISO_LOCAL_TIME
        val formatted = current.format(formatter)

        when (formatted) {
            in "08:00:000".."13:59:999" -> {
                window.statusBarColor = Color.parseColor("#57DDFF")
                when (bgSelect) {
                    1 -> infant_select_feel.setBackgroundResource(R.drawable.img_infant_home_morning_bg)
                    2 -> infant_select_feel.setBackgroundResource(R.drawable.img_infant_home_bg_flower1)
                    3 -> infant_select_feel.setBackgroundResource(R.drawable.img_infant_home_bg_sea1)
                    4 -> {
                        infant_select_feel.setBackgroundResource(R.drawable.img_infant_home_bg_space1)
                        window.statusBarColor = Color.parseColor("#0F0E15")
                    } // 우주

                }

            }
            in "14:00:000".."19:59:999" -> {
                window.statusBarColor = Color.parseColor("#FF6471")
                when (bgSelect) {
                    1 -> infant_select_feel.setBackgroundResource(R.drawable.img_infant_home_after_bg)
                    2 -> infant_select_feel.setBackgroundResource(R.drawable.img_infant_home_bg_flower2)
                    3 -> infant_select_feel.setBackgroundResource(R.drawable.img_infant_home_bg_sea2)
                    4 -> {
                        infant_select_feel.setBackgroundResource(R.drawable.img_infant_home_bg_space2)
                        window.statusBarColor = Color.parseColor("#0F0E15")
                    } // 우주

                }

            }
            in "20:00:00".."23:59:999" -> {
                when (bgSelect) {
                    1 -> infant_select_feel.setBackgroundResource(R.drawable.img_infant_home_night_bg)
                    2 -> infant_select_feel.setBackgroundResource(R.drawable.img_infant_home_bg_flower3)
                    3 -> infant_select_feel.setBackgroundResource(R.drawable.img_infant_home_bg_sea3)
                    4 -> infant_select_feel.setBackgroundResource(R.drawable.img_infant_home_bg_space3) // 우주

                }
                window.statusBarColor = Color.parseColor("#0F0E15")
            }
            !in "08:00:00".."23:59:999" -> {
                when (bgSelect) {
                    1 -> infant_select_feel.setBackgroundResource(R.drawable.img_infant_home_night_bg)
                    2 -> infant_select_feel.setBackgroundResource(R.drawable.img_infant_home_bg_flower3)
                    3 -> infant_select_feel.setBackgroundResource(R.drawable.img_infant_home_bg_sea3)
                    4 -> infant_select_feel.setBackgroundResource(R.drawable.img_infant_home_bg_space3) // 우주

                }
                window.statusBarColor = Color.parseColor("#0F0E15")
            }
        }

        val intent = Intent(this, InfantSelectPersonActivity::class.java)
        //감정선택버튼
        infant_emj_feel_1.setOnClickListener {
            when (lottieSelect) {
                0 -> lottieSelect = 1
            }
            setFeelingVariableAtFirebase(1)
            intent.putExtra("bgSelect", bgSelect)
            intent.putExtra("chSelect", chSelect)
            intent.putExtra("cookieCount", cookieCount)
            intent.putExtra("giftSelect", giftSelect)
            intent.putExtra("lottieSelect", lottieSelect)
            startActivity(intent)
            overridePendingTransition(0, 0)
        }
        infant_emj_feel_2.setOnClickListener {
            when (lottieSelect) {
                0 -> lottieSelect = 2
            }
            setFeelingVariableAtFirebase(2)
            intent.putExtra("bgSelect", bgSelect)
            intent.putExtra("chSelect", chSelect)
            intent.putExtra("cookieCount", cookieCount)
            intent.putExtra("giftSelect", giftSelect)
            intent.putExtra("lottieSelect", lottieSelect)
            startActivity(intent)
            overridePendingTransition(0, 0)
        }
        infant_emj_feel_3.setOnClickListener {
            when (lottieSelect) {
                0 -> lottieSelect = 3
            }
            setFeelingVariableAtFirebase(3)
            intent.putExtra("bgSelect", bgSelect)
            intent.putExtra("chSelect", chSelect)
            intent.putExtra("cookieCount", cookieCount)
            intent.putExtra("giftSelect", giftSelect)
            intent.putExtra("lottieSelect", lottieSelect)
            startActivity(intent)
            overridePendingTransition(0, 0)
        }
        infant_emj_feel_4.setOnClickListener {
            when (lottieSelect) {
                0 -> lottieSelect = 4
            }
            setFeelingVariableAtFirebase(4)
            intent.putExtra("bgSelect", bgSelect)
            intent.putExtra("chSelect", chSelect)
            intent.putExtra("cookieCount", cookieCount)
            intent.putExtra("giftSelect", giftSelect)
            intent.putExtra("lottieSelect", lottieSelect)
            startActivity(intent)
            overridePendingTransition(0, 0)
        }
        infant_emj_feel_5.setOnClickListener {
            when (lottieSelect) {
                0 -> lottieSelect = 5
            }
            setFeelingVariableAtFirebase(5)
            intent.putExtra("bgSelect", bgSelect)
            intent.putExtra("chSelect", chSelect)
            intent.putExtra("cookieCount", cookieCount)
            intent.putExtra("giftSelect", giftSelect)
            intent.putExtra("lottieSelect", lottieSelect)
            startActivity(intent)
            overridePendingTransition(0, 0)
        }
        infant_emj_feel_6.setOnClickListener {
            when (lottieSelect) {
                0 -> lottieSelect = 6
            }
            setFeelingVariableAtFirebase(6)
            intent.putExtra("bgSelect", bgSelect)
            intent.putExtra("chSelect", chSelect)
            intent.putExtra("cookieCount", cookieCount)
            intent.putExtra("giftSelect", giftSelect)
            intent.putExtra("lottieSelect", lottieSelect)
            startActivity(intent)
            overridePendingTransition(0, 0)
        }
    }

    private fun setSelectCharacter() {
        when (chSelect) {
            0 -> char_talk_feel.setAnimation("dami_hi.json")
            1 -> char_talk_feel.setAnimation("knock_earup.json") // 녹녹이
            2 -> char_talk_feel.setAnimation("timi_earout.json")
        }
        setOnMotionStart()
    }

    private fun setOnMotionStart() {
        char_talk_feel.repeatMode = LottieDrawable.REVERSE
        char_talk_feel.repeatCount = 1
        char_talk_feel.playAnimation()
    }

    private fun setFeelingVariableAtFirebase(feelNum: Int) {
        val parentId = "부모1"
        val childName = "아이1"
        var feelString = ""
        feelString = when (feelNum) {
            1 -> "기뻐"
            2 -> "신나"
            3 -> "속상해"
            4 -> "슬퍼"
            5 -> "무서워"
            6 -> "화나"
            else -> ""
        }
        databaseReference.child(parentId).child(parentId + "의 child " + childName)
            .child("childFeel")
            .setValue(feelString)
    }
}