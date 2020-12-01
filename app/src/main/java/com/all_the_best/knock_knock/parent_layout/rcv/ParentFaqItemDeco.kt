package com.all_the_best.knock_knock.parent_layout.rcv

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class ParentFaqItemDeco(private val space: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
    ) {
        outRect.top = space
        outRect.bottom = space
        outRect.left = space
        outRect.right = space
    }
}