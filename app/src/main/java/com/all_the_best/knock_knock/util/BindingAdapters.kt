package com.all_the_best.knock_knock.util

import android.annotation.SuppressLint
import android.net.Uri
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.text.style.UnderlineSpan
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.all_the_best.knock_knock.R
import com.bumptech.glide.Glide
import de.hdodenhof.circleimageview.CircleImageView

object BindingAdapters {
    @BindingAdapter("setUnderLine")
    @JvmStatic
    fun setUnderLine(textView: TextView, txt: String) {
        Log.d("dialog",txt)
        val text = txt
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

    @BindingAdapter("help_dialog:txt", "help_dialog:start", "help_dialog:end")
    @JvmStatic
    fun setBlueColor(textView: TextView, txt: String, start: Int, end: Int) {
        val text = txt
        val spannableString = SpannableStringBuilder(text)
        spannableString.setSpan(
            ForegroundColorSpan(textView.context.getColor(R.color.help_dialog_blue)),
            start,
            end,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        textView.text = spannableString
    }

    @BindingAdapter("setSrcFromUrl")
    @JvmStatic
    fun setSrcFromUrl(imageView: CircleImageView, uri: Uri?) {
        if (uri == null) {
            imageView.setImageResource(R.drawable.img_baby_mybaby1)
        } else {
            Glide.with(imageView.context)
                .load(uri)
                .centerCrop()
                .into(imageView)
        }
    }
}