package com.all_the_best.knock_knock.parent_layout.rcv

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class ParentFaqItemDeco(private val horizontalSpace: Int, private val verticalSpace: Int) :
    RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.top = verticalSpace
        outRect.bottom = verticalSpace
        outRect.left = horizontalSpace
        outRect.right = horizontalSpace
    }
}