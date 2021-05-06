package com.all_the_best.knock_knock.parent.mypage.model

import android.net.Uri
import androidx.lifecycle.LiveData

data class ParentMyPageBaby(
    var uri: String?,
    var name: String,
    val gender: String,
    val birth: String,
    val nickname: String
)