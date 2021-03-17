package com.all_the_best.knock_knock.parent.mypage.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.all_the_best.knock_knock.parent.mypage.model.ParentMyPageBaby

class ParentMyPageViewModel : ViewModel() {
    private var tempParentMyPageBabyList: List<ParentMyPageBaby> =
        listOf(
            ParentMyPageBaby("연주", "남", "2018.06.13", "쥬쥬"),
            ParentMyPageBaby("지수", "여", "2018.07.13", "슈슈"),
            ParentMyPageBaby("지호", "남", "2018.08.13", "죠죠"),
            ParentMyPageBaby("윤정", "여", "2018.09.13", "졍졍")
        )

    private val _parentMyPageBabyList = MutableLiveData<MutableList<ParentMyPageBaby>>()
    val parentMyPageBabyList: LiveData<MutableList<ParentMyPageBaby>>
        get() = _parentMyPageBabyList

    fun setParentMyPageBabyList() {
        _parentMyPageBabyList.value = tempParentMyPageBabyList.toMutableList()
    }
}