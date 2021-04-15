package com.all_the_best.knock_knock.parent.faq.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.all_the_best.knock_knock.R
import com.all_the_best.knock_knock.parent.faq.model.ParentFaqData

class ParentFaqViewModel : ViewModel() {
    private val _faqList = MutableLiveData<MutableList<ParentFaqData>>()
    val faqList: LiveData<MutableList<ParentFaqData>>
        get() = _faqList

    private val _myScrapList = MutableLiveData<MutableList<ParentFaqData>>()
    val myScrapList: LiveData<MutableList<ParentFaqData>>
        get() = _myScrapList

    private var tempFaqList: List<ParentFaqData> = listOf(
        ParentFaqData(0, "자존감이\n" + "낮은 아이", R.string.faq_data_content, false),
        ParentFaqData(1, "아이를\n" + "바르게\n" + "대하는 방법", R.string.faq_data_content, false),
        ParentFaqData(2, "자존감이\n" + "낮은 아이", R.string.faq_data_content, false),
        ParentFaqData(3, "자존감이\n" + "낮은 아이", R.string.faq_data_content, false),
        ParentFaqData(4, "아이가\n" + "싫어하는 행동을\n" + "표현한 경우", R.string.faq_data_content, false),
        ParentFaqData(5, "아이가\n" + "싫어하는 행동을\n" + "표현한 경우", R.string.faq_data_content, false),
        ParentFaqData(6, "참는 성향이\n" + "있는 아이", R.string.faq_data_content, false),
        ParentFaqData(7, "참는 성향이\n" + "있는 아이", R.string.faq_data_content, false),
        ParentFaqData(8, "아이를\n" + "바르게\n" + "대하는 방법", R.string.faq_data_content, false)
    )

    private var tempMyScrapList: List<ParentFaqData> = listOf(
        ParentFaqData(0, "자존감이 낮은 아이", R.string.faq_data_content, true),
        ParentFaqData(1, "아이를 바르게 대하는 방법", R.string.faq_data_content, true),
        ParentFaqData(2, "자존감이 낮은 아이", R.string.faq_data_content, true),
        ParentFaqData(4, "아이가 싫어하는 행동을 표현한 경우", R.string.faq_data_content, true),
        ParentFaqData(6, "참는 성향이 있는 아이", R.string.faq_data_content, true),
        ParentFaqData(8, "아이를 바르게 대하는 방법", R.string.faq_data_content, true)
    )

    fun setFaqList() {
        _faqList.value = tempFaqList.toMutableList()
    }

    fun setMyScrapList() {
        _myScrapList.value = tempMyScrapList.toMutableList()
    }
}