package com.all_the_best.knock_knock.parent_layout.rcv

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.all_the_best.knock_knock.R

class ParentHomeAdapter(private val context: Context): RecyclerView.Adapter<ParentHomeViewHolder>(){
    var data = mutableListOf<ParentHomeData>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParentHomeViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.rcv_parent_home, parent, false)

        return ParentHomeViewHolder(view)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ParentHomeViewHolder, position: Int) {
        holder.onBind(data[position])
    }
}