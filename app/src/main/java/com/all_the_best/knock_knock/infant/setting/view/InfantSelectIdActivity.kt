package com.all_the_best.knock_knock.infant.setting.view

import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.all_the_best.knock_knock.R
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.storage.FileDownloadTask
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_infant_select_id.*
import java.io.File
import java.io.IOException


class InfantSelectIdActivity : AppCompatActivity() {

    private var chSelect: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_infant_select_id)

        chSelect = intent.getIntExtra("chSelect",0)
        //상태바 색상 지정
        window.statusBarColor = Color.parseColor("#74DAFF")

        getImgFromStorage(0,1)
        val intent = Intent(this, InfantSelectCharacterActivity::class.java)
        button1.setOnClickListener{
            intent.putExtra("chSelect", chSelect)
            startActivity(intent)
            finish()
            overridePendingTransition(0, 0)
        }
    }

    override fun onBackPressed(){
        Log.d("backpress","막음")
    }

    private fun getImgFromStorage(listNum: Int,listNum2: Int){
        var storage = FirebaseStorage.getInstance()
        var storageRef =
            storage.getReferenceFromUrl("gs://knockknock-29f42.appspot.com").child("imageFile").child("imageUri($listNum).png")
        var storageRef2 =
            storage.getReferenceFromUrl("gs://knockknock-29f42.appspot.com").child("imageFile").child("imageUri($listNum2).png")
        try {
            val localFile: File = File.createTempFile("images", "png")
            //val localFile2: File = File.createTempFile("images", "png")
            storageRef.getFile(localFile)
                .addOnSuccessListener(OnSuccessListener<FileDownloadTask.TaskSnapshot?> {
                    val bitmap = BitmapFactory.decodeFile(localFile.absolutePath)
                    id_image1.setImageBitmap(bitmap)
                }).addOnFailureListener(OnFailureListener { })
            storageRef2.getFile(localFile)
                .addOnSuccessListener(OnSuccessListener<FileDownloadTask.TaskSnapshot?> {
                    val bitmap = BitmapFactory.decodeFile(localFile.absolutePath)
                    id_image2.setImageBitmap(bitmap)
                }).addOnFailureListener(OnFailureListener { })
        } catch (e: IOException) {
        }
    }
}

