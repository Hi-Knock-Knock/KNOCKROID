package com.all_the_best.knock_knock.util

import android.annotation.SuppressLint
import android.media.Image
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
import com.bumptech.glide.load.engine.DiskCacheStrategy
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

    @BindingAdapter("setQuestion1FromHome")
    @JvmStatic
    fun setQuestion1FromHome(textView: TextView, nickname: String) {
        textView.text = nickname + "야 오늘 기분이 어때?"
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
    fun setSrcFromUrl(imageView: ImageView, uri: String?) {
        if (uri == null) {
            Log.d("tag_img_binding", "null")
            imageView.setImageResource(R.drawable.img_infant_home_bg_sea3)
        } else {
            Log.d("tag_img_binding", uri)
            Glide.with(imageView.context)
                .load(uri)
                .centerCrop()
                .error(R.drawable.img_baby_mybaby1)
                .into(imageView)
        }
    }
}