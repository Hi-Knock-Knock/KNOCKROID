package com.all_the_best.knock_knock.infant.talk.view

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.airbnb.lottie.LottieDrawable
import com.all_the_best.knock_knock.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_infant_home.*
import kotlinx.android.synthetic.main.activity_infant_select_person.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class InfantSelectPersonActivity : AppCompatActivity() {
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
        setContentView(R.layout.activity_infant_select_person)
        bgSelect = intent.getIntExtra("bgSelect", 1)
        chSelect = intent.getIntExtra("chSelect", 0)
        cookieCount = intent.getIntExtra("cookieCount", 5)
        giftSelect = intent.getIntExtra("giftSelect", 0)
        lottieSelect = intent.getIntExtra("lottieSelect", 0)

        //setSelectCharacter()
        setSelectFeelMotion()

        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ISO_LOCAL_TIME
        val formatted = current.format(formatter)


        when (formatted) {
            in "08:00:000".."13:59:999" -> {
                window.statusBarColor = Color.parseColor("#57DDFF")
                when (bgSelect) {
                    1 -> infant_select_person.setBackgroundResource(R.drawable.img_infant_home_morning_bg)
                    2 -> infant_select_person.setBackgroundResource(R.drawable.img_infant_home_bg_flower1)
                    3 -> infant_select_person.setBackgroundResource(R.drawable.img_infant_home_bg_sea1)
                    4 -> {
                        infant_select_person.setBackgroundResource(R.drawable.img_infant_home_bg_space1)
                        window.statusBarColor = Color.parseColor("#0F0E15")
                    } // 우주
                }

            }
            in "14:00:000".."19:59:999" -> {
                window.statusBarColor = Color.parseColor("#FF6471")
                when (bgSelect) {
                    1 -> infant_select_person.setBackgroundResource(R.drawable.img_infant_home_after_bg)
                    2 -> infant_select_person.setBackgroundResource(R.drawable.img_infant_home_bg_flower2)
                    3 -> infant_select_person.setBackgroundResource(R.drawable.img_infant_home_bg_sea2)
                    4 -> {
                        infant_select_person.setBackgroundResource(R.drawable.img_infant_home_bg_space2)
                        window.statusBarColor = Color.parseColor("#0F0E15")
                    } // 우주
                }

            }
            in "20:00:00".."23:59:999" -> {
                when (bgSelect) {
                    1 -> infant_select_person.setBackgroundResource(R.drawable.img_infant_home_night_bg)
                    2 -> infant_select_person.setBackgroundResource(R.drawable.img_infant_home_bg_flower3)
                    3 -> infant_select_person.setBackgroundResource(R.drawable.img_infant_home_bg_sea3)
                    4 -> infant_select_person.setBackgroundResource(R.drawable.img_infant_home_bg_space3) // 우주
                }
                window.statusBarColor = Color.parseColor("#0F0E15")
            }
            !in "08:00:00".."23:59:999" -> {
                when (bgSelect) {
                    1 -> infant_select_person.setBackgroundResource(R.drawable.img_infant_home_night_bg)
                    2 -> infant_select_person.setBackgroundResource(R.drawable.img_infant_home_bg_flower3)
                    3 -> infant_select_person.setBackgroundResource(R.drawable.img_infant_home_bg_sea3)
                    4 -> infant_select_person.setBackgroundResource(R.drawable.img_infant_home_bg_space3) // 우주
                }
                window.statusBarColor = Color.parseColor("#0F0E15")
            }
        }

        val intent = Intent(this, InfantTalkStartActivity::class.java)
        // 대상 선택 버튼
        infant_emj_person_1.setOnClickListener {
            setPersonVariableAtFirebase(1)
            intent.putExtra("bgSelect", bgSelect)
            intent.putExtra("chSelect", chSelect)
            intent.putExtra("cookieCount", cookieCount)
            intent.putExtra("giftSelect", giftSelect)
            intent.putExtra("lottieSelect", lottieSelect)
            startActivity(intent)
            overridePendingTransition(0, 0)
        }
        infant_emj_person_2.setOnClickListener {
            setPersonVariableAtFirebase(2)
            intent.putExtra("bgSelect", bgSelect)
            intent.putExtra("chSelect", chSelect)
            intent.putExtra("cookieCount", cookieCount)
            intent.putExtra("giftSelect", giftSelect)
            intent.putExtra("lottieSelect", lottieSelect)
            startActivity(intent)
            overridePendingTransition(0, 0)
        }
        infant_emj_person_3.setOnClickListener {
            setPersonVariableAtFirebase(3)
            intent.putExtra("bgSelect", bgSelect)
            intent.putExtra("chSelect", chSelect)
            intent.putExtra("cookieCount", cookieCount)
            intent.putExtra("giftSelect", giftSelect)
            intent.putExtra("lottieSelect", lottieSelect)
            startActivity(intent)
            overridePendingTransition(0, 0)
        }
        infant_emj_person_4.setOnClickListener {
            setPersonVariableAtFirebase(4)
            intent.putExtra("bgSelect", bgSelect)
            intent.putExtra("chSelect", chSelect)
            intent.putExtra("cookieCount", cookieCount)
            intent.putExtra("giftSelect", giftSelect)
            intent.putExtra("lottieSelect", lottieSelect)
            startActivity(intent)
            overridePendingTransition(0, 0)
        }
        infant_emj_person_5.setOnClickListener {
            setPersonVariableAtFirebase(5)
            intent.putExtra("bgSelect", bgSelect)
            intent.putExtra("chSelect", chSelect)
            intent.putExtra("cookieCount", cookieCount)
            intent.putExtra("giftSelect", giftSelect)
            intent.putExtra("lottieSelect", lottieSelect)
            startActivity(intent)
            overridePendingTransition(0, 0)
        }
    }

    // 기분 모션 선택
    private fun setSelectFeelMotion() {
        when (chSelect) {
            0 -> {
                when (lottieSelect) {
                    1 -> char_talk_person.setAnimation("dami_happy.json")
                    2 -> char_talk_person.setAnimation("dami_happy.json")
                    3 -> char_talk_person.setAnimation("dami_sad.json")
                    4 -> char_talk_person.setAnimation("dami_sad.json")
                    5 -> char_talk_person.setAnimation("dami_scared.json")
                    6 -> char_talk_person.setAnimation("dami_angry.json")
                }
            }
            1 -> { // 녹녹이일때
                when (lottieSelect) {
                    1 -> char_talk_person.setAnimation("knock_happy.json")
                    2 -> char_talk_person.setAnimation("knock_happy.json")
                    3 -> char_talk_person.setAnimation("knock_sad.json")
                    4 -> char_talk_person.setAnimation("knock_sad.json")
                    5 -> char_talk_person.setAnimation("knock_scared.json")
                    6 -> char_talk_person.setAnimation("knock_angry.json")
                }
            }
            2 -> { // 티미일때
                when (lottieSelect) {
                    1 -> char_talk_person.setAnimation("timi_happy.json")
                    2 -> char_talk_person.setAnimation("timi_happy.json")
                    3 -> char_talk_person.setAnimation("timi_sad.json")
                    4 -> char_talk_person.setAnimation("timi_sad.json")
                    5 -> char_talk_person.setAnimation("timi_scared.json")
                    6 -> char_talk_person.setAnimation("timi_angry.json")
                }
            }
        }
        setOnMotionStart()
    }

    private fun setOnMotionStart() {
        char_talk_person.repeatMode = LottieDrawable.REVERSE
        char_talk_person.repeatCount = LottieDrawable.INFINITE
        char_talk_person.playAnimation()
    }

    private fun setPersonVariableAtFirebase(personNum: Int) {
        val parentId = "부모1"
        val childName = "아이1"
        var personString = ""
        personString = when (personNum) {
            1 -> "나혼자"
            2 -> "비밀이야"
            3 -> "선생님"
            4 -> "친구"
            5 -> "가족"
            else -> ""
        }
        databaseReference.child(parentId).child(parentId + "의 child " + childName)
            .child("childPerson")
            .setValue(personString)
    }
}