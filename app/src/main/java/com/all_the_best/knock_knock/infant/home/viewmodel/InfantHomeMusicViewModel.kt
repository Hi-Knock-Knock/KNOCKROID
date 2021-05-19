package com.all_the_best.knock_knock.infant.home.viewmodel

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.all_the_best.knock_knock.parent.home.model.ParentHomeRecord
import com.google.firebase.database.*

class InfantHomeMusicViewModel : ViewModel() {
    private var _musicPlay = MutableLiveData(0)
    val musicPlay: LiveData<Int>
        get() = _musicPlay

    fun setmusicPlay(musicNum: Int) {
        _musicPlay.value = musicNum
    }

}