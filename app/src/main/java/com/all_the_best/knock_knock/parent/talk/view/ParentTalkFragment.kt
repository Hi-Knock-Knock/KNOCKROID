package com.all_the_best.knock_knock.parent.talk.view

import android.app.AlertDialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.InsetDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.all_the_best.knock_knock.R
import com.all_the_best.knock_knock.databinding.FragmentParentTalkBinding
import com.all_the_best.knock_knock.databinding.HelpDialogBinding
import com.all_the_best.knock_knock.databinding.TalkDialogBinding
import com.all_the_best.knock_knock.parent.alarm.view.ParentNoticeActivity
import com.all_the_best.knock_knock.parent.mypage.view.ParentMyPageActivity
import com.all_the_best.knock_knock.parent.setting.view.ParentSettingActivity
import com.all_the_best.knock_knock.util.FragmentOnBackPressed
import com.all_the_best.knock_knock.util.StatusBarUtil
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.fragment_parent_talk.*
import kotlinx.android.synthetic.main.help_dialog.view.*
import kotlinx.android.synthetic.main.talk_dialog.view.*


class ParentTalkFragment : Fragment(), FragmentOnBackPressed,
    NavigationView.OnNavigationItemSelectedListener {
    private lateinit var binding: FragmentParentTalkBinding
    private lateinit var dialogBinding: HelpDialogBinding
    private lateinit var refuseDialogBinding: TalkDialogBinding

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
        dialogBinding = DataBindingUtil.inflate(inflater, R.layout.help_dialog, container, false)
        dialogBinding.txtTitle = "여기서 보내는 질문이란?"
        dialogBinding.txtContentTop =
            "부모가 아이와 실시간 대화가\n불가능할 경우 선택한\n한가지 질문으로 캐릭터가 아이에게\n부모 대신 물어보게 됩니다."
        dialogBinding.txtContentBottom = "단 한 개의 질문만\n선택할 수 있으며,\n아이가 대화를 시도하기 전\n질문을 수정할 수 있습니다."
        refuseDialogBinding =
            DataBindingUtil.inflate(inflater, R.layout.talk_dialog, container, false)
        refuseDialogBinding.txtTitle = "실시간 대화 거절"
        refuseDialogBinding.txtEdit = "수정하기"
        setOnClickListenerForBtnHamburger()
        setOnClickListenerForBtnSubmit()
        setOnClickListenerForBtnHelp()
        setOnClickListnerForSwitchMode()
        setOnClickListenerFroBtnRefuse()
        return binding.root
    }

    private fun setOnClickListnerForSwitchMode() {
        binding.talkVerBtnSwitchMode.setOnClickListener {
            binding.realTalkVerConstraint.visibility = View.VISIBLE
            binding.talkVerConstraint.visibility = View.INVISIBLE
            StatusBarUtil.setStatusBar(
                requireActivity(),
                resources.getColor(R.color.light_blue_status_bar, null)
            )
        }
        binding.realTalkVerBtnSwitchMode.setOnClickListener {
            binding.realTalkVerConstraint.visibility = View.INVISIBLE
            binding.talkVerConstraint.visibility = View.VISIBLE
            StatusBarUtil.setStatusBar(
                requireActivity(),
                resources.getColor(R.color.blue_status_bar, null)
            )
        }
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
            dialogBinding.root.help_txt_ok.setOnClickListener {
                dialog.dismiss()
            }
            dialog.apply {
                window!!.setBackgroundDrawable(inset)
                show()
            }
        }

    }

    private fun setOnClickListenerFroBtnRefuse() {
        binding.realTalkTxtNo.setOnClickListener {
            refuseDialogBinding.apply {
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

            refuseDialogBinding.root.talk_dialog_txt_ok.setOnClickListener {
                //dialog.dismiss()
                refuseDialogBinding.apply {
                    talkDialogConstraintFinish.visibility = View.VISIBLE
                    talkDialogConstraintBtnNoOk.visibility = View.INVISIBLE
                    talkDialogConstraintRadioBtn.visibility = View.GONE
                    talkDialogTxtEdit.visibility = View.GONE
                    talkDialogTxtSubSelected.visibility = View.GONE
                    talkDialogConstraintSubmit.visibility = View.VISIBLE
                }
            }

            refuseDialogBinding.root.talk_dialog_txt_edit.setOnClickListener {
                refuseDialogBinding.apply {
                    talkDialogConstraintRadioBtn.visibility = View.VISIBLE
                    talkDialogTxtEdit.visibility = View.GONE
                }
            }

            refuseDialogBinding.apply {
                root.talk_dialog_txt_no.setOnClickListener {
                    dialog.dismiss()
                }
                root.talk_dialog_txt_finish.setOnClickListener {
                    dialog.dismiss()
                }
            }

            dialog.apply {
                window!!.setBackgroundDrawable(inset)
                show()
            }
        }
    }

    private fun setOnClickListenerForBtnSubmit() {
        //저장하기 버튼 클릭시 선택한 질문 텍스트 벼경
        binding.talkBtnSubmit.setOnClickListener {
            binding.talkTxtSelectedQuestion.text =
                when (binding.talkRadiogroup.checkedRadioButtonId) {
                    R.id.rb1 -> "없음"
                    R.id.rb2 -> "엄마랑 아빠랑 싸우면 기분이 어때?"
                    R.id.rb3 -> "동생은 어떤 존재야?"
                    R.id.rb4 -> "동생이랑 노는 거 즐거워?"
                    R.id.rb5 -> "엄마가 어떻게 해줬으면 좋겠어?"
                    R.id.rb6 -> "아빠한테 속상한 거 있어?"
                    R.id.rb7 -> "엄마한테 속상한 거 있어?"
                    R.id.rb8 -> "엄마랑 아빠랑 싸우면 기분이 어때?"
                    R.id.rb9 -> "동생은 어떤 존재야?"
                    R.id.rb10 -> "동생이랑 노는 거 즐거워?"
                    R.id.rb11 -> "엄마가 어떻게 해줬으면 좋겠어?"
                    R.id.rb12 -> "동생이랑 노는 거 즐거워?"
                    R.id.rb13 -> "엄마가 어떻게 해줬으면 좋겠어?"
                    R.id.rb14 -> "동생이랑 노는 거 즐거워?"
                    R.id.rb15 -> "엄마가 어떻게 해줬으면 좋겠어?"
                    else -> "없음"
                }
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

}