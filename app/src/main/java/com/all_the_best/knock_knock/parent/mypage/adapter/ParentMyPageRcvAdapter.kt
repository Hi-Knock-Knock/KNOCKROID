package com.all_the_best.knock_knock.parent.mypage.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.all_the_best.knock_knock.R
import com.all_the_best.knock_knock.databinding.ItemParentMyPageBabyBinding
import com.all_the_best.knock_knock.parent.mypage.model.ParentMyPageBaby

class ParentMyPageRcvAdapter :
    ListAdapter<ParentMyPageBaby, ParentMyPageRcvAdapter.ParentMyPageRcvViewHolder>(
        ParentMyPageRcvDiffUtil()
    ) {
    inner class ParentMyPageRcvViewHolder(private val binding: ItemParentMyPageBabyBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(parentMyPageBabyData: ParentMyPageBaby) {
            binding.parentMyPageBabyData = parentMyPageBabyData
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParentMyPageRcvViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemParentMyPageBabyBinding =
            DataBindingUtil.inflate(
                layoutInflater,
                R.layout.item_parent_my_page_baby,
                parent,
                false
            )
        return ParentMyPageRcvViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ParentMyPageRcvViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private class ParentMyPageRcvDiffUtil : DiffUtil.ItemCallback<ParentMyPageBaby>() {
        override fun areItemsTheSame(
            oldItem: ParentMyPageBaby,
            newItem: ParentMyPageBaby
        ) =
            (oldItem.name == newItem.name)

        override fun areContentsTheSame(
            oldItem: ParentMyPageBaby,
            newItem: ParentMyPageBaby
        ) =
            (oldItem == newItem)
    }
}