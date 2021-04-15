package com.all_the_best.knock_knock.parent.faq.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.all_the_best.knock_knock.BR
import com.all_the_best.knock_knock.R
import com.all_the_best.knock_knock.databinding.*
import com.all_the_best.knock_knock.parent.faq.model.ParentFaqData
import com.all_the_best.knock_knock.parent.faq.view.ParentFaqDetailActivity
import com.all_the_best.knock_knock.parent.mypage.adapter.ParentMyPageRcvAdapter
import com.all_the_best.knock_knock.parent.mypage.model.ParentMyPageBaby

class ParentFaqRcvAdapter<B : ViewDataBinding>(val layout: Int, val context: Context) :
    ListAdapter<ParentFaqData, ParentFaqRcvAdapter<B>.ParentFaqRcvViewHolder<B>>(
        ParentFaqRcvDiffUtil()
    ) {
    inner class ParentFaqRcvViewHolder<B : ViewDataBinding>(private val binding: B) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(parentFaqData: ParentFaqData) {
            when (binding) {
                is ItemParentFaqBinding -> {
                    with(binding as ItemParentFaqBinding) {
                        setVariable(BR.faqData, parentFaqData)
                        executePendingBindings()
                        setOnItemClickListenerAtFaq(binding, parentFaqData, context)
                    }
                }
                else -> {
                    with(binding as ItemParentMyScrapBinding) {
                        setVariable(BR.myScrapData, parentFaqData)
                        executePendingBindings()
                        setOnItemClickListenerAtScrap(binding, parentFaqData, context)
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ParentFaqRcvViewHolder<B> {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: B =
            DataBindingUtil.inflate(
                layoutInflater,
                layout,
                parent,
                false
            )
        return ParentFaqRcvViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ParentFaqRcvViewHolder<B>, position: Int) {
        holder.bind(getItem(position))
    }

    private class ParentFaqRcvDiffUtil : DiffUtil.ItemCallback<ParentFaqData>() {
        override fun areItemsTheSame(
            oldItem: ParentFaqData,
            newItem: ParentFaqData
        ) =
            (oldItem.title == newItem.title)

        override fun areContentsTheSame(
            oldItem: ParentFaqData,
            newItem: ParentFaqData
        ) =
            (oldItem == newItem)
    }

    private fun setOnItemClickListenerAtFaq(
        binding: ItemParentFaqBinding,
        data: ParentFaqData,
        context: Context
    ) {
        binding.itemFaqConstraint.setOnClickListener {
            val intent = Intent(context, ParentFaqDetailActivity::class.java)
            intent.putExtra("faqData", data)
            context.startActivity(intent)
        }
    }

    private fun setOnItemClickListenerAtScrap(
        binding: ItemParentMyScrapBinding,
        data: ParentFaqData,
        context: Context
    ) {
        binding.itemScrapConstraint.setOnClickListener {
            val intent = Intent(context, ParentFaqDetailActivity::class.java)
            intent.putExtra("faqData", data)
            context.startActivity(intent)
        }
    }

}