package com.all_the_best.knock_knock.parent.mypage.adapter

import android.content.Context
import android.content.Intent
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.all_the_best.knock_knock.BR
import com.all_the_best.knock_knock.databinding.ItemParentMyBabyBinding
import com.all_the_best.knock_knock.databinding.ItemParentMyPageBabyBinding
import com.all_the_best.knock_knock.parent.mypage.model.ParentMyPageBaby
import com.all_the_best.knock_knock.parent.mypage.view.ParentMyBabyActivity

class ParentMyPageRcvAdapter<B : ViewDataBinding>(
    val context: Context,
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
                        setVariable(BR.parentMyBabyTxtDone, "확인")
                        setOnEditClick(binding)
                        setOnDoneClick(binding)
                        setOnEditPhotoClick(binding, position)
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
        Log.d("tag_getImg_adapter", getItem(position).uri.toString())
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

    private fun setOnEditClick(binding: ItemParentMyBabyBinding) {
        binding.itemMyBabyTxtEdit.setOnClickListener {
            setVisibilityForEdit(true, binding)
        }
    }

    private fun setOnDoneClick(binding: ItemParentMyBabyBinding) {
        binding.itemMyBabyTxtDone.setOnClickListener {
            setVisibilityForEdit(false, binding)
        }
    }

    private fun setOnEditPhotoClick(binding: ItemParentMyBabyBinding, position: Int) {
        binding.itemMyBabyEditPhoto.setOnClickListener {
            val imageIntent =
                Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            (context as ParentMyBabyActivity).startActivityForResult(imageIntent, position)
        }
    }

    private fun setVisibilityForEdit(isEdit: Boolean, binding: ItemParentMyBabyBinding) {
        if (isEdit) {
            binding.itemMyBabyTxtDone.visibility = View.VISIBLE
            binding.itemMyBabyTxtEdit.visibility = View.INVISIBLE
            binding.itemMyBabyEditPhoto.visibility = View.VISIBLE
            binding.itemMyBabyTxtGenderRight.visibility = View.INVISIBLE
            binding.itemMyBabyRadioGroup.visibility = View.VISIBLE
            binding.itemMyBabyEdtName.visibility = View.VISIBLE
            binding.itemMyBabyName.visibility = View.INVISIBLE
            binding.itemMyBabyEdtNickname.visibility = View.VISIBLE
            binding.itemMyBabyTxtNicknameRight.visibility = View.INVISIBLE
            binding.itemMyBabyEdtBirth.visibility = View.VISIBLE
            binding.itemMyBabyTxtBirthRight.visibility = View.INVISIBLE
        } else {
            binding.itemMyBabyTxtDone.visibility = View.INVISIBLE
            binding.itemMyBabyTxtEdit.visibility = View.VISIBLE
            binding.itemMyBabyEditPhoto.visibility = View.INVISIBLE
            binding.itemMyBabyTxtGenderRight.visibility = View.VISIBLE
            binding.itemMyBabyRadioGroup.visibility = View.INVISIBLE
            binding.itemMyBabyEdtName.visibility = View.INVISIBLE
            binding.itemMyBabyName.visibility = View.VISIBLE
            binding.itemMyBabyEdtNickname.visibility = View.INVISIBLE
            binding.itemMyBabyTxtNicknameRight.visibility = View.VISIBLE
            binding.itemMyBabyEdtBirth.visibility = View.INVISIBLE
            binding.itemMyBabyTxtBirthRight.visibility = View.VISIBLE
        }
    }
}