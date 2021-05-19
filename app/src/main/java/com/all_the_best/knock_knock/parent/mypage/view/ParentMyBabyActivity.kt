package com.all_the_best.knock_knock.parent.mypage.view

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.observe
import com.all_the_best.knock_knock.R
import com.all_the_best.knock_knock.databinding.ActivityParentMyBabyBinding
import com.all_the_best.knock_knock.databinding.ItemParentMyBabyBinding
import com.all_the_best.knock_knock.parent.mypage.adapter.ParentMyPageRcvAdapter
import com.all_the_best.knock_knock.parent.mypage.viewmodel.ParentMyPageViewModel
import com.all_the_best.knock_knock.util.StatusBarUtil
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage

class ParentMyBabyActivity : AppCompatActivity() {
    private lateinit var binding: ActivityParentMyBabyBinding
    private val parentMyBabyViewModel: ParentMyPageViewModel by viewModels()
    private var firebaseStorage: FirebaseStorage = FirebaseStorage.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(0, 0)
        StatusBarUtil.setStatusBar(this, resources.getColor(R.color.blue_status_bar, null))
        binding = DataBindingUtil.setContentView(this, R.layout.activity_parent_my_baby)
        parentMyBabyViewModel.getProfileImgFromStorage()
        parentMyBabyViewModel.getDefaultUri()
        setParentMyBabyRcvAdapter()
        setOnClickListenerForBtnGoBack()
        setParentMyBabyObserve()
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
            val selectedImage: Uri? = data.data
            uploadImgUri(selectedImage!!, requestCode)
            parentMyBabyViewModel.tempParentMyPageBabyList[requestCode].uri =
                selectedImage.toString()
            binding.parentMyBabyRcv.adapter?.notifyDataSetChanged()
        }
    }

    private fun uploadImgUri(file: Uri, listNum: Int) {
        // val file = Uri.fromFile(file)
        val database: FirebaseDatabase = FirebaseDatabase.getInstance()
        // 데이터베이스의 인스턴스를 가져온다고 생각(즉, Root를 가져온다고 이해하면 쉬움)
        val databaseReference: DatabaseReference = database.reference
        val parentId = "부모1"
        val childName = "아이1"
        databaseReference.child(parentId).child(parentId + "의 child " + childName)
            .child("imageUri($listNum)")
            .setValue(file.toString())


        firebaseStorage.reference.child("imageFile").child("imageUri($listNum)")
            .putFile(file).addOnCompleteListener {
                if (it.isSuccessful) {
                    Log.d("storage", "upload success")
                }
            }
    }

}