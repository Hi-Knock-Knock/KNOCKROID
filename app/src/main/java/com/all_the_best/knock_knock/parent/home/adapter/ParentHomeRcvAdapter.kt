package com.all_the_best.knock_knock.parent.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.all_the_best.knock_knock.R
import com.all_the_best.knock_knock.databinding.ItemParentHomeBinding
import com.all_the_best.knock_knock.parent.home.model.ParentHomeRecord

class ParentHomeRcvAdapter :
    ListAdapter<ParentHomeRecord, ParentHomeRcvAdapter.ParentHomeRcvViewHolder>(
        ParentHomeRecordDiffUtil()
    ) {
    inner class ParentHomeRcvViewHolder(private val binding: ItemParentHomeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(parentHomeRecordData: ParentHomeRecord) {
            binding.parentHomeRecordData = parentHomeRecordData
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParentHomeRcvViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemParentHomeBinding =
            DataBindingUtil.inflate(
                layoutInflater,
                R.layout.item_parent_home,
                parent,
                false
            )
        return ParentHomeRcvViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ParentHomeRcvViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private class ParentHomeRecordDiffUtil : DiffUtil.ItemCallback<ParentHomeRecord>() {
        override fun areItemsTheSame(
            oldItem: ParentHomeRecord,
            newItem: ParentHomeRecord
        ) =
            (oldItem.nickname == newItem.nickname)

        override fun areContentsTheSame(
            oldItem: ParentHomeRecord,
            newItem: ParentHomeRecord
        ) =
            (oldItem == newItem)
    }

}
