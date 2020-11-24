package com.all_the_best.knock_knock.parent_layout

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.all_the_best.knock_knock.R

class ParentHomeViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){
    private val imageNum: ImageView = itemView.findViewById(R.id.rcv_img)

    fun onBind(data: ParentHomeData){
        imageNum.setImageResource(data.imageNum)
    }
}