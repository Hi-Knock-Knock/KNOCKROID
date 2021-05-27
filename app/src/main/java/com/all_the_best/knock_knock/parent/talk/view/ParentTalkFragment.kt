package com.all_the_best.knock_knock.parent.talk.view

import android.app.AlertDialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.InsetDrawable
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.all_the_best.knock_knock.R
import com.all_the_best.knock_knock.databinding.DialogHelpBinding
import com.all_the_best.knock_knock.databinding.DialogTalkBinding
import com.all_the_best.knock_knock.databinding.FragmentParentTalkBinding
import com.all_the_best.knock_knock.parent.alarm.view.ParentNoticeActivity
import com.all_the_best.knock_knock.parent.mypage.view.ParentMyPageActivity
import com.all_the_best.knock_knock.parent.setting.view.ParentSettingActivity
import com.all_the_best.knock_knock.util.FragmentOnBackPressed
import com.all_the_best.knock_knock.util.StatusBarUtil
import com.google.android.material.navigation.NavigationView
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_parent_talk.*


class ParentTalkFragment : Fragment(), FragmentOnBackPressed,
    NavigationView.OnNavigationItemSelectedListener {
    private lateinit var binding: FragmentParentTalkBinding
    private lateinit var dialogBinding: DialogHelpBinding
    private lateinit var refuseDialogBinding: DialogTalkBinding
    private val database: FirebaseDatabase = FirebaseDatabase.getInstance()
    private val databaseReference: DatabaseReference = database.reference
    val parentId = "부모1"
    val childName = "아이1"

    override fun onBackPressed(): Boolean {
        return if (binding.talkDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            Log.d("프래그먼트", "대화하기 if")
            binding.talkDrawerLayout.closeDrawers()
            true
        } else {
            false
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_parent_talk, container, false)
        binding.talkNavigationView.setNavigationItemSelectedListener(this)
        dialogBinding = DataBindingUtil.inflate(inflater, R.layout.dialog_help, container, false)
        dialogBinding.txtTitle = "여기서 보내는 질문이란?"
        dialogBinding.txtContentTop =
            "부모가 아이와 실시간 대화가\n불가능할 경우 선택한\n한가지 질문으로 캐릭터가 아이에게\n부모 대신 물어보게 됩니다."
        dialogBinding.txtContentBottom = "단 한 개의 질문만\n선택할 수 있으며,\n아이가 대화를 시도하기 전\n질문을 수정할 수 있습니다."
        refuseDialogBinding =
            DataBindingUtil.inflate(inflater, R.layout.dialog_talk, container, false)
        refuseDialogBinding.txtTitle = "실시간 대화 거절"
        refuseDialogBinding.txtEdit = "수정하기"
        setSelectedQuestion()
        setOnClickListenerForBtnHamburger()
        setOnClickListenerForBtnSubmit()
        setOnClickListenerForBtnHelp()
        setOnClickListenerForBtnAccept()
        setOnClickListenerForBtnRefuse()
        setMode()
        return binding.root
    }

    private fun startTimerBeforeDeny() {
        binding.realTalkTimerTime.apply {
            base = SystemClock.elapsedRealtime() + 16000
            start()
            setOnChronometerTickListener {
                if (it.base <= SystemClock.elapsedRealtime() + 0) {
                    stop()
                    binding.realTalkConstraintBeforeSubmit.visibility = View.INVISIBLE
                    binding.realTalkConstraintAfterSubmit.visibility = View.VISIBLE
                    setParentDenyTalkAtFirebase(true)
                    setSelectedQuestionDialogAtFirebase(binding.selectedQuestion.toString())
                }
            }
        }
    }

    private fun setSelectedQuestion() {
        val myValue: DatabaseReference =
            databaseReference.child(parentId).child(parentId + "의 child " + childName)
                .child("selectedQuestion")
        myValue.get().addOnSuccessListener {
            binding.selectedQuestion = it.value.toString()
        }.addOnFailureListener {
            Log.e("firebase", "Error getting data", it)
        }
        val radioIndex: DatabaseReference =
            databaseReference.child(parentId).child(parentId + "의 child " + childName)
                .child("selectedQuestionIndex")
        radioIndex.get().addOnSuccessListener {
            binding.talkRadiogroup.check(
                when (it.value.toString()) {
                    "1" -> R.id.rb1
                    "2" -> R.id.rb2
                    "3" -> R.id.rb3
                    "4" -> R.id.rb4
                    "5" -> R.id.rb5
                    "6" -> R.id.rb6
                    "7" -> R.id.rb7
                    "8" -> R.id.rb8
                    "9" -> R.id.rb9
                    "10" -> R.id.rb10
                    "11" -> R.id.rb11
                    else -> R.id.rb1
                }
            )

        }.addOnFailureListener {
            Log.e("firebase", "Error getting data", it)
        }
    }

    private fun setMode() {
        val myValue: DatabaseReference =
            databaseReference.child(parentId).child(parentId + "의 child " + childName)
                .child("startTalkChild")
        myValue.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.value as Boolean) {
                    binding.apply {
                        startTimerBeforeDeny()
                        realTalkConstraintBeforeSubmit.visibility = View.VISIBLE
                        realTalkConstraintAfterSubmit.visibility = View.GONE
                        realTalkVerConstraint.visibility = View.VISIBLE
                        talkVerConstraint.visibility = View.INVISIBLE
                    }
                    StatusBarUtil.setStatusBar(
                        requireActivity(),
                        resources.getColor(R.color.light_blue_status_bar, null)
                    )
                } else {
                    binding.apply {
                        realTalkVerConstraint.visibility = View.INVISIBLE
                        talkVerConstraint.visibility = View.VISIBLE
                    }
                    StatusBarUtil.setStatusBar(
                        requireActivity(),
                        resources.getColor(R.color.blue_status_bar, null)
                    )
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }

    private fun setOnClickListenerForBtnHelp() {
        //도움말 버튼(물음표 버튼) 누르면 도움말 창 띄우기
        binding.talkBtnHelp.setOnClickListener {
            val builder = AlertDialog.Builder(requireContext())
            val dialog = builder.setView(dialogBinding.root).create()
            val color = ColorDrawable(Color.TRANSPARENT)
            val inset = InsetDrawable(color, 40)
            if (dialogBinding.root.parent != null) (dialogBinding.root.parent as ViewGroup).removeView(
                dialogBinding.root
            )
            dialogBinding.helpTxtOk.setOnClickListener {
                dialog.dismiss()
            }
            dialog.apply {
                window!!.setBackgroundDrawable(inset)
                show()
            }
        }
    }

    private fun setParentAcceptTalkAtFirebase(isAccept: Boolean) {
        databaseReference.child(parentId).child(parentId + "의 child " + childName)
            .child("parentAcceptTalk")
            .setValue(isAccept)
    }

    private fun setOnClickListenerForBtnAccept() {
        binding.realTalkTxtOk.setOnClickListener {
            setParentAcceptTalkAtFirebase(true)
            val intent = Intent(requireContext(), ParentRealTalkActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setOnClickListenerForBtnRefuse() {
        binding.realTalkTxtNo.setOnClickListener {
            setParentAcceptTalkAtFirebase(false)
            with(refuseDialogBinding) {
                talkDialogTxtSelectedQuestion.text = binding.selectedQuestion.toString()
                talkDialogTxtEdit.visibility = View.VISIBLE
                talkDialogConstraintRadioBtn.visibility = View.GONE
                talkDialogConstraintFinish.visibility = View.INVISIBLE
                talkDialogConstraintBtnNoOk.visibility = View.VISIBLE
                talkDialogTxtEdit.visibility = View.VISIBLE
                talkDialogTxtSubSelected.visibility = View.VISIBLE
                talkDialogConstraintSubmit.visibility = View.GONE
                talkDialogRadiogroup.clearCheck()
            }

            val builder = AlertDialog.Builder(requireContext())
            val dialog = builder.setView(refuseDialogBinding.root).create()
            val color = ColorDrawable(Color.TRANSPARENT)
            val inset = InsetDrawable(color, 40)

            if (refuseDialogBinding.root.parent != null) (refuseDialogBinding.root.parent as ViewGroup).removeView(
                refuseDialogBinding.root
            )

            with(refuseDialogBinding) {
                talkDialogTxtOk.setOnClickListener {
                    refuseDialogBinding.talkDialogTimerTime.stop()
                    talkDialogConstraintFinish.visibility = View.VISIBLE
                    talkDialogConstraintBtnNoOk.visibility = View.INVISIBLE
                    talkDialogConstraintRadioBtn.visibility = View.GONE
                    talkDialogTxtEdit.visibility = View.GONE
                    talkDialogTxtSubSelected.visibility = View.GONE
                    talkDialogConstraintSubmit.visibility = View.VISIBLE
                }
                talkDialogTxtEdit.setOnClickListener {
                    talkDialogConstraintRadioBtn.visibility = View.VISIBLE
                    talkDialogTxtEdit.visibility = View.GONE
                }
                talkDialogTxtNo.setOnClickListener { dialog.dismiss() }
                talkDialogTxtFinish.setOnClickListener {
                    dialog.dismiss()
                    binding.realTalkConstraintBeforeSubmit.visibility = View.GONE
                    binding.realTalkConstraintAfterSubmit.visibility = View.VISIBLE
                }
                talkDialogRadiogroup.setOnCheckedChangeListener { questionGroup, checkedId ->
                    setParentDenyTalkAtFirebase(true)
                    talkDialogTxtSelectedQuestion.text =
                        when (checkedId) {
                            R.id.rb1_dialog -> getString(R.string.talk_question_0)
                            R.id.rb2_dialog -> getString(R.string.talk_question_1)
                            R.id.rb3_dialog -> getString(R.string.talk_question_2)
                            R.id.rb4_dialog -> getString(R.string.talk_question_3)
                            R.id.rb5_dialog -> getString(R.string.talk_question_4)
                            R.id.rb6_dialog -> getString(R.string.talk_question_5)
                            R.id.rb7_dialog -> getString(R.string.talk_question_6)
                            R.id.rb8_dialog -> getString(R.string.talk_question_7)
                            R.id.rb9_dialog -> getString(R.string.talk_question_8)
                            R.id.rb10_dialog -> getString(R.string.talk_question_9)
                            R.id.rb11_dialog -> getString(R.string.talk_question_10)
                            else -> binding.selectedQuestion.toString()
                        }
                    setSelectedQuestionDialogAtFirebase(talkDialogTxtSelectedQuestion.text.toString())
                }
            }

            dialog.apply {
                window!!.setBackgroundDrawable(inset)
                show()
                refuseDialogBinding.talkDialogTimerTime.apply {
                    base = SystemClock.elapsedRealtime() + 61000
                    start()
                    setOnChronometerTickListener {
                        if (it.base <= SystemClock.elapsedRealtime() + 0) {
                            stop()
                            setVisibilityAfterTimerAtDialog()
                            setSelectedQuestionDialogAtFirebase(binding.selectedQuestion.toString())
                        }
                    }
                }
            }
        }
    }

    private fun setVisibilityAfterTimerAtDialog(){
        with(refuseDialogBinding){
            talkDialogConstraintFinish.visibility = View.VISIBLE
            talkDialogConstraintBtnNoOk.visibility = View.INVISIBLE
            talkDialogConstraintRadioBtn.visibility = View.GONE
            talkDialogTxtEdit.visibility = View.GONE
            talkDialogTxtSubSelected.visibility = View.GONE
            talkDialogConstraintSubmit.visibility = View.VISIBLE
        }
    }

    private fun setOnClickListenerForBtnSubmit() {
        //저장하기 버튼 클릭시 선택한 질문 텍스트 벼경
        binding.talkBtnSubmit.setOnClickListener {
            binding.talkTxtSelectedQuestion.text =
                when (binding.talkRadiogroup.checkedRadioButtonId) {
                    R.id.rb1 -> getString(R.string.talk_question_0)
                    R.id.rb2 -> getString(R.string.talk_question_1)
                    R.id.rb3 -> getString(R.string.talk_question_2)
                    R.id.rb4 -> getString(R.string.talk_question_3)
                    R.id.rb5 -> getString(R.string.talk_question_4)
                    R.id.rb6 -> getString(R.string.talk_question_5)
                    R.id.rb7 -> getString(R.string.talk_question_6)
                    R.id.rb8 -> getString(R.string.talk_question_7)
                    R.id.rb9 -> getString(R.string.talk_question_8)
                    R.id.rb10 -> getString(R.string.talk_question_9)
                    R.id.rb11 -> getString(R.string.talk_question_10)
                    else -> getString(R.string.talk_question_0)
                }
            var index = when (binding.talkRadiogroup.checkedRadioButtonId) {
                R.id.rb1 -> 1
                R.id.rb2 -> 2
                R.id.rb3 -> 3
                R.id.rb4 -> 4
                R.id.rb5 -> 5
                R.id.rb6 -> 6
                R.id.rb7 -> 7
                R.id.rb8 -> 8
                R.id.rb9 -> 9
                R.id.rb10 -> 10
                R.id.rb11 -> 11
                else -> 1
            }
            setSelectedQuestionAtFirebase(binding.talkTxtSelectedQuestion.text.toString(), index)
        }
    }

    private fun setOnClickListenerForBtnHamburger() {
        //햄버거바 클릭시 DrawerLayout 열림
        binding.talkBtnHamburger.setOnClickListener {
            talk_drawer_layout.openDrawer(GravityCompat.START) //네비게이션 드로어 열기
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        lateinit var intent: Intent
        when (item.itemId) {
            R.id.item_mypage -> intent = Intent(context, ParentMyPageActivity::class.java)
            R.id.item_alarm -> intent = Intent(context, ParentNoticeActivity::class.java)
            R.id.item_settings -> intent = Intent(context, ParentSettingActivity::class.java)
        }
        startActivity(intent)
        return false
    }

    private fun setSelectedQuestionAtFirebase(question: String, index: Int) {
        databaseReference.child(parentId).child(parentId + "의 child " + childName)
            .child("selectedQuestion")
            .setValue(question)
        databaseReference.child(parentId).child(parentId + "의 child " + childName)
            .child("selectedQuestionIndex")
            .setValue(index)
    }

    private fun setSelectedQuestionDialogAtFirebase(question: String) {
        databaseReference.child(parentId).child(parentId + "의 child " + childName)
            .child("selectedQuestionAtDialog")
            .setValue(question)
    }

    private fun setParentDenyTalkAtFirebase(isDeny: Boolean) {
        databaseReference.child(parentId).child(parentId + "의 child " + childName)
            .child("parentDenyTalk")
            .setValue(isDeny)
    }

}