package com.all_the_best.knock_knock.parent.talk.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.all_the_best.knock_knock.R
import com.all_the_best.knock_knock.databinding.ItemParentAcceptTalkRcvBinding
import com.all_the_best.knock_knock.parent.talk.model.ParentTalkAcceptTip

class ParentTalkAcceptTipRcvAdapter :
    ListAdapter<ParentTalkAcceptTip, ParentTalkAcceptTipRcvAdapter.ParentTalkAcceptTipRcvViewHolder>(
        ParentTalkAcceptTipDiffUtil()
    ) {
    inner class ParentTalkAcceptTipRcvViewHolder(
        private val binding: ItemParentAcceptTalkRcvBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(parentTalkAcceptTip: ParentTalkAcceptTip) {
            binding.parentTalkTip = parentTalkAcceptTip
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ParentTalkAcceptTipRcvViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemParentAcceptTalkRcvBinding =
            DataBindingUtil.inflate(
                layoutInflater,
                R.layout.item_parent_accept_talk_rcv,
                parent,
                false
            )
        return ParentTalkAcceptTipRcvViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ParentTalkAcceptTipRcvViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private class ParentTalkAcceptTipDiffUtil: DiffUtil.ItemCallback<ParentTalkAcceptTip>(){
        override fun areItemsTheSame(
            oldItem: ParentTalkAcceptTip,
            newItem: ParentTalkAcceptTip
        ) =
            (oldItem.index == newItem.index)

        override fun areContentsTheSame(
            oldItem: ParentTalkAcceptTip,
            newItem: ParentTalkAcceptTip
        ) =
            (oldItem == newItem)
    }

}