package com.all_the_best.knock_knock.parent_layout.rcv

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ParentFaqData(
        val index: Int,
        val title: String,
        var checked: Boolean
): Parcelable
