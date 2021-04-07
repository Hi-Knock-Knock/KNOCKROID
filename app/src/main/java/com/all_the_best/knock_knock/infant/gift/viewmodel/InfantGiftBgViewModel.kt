package com.all_the_best.knock_knock.infant.gift.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class InfantGiftBgViewModel  : ViewModel() {
    private var _giftSelect = MutableLiveData(0)
    val giftSelect: LiveData<Int>
        get() = _giftSelect

    fun setgiftbgSelect(giftNum: Int) {
        _giftSelect.value = giftNum
    }
}