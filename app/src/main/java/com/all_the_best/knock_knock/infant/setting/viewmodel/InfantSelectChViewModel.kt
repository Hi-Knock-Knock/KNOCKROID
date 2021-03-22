package com.all_the_best.knock_knock.infant.setting.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class InfantSelectChViewModel : ViewModel() {
    private var _chSelect = MutableLiveData(1)
    val chSelect: LiveData<Int>
        get() = _chSelect

    fun setchSelect(chNum: Int) {
        _chSelect.value = chNum
    }
}