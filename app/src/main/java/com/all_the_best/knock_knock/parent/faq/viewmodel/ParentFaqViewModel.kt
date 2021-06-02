package com.all_the_best.knock_knock.parent.faq.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.all_the_best.knock_knock.R
import com.all_the_best.knock_knock.parent.faq.model.ParentFaqData
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class ParentFaqViewModel : ViewModel() {
    private val _faqList = MutableLiveData<MutableList<ParentFaqData>>()
    val faqList: LiveData<MutableList<ParentFaqData>>
        get() = _faqList

    private val _myScrapList = MutableLiveData<MutableList<ParentFaqData>>()
    val myScrapList: LiveData<MutableList<ParentFaqData>>
        get() = _myScrapList

    private val _threeTitle = MutableLiveData(mutableListOf("", "", ""))
    val threeTitle: LiveData<MutableList<String>>
        get() = _threeTitle

    var firstTitle = ParentFaqData(0, R.string.talk_question_0, 0, false)

    var secondTitle = ParentFaqData(0, R.string.talk_question_0, 0, false)

    var thirdTitle = ParentFaqData(0, R.string.talk_question_0, 0, false)

    var count = 0

    var tempFaqList: List<ParentFaqData> = listOf(
        ParentFaqData(0, R.string.faq_title_1, R.string.faq_data_content_1, false),
        ParentFaqData(1, R.string.faq_title_2, R.string.faq_data_content_2, false),
        ParentFaqData(2, R.string.faq_title_3, R.string.faq_data_content_3, false),
        ParentFaqData(3, R.string.faq_title_4, R.string.faq_data_content_4, false),
        ParentFaqData(4, R.string.faq_title_5, R.string.faq_data_content_5, false),
        ParentFaqData(5, R.string.faq_title_6, R.string.faq_data_content_6, false),
        ParentFaqData(6, R.string.faq_title_7, R.string.faq_data_content_7, false),
        ParentFaqData(7, R.string.faq_title_8, R.string.faq_data_content_8, false),
        ParentFaqData(8, R.string.faq_title_9, R.string.faq_data_content_9, false),
        ParentFaqData(9, R.string.faq_title_10, R.string.faq_data_content_10, false),
        ParentFaqData(10, R.string.faq_title_11, R.string.faq_data_content_11, false),
        ParentFaqData(11, R.string.faq_title_12, R.string.faq_data_content_12, false),
        ParentFaqData(12, R.string.faq_title_13, R.string.faq_data_content_13, false)
    )

    var faqDetailList: List<ParentFaqData> = listOf(
        ParentFaqData(0, R.string.faq_detail_title_1, R.string.faq_data_content_1, false),
        ParentFaqData(1, R.string.faq_detail_title_2, R.string.faq_data_content_2, false),
        ParentFaqData(2, R.string.faq_detail_title_3, R.string.faq_data_content_3, false),
        ParentFaqData(3, R.string.faq_detail_title_4, R.string.faq_data_content_4, false),
        ParentFaqData(4, R.string.faq_detail_title_5, R.string.faq_data_content_5, false),
        ParentFaqData(5, R.string.faq_detail_title_6, R.string.faq_data_content_6, false),
        ParentFaqData(6, R.string.faq_detail_title_7, R.string.faq_data_content_7, false),
        ParentFaqData(7, R.string.faq_detail_title_8, R.string.faq_data_content_8, false),
        ParentFaqData(8, R.string.faq_detail_title_9, R.string.faq_data_content_9, false),
        ParentFaqData(9, R.string.faq_detail_title_10, R.string.faq_data_content_10, false),
        ParentFaqData(10, R.string.faq_detail_title_11, R.string.faq_data_content_11, false),
        ParentFaqData(11, R.string.faq_detail_title_12, R.string.faq_data_content_12, false),
        ParentFaqData(12, R.string.faq_detail_title_13, R.string.faq_data_content_13, false)
    )


    fun setFaqList() {
        _faqList.value = tempFaqList.toMutableList()
    }

    fun setScrapList() {
        var tempScrapList = mutableListOf<ParentFaqData>()
        val database: FirebaseDatabase = FirebaseDatabase.getInstance()
        val databaseReference: DatabaseReference = database.reference
        for (index in 0..12) {
            val myValue = databaseReference.child("부모1").child("faqScrap")
                .child("index_$index")
            myValue.get().addOnSuccessListener {
                if (it.value as Boolean) {
                    tempScrapList.add(faqDetailList[index])
                    if (count == 0) {
                        firstTitle = faqDetailList[index]
                        count++
                    } else if (count == 1) {
                        secondTitle = faqDetailList[index]
                        count++
                    } else if (count == 2) {
                        thirdTitle = faqDetailList[index]
                        count++
                    }
                    _myScrapList.value = tempScrapList.toMutableList()
                }
            }.addOnFailureListener {
                Log.e("firebase", "Error getting data", it)
            }
        }
    }

    fun setIsScrapped() {
        val database: FirebaseDatabase = FirebaseDatabase.getInstance()
        val databaseReference: DatabaseReference = database.reference
        for (index in 0..12) {
            val myValue = databaseReference.child("부모1").child("faqScrap")
                .child("index_$index")
            myValue.get().addOnSuccessListener {
                tempFaqList[index].isScrapped = it.value as Boolean
                if(index ==12)_faqList.value = tempFaqList.toMutableList()
            }.addOnFailureListener {
                Log.e("firebase", "Error getting data", it)
            }
        }

    }
}