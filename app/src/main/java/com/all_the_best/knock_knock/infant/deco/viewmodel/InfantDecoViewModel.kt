package com.all_the_best.knock_knock.infant.deco.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class InfantDecoViewModel : ViewModel() {
    private var _bgSelect = MutableLiveData(1)
    val bgSelect: LiveData<Int>
        get() = _bgSelect

    fun setBgSelect(bgNum: Int) {
        _bgSelect.value = bgNum
    }
}