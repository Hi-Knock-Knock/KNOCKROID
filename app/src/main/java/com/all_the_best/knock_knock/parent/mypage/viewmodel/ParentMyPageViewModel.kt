package com.all_the_best.knock_knock.parent.mypage.viewmodel

import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.all_the_best.knock_knock.parent.mypage.model.ParentMyPageBaby
import com.bumptech.glide.Glide
import com.google.firebase.storage.FirebaseStorage
import java.io.File

class ParentMyPageViewModel : ViewModel() {
    private val _uri0 = MutableLiveData<Uri?>()
    val uri0: LiveData<Uri?>
        get() = _uri0
    private val _uri1 = MutableLiveData<Uri?>()
    val uri1: LiveData<Uri?>
        get() = _uri1
    private val _uri2 = MutableLiveData<Uri?>()
    val uri2: LiveData<Uri?>
        get() = _uri2
    private val _uri3 = MutableLiveData<Uri?>()
    val uri3: LiveData<Uri?>
        get() = _uri3

    var tempParentMyPageBabyList: List<ParentMyPageBaby> =
        listOf(
            ParentMyPageBaby(uri0.value,"연주", "남", "2018.06.13", "쥬쥬"),
            ParentMyPageBaby(uri1.value,"지수", "여", "2018.07.13", "슈슈"),
            ParentMyPageBaby(uri2.value,"지호", "남", "2018.08.13", "죠죠"),
            ParentMyPageBaby(uri3.value,"윤정", "여", "2018.09.13", "졍졍")
        )

    private val _parentMyPageBabyList = MutableLiveData<MutableList<ParentMyPageBaby>>()
    val parentMyPageBabyList: LiveData<MutableList<ParentMyPageBaby>>
        get() = _parentMyPageBabyList

    fun setParentMyPageBabyList() {
        _parentMyPageBabyList.value = tempParentMyPageBabyList.toMutableList()
    }

    fun getProfileImgFromStorage() {
        getImgFromStorage(0)
        getImgFromStorage(1)
        getImgFromStorage(2)
        getImgFromStorage(3)
    }
    private fun getImgFromStorage(listNum:Int){
        val firebaseStorage: FirebaseStorage = FirebaseStorage.getInstance()
        val pathReference =
            firebaseStorage.reference.child("imageFile").child("imageUri($listNum).png")

        val localFile = File.createTempFile("temp_download", "png")
        localFile.deleteOnExit()

        pathReference.downloadUrl.addOnSuccessListener {
            // Local temp file has been created
            Log.d("tag_getImg_viewmodel", it.toString())
            tempParentMyPageBabyList[listNum].uri = it
            Log.d("tag_getImg_viewmodel_", tempParentMyPageBabyList[listNum].uri.toString())
            Log.d("tag", "success")
            setParentMyPageBabyList()
        }.addOnFailureListener {
            // Handle any errors
            Log.d("tag", "fail")
        }
    }
}