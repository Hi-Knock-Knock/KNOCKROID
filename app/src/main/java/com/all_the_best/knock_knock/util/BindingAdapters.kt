package com.all_the_best.knock_knock.util

import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.UnderlineSpan
import android.widget.TextView
import androidx.databinding.BindingAdapter

object BindingAdapters {
    @BindingAdapter("setUnderLine")
    @JvmStatic
    fun setUnderLine(textView: TextView, delete: String) {
        val text = delete
        val spannableString = SpannableStringBuilder(text)
        spannableString.setSpan(
            UnderlineSpan(),
            0,
            text.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        textView.text = spannableString
    }

    @BindingAdapter("setTitleFromHome")
    @JvmStatic
    fun setTitleFromHome(textView: TextView, nickname: String) {
        textView.text = nickname + "의 오늘"
    }
}