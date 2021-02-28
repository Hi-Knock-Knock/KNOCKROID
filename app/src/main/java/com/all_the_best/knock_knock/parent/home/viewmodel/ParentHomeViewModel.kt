package com.all_the_best.knock_knock.parent.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.all_the_best.knock_knock.parent.home.model.ParentHomeRecord

class ParentHomeViewModel : ViewModel() {
    private var tempParentHomeRecordList: List<ParentHomeRecord> =
        listOf(
            ParentHomeRecord("연주", "행복해", "친구"),
            ParentHomeRecord("지호", "속상해", "가족"),
            ParentHomeRecord("지수", "행복해", "선생님"),
            ParentHomeRecord("윤정", "속상해", "가족")
        )
    private val _parentHomeRecordList =
        MutableLiveData<MutableList<ParentHomeRecord>>()
    val parentHomeRecordList: LiveData<MutableList<ParentHomeRecord>>
        get() = _parentHomeRecordList

    fun setParentRecordList() {
        _parentHomeRecordList.value = tempParentHomeRecordList.toMutableList()
    }

}