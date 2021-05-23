package com.all_the_best.knock_knock.parent.base.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.all_the_best.knock_knock.R
import com.all_the_best.knock_knock.infant.setting.view.InfantSelectIdActivity
import com.all_the_best.knock_knock.parent.home.view.ParentHomeActivity
import com.all_the_best.knock_knock.util.StatusBarUtil
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_select_mode.*

class SelectModeActivity : AppCompatActivity() {
    private val database: FirebaseDatabase = FirebaseDatabase.getInstance()
    // 데이터베이스의 인스턴스를 가져온다고 생각(즉, Root를 가져온다고 이해하면 쉬움)
    private val databaseReference: DatabaseReference = database.reference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_mode)
        overridePendingTransition(0, 0)
        StatusBarUtil.setStatusBar(this, resources.getColor(R.color.light_blue_status_bar, null))

        val intent1 = Intent(this, ParentHomeActivity::class.java)
        val intent2 = Intent(this, InfantSelectIdActivity::class.java)
        select_btn_infantmode.setOnClickListener {
            startActivity(intent2)
            overridePendingTransition(0, 0)
        }
        select_btn_parentmode.setOnClickListener {
            startActivity(intent1)
            overridePendingTransition(0, 0)
        }
        setDefaultVariableAtFirebase()
    }

    private fun setDefaultVariableAtFirebase() {
        val parentId = "부모1"
        val childName = "아이1"
        databaseReference.child(parentId).child(parentId + "의 child " + childName).child("startTalkChild")
            .setValue(false)
        databaseReference.child(parentId).child(parentId + "의 child " + childName).child("parentAcceptTalk")
            .setValue(false)
        databaseReference.child(parentId).child(parentId + "의 child " + childName).child("finishRecordChild")
            .setValue(false)
        databaseReference.child(parentId).child(parentId + "의 child " + childName).child("finishRecordParent")
            .setValue(false)
        databaseReference.child(parentId).child(parentId + "의 child " + childName).child("selectedQuestionAtDialog")
            .setValue("")
        databaseReference.child(parentId).child(parentId + "의 child " + childName).child("parentDenyTalk")
            .setValue(false)
        databaseReference.child(parentId).child(parentId + "의 child " + childName).child("finishRecordAfterFirst")
            .setValue(false)
//        databaseReference.child(parentId).child(parentId + "의 child " + childName).child("childFeel")
//            .setValue("답변 전")
//        databaseReference.child(parentId).child(parentId + "의 child " + childName).child("childPerson")
//            .setValue("답변 전")
    }
}