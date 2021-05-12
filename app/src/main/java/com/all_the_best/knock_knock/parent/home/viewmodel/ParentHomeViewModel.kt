package com.all_the_best.knock_knock.parent.home.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.all_the_best.knock_knock.parent.home.model.ParentHomeRecord
import com.google.firebase.database.*

class ParentHomeViewModel : ViewModel() {
    private var _goFaqFlag = MutableLiveData(false)
    val goFaqFlag: LiveData<Boolean>
        get() = _goFaqFlag

    fun setGoFaqFlag(flag: Boolean){
        _goFaqFlag.value = flag
    }

    private var tempParentHomeRecordList: List<ParentHomeRecord> =
        listOf(
            ParentHomeRecord("윤하", null, "답변 전", "답변 전"),
            ParentHomeRecord("윤지", null, "답변 전", "답변 전")
        )
    private val _parentHomeRecordList =
        MutableLiveData<MutableList<ParentHomeRecord>>()
    val parentHomeRecordList: LiveData<MutableList<ParentHomeRecord>>
        get() = _parentHomeRecordList

    fun setParentRecordList() {
        _parentHomeRecordList.value = tempParentHomeRecordList.toMutableList()
    }

    fun getDefaultUri() {
        for (i in 0..1) {
            getDefaultUri(i)
        }
    }

    fun getProfileImgFromStorage() {
        getImgFromStorage(0)
        getImgFromStorage(1)
    }

    private fun getDefaultUri(listNum: Int) {
        val database: FirebaseDatabase = FirebaseDatabase.getInstance()
        // 데이터베이스의 인스턴스를 가져온다고 생각(즉, Root를 가져온다고 이해하면 쉬움)
        val databaseReference: DatabaseReference = database.reference
        val parentId = "부모1"
        val childName = "아이1"
        val myValue: DatabaseReference =
            databaseReference.child(parentId).child(parentId + "의 child " + childName)
                .child("imageUri($listNum)")
        myValue.get().addOnSuccessListener {
            tempParentHomeRecordList[listNum].uri = it.value.toString()
            if (listNum == 1) {
                setParentRecordList()
                Log.d("tag_img_viewmodel_get", parentHomeRecordList.value.toString())
            }
        }.addOnFailureListener {
            Log.e("firebase", "Error getting data", it)
        }
    }

    private fun getImgFromStorage(listNum: Int) {
        val database: FirebaseDatabase = FirebaseDatabase.getInstance()
        // 데이터베이스의 인스턴스를 가져온다고 생각(즉, Root를 가져온다고 이해하면 쉬움)
        val databaseReference: DatabaseReference = database.reference
        val parentId = "부모1"
        val childName = "아이1"
        val myValue: DatabaseReference =
            databaseReference.child(parentId).child(parentId + "의 child " + childName)
                .child("imageUri($listNum)")
        myValue.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                tempParentHomeRecordList[listNum].uri = snapshot.value.toString()
                Log.d("tag_img_viewmodel_change", parentHomeRecordList.value.toString())
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }

    fun getAnswerFromFirebase(){
        val database: FirebaseDatabase = FirebaseDatabase.getInstance()
        // 데이터베이스의 인스턴스를 가져온다고 생각(즉, Root를 가져온다고 이해하면 쉬움)
        val databaseReference: DatabaseReference = database.reference
        val parentId = "부모1"
        val childName = "아이1"
        val selectedFeeling: DatabaseReference =
            databaseReference.child(parentId).child(parentId + "의 child " + childName)
                .child("childFeel")
        val selectedPerson: DatabaseReference =
            databaseReference.child(parentId).child(parentId + "의 child " + childName)
                .child("childPerson")
        selectedFeeling.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                tempParentHomeRecordList[0].answer1 = snapshot.value as String
            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
        selectedPerson.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                tempParentHomeRecordList[0].answer2 = snapshot.value as String
            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
}