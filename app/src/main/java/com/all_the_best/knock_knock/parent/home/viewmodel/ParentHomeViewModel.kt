package com.all_the_best.knock_knock.parent.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.all_the_best.knock_knock.parent.home.model.ParentHomeRecord

class ParentHomeViewModel : ViewModel() {
    private var _goFaqFlag = MutableLiveData(false)
    val goFaqFlag: LiveData<Boolean>
        get() = _goFaqFlag

    fun setGoFaqFlag(flag: Boolean){
        _goFaqFlag.value = flag
    }

    private var tempParentHomeRecordList: List<ParentHomeRecord> =
        listOf(
            ParentHomeRecord("윤하", "행복해", "선생님"),
            ParentHomeRecord("윤지", "속상해", "가족")
        )
    private val _parentHomeRecordList =
        MutableLiveData<MutableList<ParentHomeRecord>>()
    val parentHomeRecordList: LiveData<MutableList<ParentHomeRecord>>
        get() = _parentHomeRecordList

    fun setParentRecordList() {
        _parentHomeRecordList.value = tempParentHomeRecordList.toMutableList()
    }

}