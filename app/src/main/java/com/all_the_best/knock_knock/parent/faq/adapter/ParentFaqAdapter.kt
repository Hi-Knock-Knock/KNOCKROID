package com.all_the_best.knock_knock.parent.faq.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.all_the_best.knock_knock.R
import com.all_the_best.knock_knock.parent.faq.model.ParentFaqData

class ParentFaqAdapter (private val context: Context): RecyclerView.Adapter<ParentFaqViewHolder>(){
    var data = mutableListOf<ParentFaqData>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParentFaqViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.rcv_parent_faq, parent, false)

        return ParentFaqViewHolder(view)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ParentFaqViewHolder, position: Int) {
        holder.onBind(data[position])
    }

}