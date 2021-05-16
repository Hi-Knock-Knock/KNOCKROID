package com.all_the_best.knock_knock.infant.setting.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.all_the_best.knock_knock.parent.home.model.ParentHomeRecord
import com.google.firebase.database.*

class InfantSelectChViewModel : ViewModel() {
    private var _chSelect = MutableLiveData(0)
    val chSelect: LiveData<Int>
        get() = _chSelect

    fun setchSelect(chNum: Int) {
        _chSelect.value = chNum
    }
}