package com.all_the_best.knock_knock.parent.mypage.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.all_the_best.knock_knock.BR
import com.all_the_best.knock_knock.R
import com.all_the_best.knock_knock.databinding.ItemParentMyBabyBinding
import com.all_the_best.knock_knock.databinding.ItemParentMyPageBabyBinding
import com.all_the_best.knock_knock.parent.mypage.model.ParentMyPageBaby

class ParentMyPageRcvAdapter<B : ViewDataBinding>(
    val layout: Int
) :
    ListAdapter<ParentMyPageBaby, ParentMyPageRcvAdapter<B>.ParentMyPageRcvViewHolder<B>>(
        ParentMyPageRcvDiffUtil()
    ) {
    inner class ParentMyPageRcvViewHolder<B : ViewDataBinding>(private val binding: B) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(parentMyPageBabyData: ParentMyPageBaby) {
            when (binding) {
                is ItemParentMyPageBabyBinding -> {
                    with(binding as ItemParentMyPageBabyBinding) {
                        setVariable(BR.parentMyPageBabyData, parentMyPageBabyData)
                        executePendingBindings()
                    }
                }
                else -> {
                    with(binding as ItemParentMyBabyBinding) {
                        setVariable(BR.parentMyBabyData, parentMyPageBabyData)
                        setVariable(BR.parentMyBabyTxtEdit, "수정하기")
                        executePendingBindings()
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ParentMyPageRcvViewHolder<B> {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: B =
            DataBindingUtil.inflate(
                layoutInflater,
                layout,
                parent,
                false
            )
        return ParentMyPageRcvViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ParentMyPageRcvViewHolder<B>, position: Int) {
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