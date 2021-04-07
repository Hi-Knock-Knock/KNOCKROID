package com.all_the_best.knock_knock.infant.talk.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class InfantTalkLottieViewModel : ViewModel() {
    private var _lottieSelect = MutableLiveData(0)
    val lottieSelect: LiveData<Int>
        get() = _lottieSelect

    fun setlottieSelect(lottieNum: Int) {
        _lottieSelect.value = lottieNum
    }
}