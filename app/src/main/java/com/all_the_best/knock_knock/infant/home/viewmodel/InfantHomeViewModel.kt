package com.all_the_best.knock_knock.infant.home.viewmodel
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.all_the_best.knock_knock.R

class InfantHomeViewModel: ViewModel() {
    //private val cookie_count_txt:TextView = findViewById(R.id.infant_home_cookie_count_txt)
    private var _cookieCount = MutableLiveData(5)
    val cookieCount: LiveData<Int>
        get() = _cookieCount

    fun setCookieCount(countNum: Int) {
        _cookieCount.value = countNum
    }
}