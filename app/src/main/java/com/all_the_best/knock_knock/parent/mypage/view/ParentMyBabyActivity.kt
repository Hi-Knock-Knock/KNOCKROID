package com.all_the_best.knock_knock.parent.mypage.view

import android.app.Activity
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.observe
import com.all_the_best.knock_knock.R
import com.all_the_best.knock_knock.databinding.ActivityParentMyBabyBinding
import com.all_the_best.knock_knock.databinding.ItemParentMyBabyBinding
import com.all_the_best.knock_knock.databinding.ItemParentMyPageBabyBinding
import com.all_the_best.knock_knock.parent.mypage.adapter.ParentMyPageRcvAdapter
import com.all_the_best.knock_knock.parent.mypage.viewmodel.ParentMyPageViewModel
import com.all_the_best.knock_knock.util.StatusBarUtil
import com.bumptech.glide.Glide
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.io.File

class ParentMyBabyActivity : AppCompatActivity() {
    private lateinit var binding: ActivityParentMyBabyBinding
    private val parentMyBabyViewModel: ParentMyPageViewModel by viewModels()
    private var firebaseStorage: FirebaseStorage = FirebaseStorage.getInstance()

    override fun onResume() {
        super.onResume()
        parentMyBabyViewModel.getProfileImgFromStorage()
        parentMyBabyViewModel.setParentMyPageBabyList()
        binding.parentMyBabyRcv.adapter?.notifyDataSetChanged()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        StatusBarUtil.setStatusBar(this, resources.getColor(R.color.blue_status_bar, null))
        binding = DataBindingUtil.setContentView(this, R.layout.activity_parent_my_baby)
        parentMyBabyViewModel.getProfileImgFromStorage()
        parentMyBabyViewModel.setParentMyPageBabyList()
        setParentMyBabyRcvAdapter()
        binding.parentMyBabyRcv.adapter?.notifyDataSetChanged()
        setOnClickListenerForBtnGoBack()
        setParentMyBabyObserve()
        //setUriObserve()
        Log.d("tag", parentMyBabyViewModel.tempParentMyPageBabyList[0].uri.toString())
    }

    private fun setOnClickListenerForBtnGoBack() {
        binding.parentMyBabyBtnBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun setParentMyBabyRcvAdapter() {
        val parentMyBabyRcvAdapter =
            ParentMyPageRcvAdapter<ItemParentMyBabyBinding>(this, R.layout.item_parent_my_baby)
        binding.parentMyBabyRcv.adapter = parentMyBabyRcvAdapter
    }

    private fun setParentMyBabyObserve() {
        parentMyBabyViewModel.parentMyPageBabyList.observe(this) { parentMyBabyList ->
            parentMyBabyList.let {
                if (binding.parentMyBabyRcv.adapter != null) with(binding.parentMyBabyRcv.adapter as ParentMyPageRcvAdapter<*>) {
                    submitList(parentMyBabyList)
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && data != null) {
            Log.d("tag", "onactivity")
            val selectedImage: Uri? = data.data
            uploadImgUri(selectedImage!!, requestCode)
            parentMyBabyViewModel.tempParentMyPageBabyList[requestCode].uri = selectedImage
            binding.parentMyBabyRcv.adapter?.notifyDataSetChanged()
        }
    }

    private fun uploadImgUri(file: Uri, listNum: Int) {
        // val file = Uri.fromFile(file)
        firebaseStorage.reference.child("imageFile").child("imageUri($listNum).png")
            .putFile(file).addOnCompleteListener {
                if (it.isSuccessful) {
                    Log.d("tag", "upload success")
                    parentMyBabyViewModel.getProfileImgFromStorage()
                }
            }
    }

    private fun setUriObserve() {
        parentMyBabyViewModel.uri0.observe(this) {
            it.let {
                binding.parentMyBabyRcv.adapter?.notifyItemChanged(
                    0
                )
            }
        }
        parentMyBabyViewModel.uri1.observe(this) {
            it.let {
                binding.parentMyBabyRcv.adapter?.notifyItemChanged(
                    1
                )
            }
        }
        parentMyBabyViewModel.uri2.observe(this) {
            it.let {
                binding.parentMyBabyRcv.adapter?.notifyItemChanged(
                    2
                )
            }
        }
        parentMyBabyViewModel.uri3.observe(this) {
            it.let {
                binding.parentMyBabyRcv.adapter?.notifyItemChanged(
                    3
                )
            }
        }
    }
}