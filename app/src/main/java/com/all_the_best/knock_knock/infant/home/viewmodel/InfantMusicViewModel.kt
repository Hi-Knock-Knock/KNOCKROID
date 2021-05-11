package com.all_the_best.knock_knock.infant.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class InfantMusicViewModel : ViewModel() {
    private var _musicPlay = MutableLiveData(0)
    val musicPlay: LiveData<Int>
        get() = _musicPlay

    fun setmusicPlay(musicNum: Int) {
        _musicPlay.value = musicNum
    }
}