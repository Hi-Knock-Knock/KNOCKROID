package com.all_the_best.knock_knock.parent.talk.view

import android.content.ContentValues
import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.media.MediaRecorder
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.PagerSnapHelper
import com.all_the_best.knock_knock.R
import com.all_the_best.knock_knock.databinding.ActivityParentRealTalkBinding
import com.all_the_best.knock_knock.parent.talk.adapter.ParentTalkAcceptTipRcvAdapter
import com.all_the_best.knock_knock.parent.talk.viewmodel.ParentTalkViewModel
import com.all_the_best.knock_knock.util.StatusBarUtil
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class ParentRealTalkActivity : AppCompatActivity() {
    private lateinit var binding: ActivityParentRealTalkBinding
    private val parentTalkViewModel: ParentTalkViewModel by viewModels()

    private var firebaseStorage: FirebaseStorage = FirebaseStorage.getInstance()
    private lateinit var pathReference: StorageReference
    private lateinit var mediaRecorder: MediaRecorder
    private var state: Boolean = false
    private lateinit var fileName: String
    private lateinit var audioUri: Uri
    private var recordNum: Int = 1
    private var getDataNum: Int = 1

    private val database: FirebaseDatabase = FirebaseDatabase.getInstance()

    // 데이터베이스의 인스턴스를 가져온다고 생각(즉, Root를 가져온다고 이해하면 쉬움)
    private val databaseReference: DatabaseReference = database.reference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        StatusBarUtil.setStatusBar(
            this,
            resources.getColor(R.color.blue_status_bar, null)
        )
        binding = DataBindingUtil.setContentView(this, R.layout.activity_parent_real_talk)
        binding.txtSubmit = "전송하기"
        setGetChildRecordClick()
        setTipRcvAdapter()
        setSubmitClick()
        setRecordBtnClick()
        getToday()
        setLayout()
    }

    private fun setLayout() {
        val parentId = "부모1"
        val childName = "아이1"
        val myValue: DatabaseReference =
            databaseReference.child(parentId).child(parentId + "의 child " + childName)
                .child("finishRecordChild")
        myValue.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.value as Boolean) {
                    binding.apply {
                        acceptTalkConstraintQuestion.visibility = View.VISIBLE
                        acceptTalkConstraintRecord.visibility = View.VISIBLE
                        acceptTalkConstraintLoading.visibility = View.GONE
                    }
                } else {
                    binding.apply {
                        acceptTalkConstraintQuestion.visibility = View.GONE
                        acceptTalkConstraintRecord.visibility = View.GONE
                        acceptTalkConstraintLoading.visibility = View.VISIBLE
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }

    private fun getToday() {
        val currentTime: Date = Calendar.getInstance().getTime()
        val simpleDate = SimpleDateFormat("yyyy.MM.dd")
        val date = simpleDate.format(currentTime)
        binding.txtDate = date.toString()

        val fileNameFormat = SimpleDateFormat("yyyy-MM-dd")
        fileName = fileNameFormat.format(currentTime).toString()
    }

    private fun setTipRcvAdapter() {
        val tipRcvAdapter = ParentTalkAcceptTipRcvAdapter()
        tipRcvAdapter.submitList(parentTalkViewModel.tipList)
        binding.acceptTalkRcv.adapter = tipRcvAdapter
        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(binding.acceptTalkRcv)
    }

    private fun setGetChildRecordClick() {
        binding.acceptTalkBtnChildRecordPlay.setOnClickListener {
            getDataFromStorage()
        }
    }

    private fun getDataFromStorage() {
        pathReference =
            firebaseStorage.reference.child(fileName).child("child").child("child($getDataNum).mp4")

        // createTempFile : 임시파일 생성 (so, 사용이 끝나면 삭제해줘야함.)
        // deleteOnExit을 사용해서 파일 삭제 -> 특징 : 파일을 바로 삭제하는 것이 아니라, JVM이 종료될 때 자동으로 저장된 파일을 삭제함.
        val localFile = File.createTempFile("temp_download", "mp4")
        localFile.deleteOnExit()

        pathReference.getFile(localFile).addOnSuccessListener {
            // Local temp file has been created
            val player = MediaPlayer()
            player.setDataSource(localFile.path)
            player.prepare()
            player.start()
            Log.d("getAudio", "success")
            getDataNum++
        }.addOnFailureListener {
            // Handle any errors
            Log.d("getAudio", "fail")
        }
    }

    private fun setRecordBtnClick() {
        binding.acceptTalkBtnRecord.setOnClickListener {
            binding.acceptTalkBtnRecord.visibility = View.INVISIBLE
            binding.acceptTalkBtnRecordStop.visibility = View.VISIBLE
            if (ContextCompat.checkSelfPermission(
                    this,
                    android.Manifest.permission.RECORD_AUDIO
                ) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(
                    this,
                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                // Permission is not granted
                val permissions = arrayOf(
                    android.Manifest.permission.RECORD_AUDIO,
                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    android.Manifest.permission.READ_EXTERNAL_STORAGE
                )
                ActivityCompat.requestPermissions(this, permissions, 0)
            } else {
                Log.d("record", "start")
                startRecording()
            }
            setRecordStopBtnClick()
        }
    }

    private fun setRecordStopBtnClick() {
        binding.acceptTalkBtnRecordStop.setOnClickListener {
            stopRecording()
            binding.acceptTalkBtnRecordStop.visibility = View.INVISIBLE
            binding.acceptTalkBtnRecord.visibility = View.VISIBLE
            binding.acceptTalkSubmit.setTextColor(
                ContextCompat.getColor(
                    this,
                    R.color.talk_submit_blue
                )
            )
        }
    }

    private fun setSubmitClick() {
        binding.acceptTalkSubmit.setOnClickListener {
            uploadAudioUri(audioUri)
            binding.acceptTalkSubmit.setTextColor(
                ContextCompat.getColor(
                    this,
                    R.color.talk_submit_gray
                )
            )
        }
    }

    private fun stopRecording() {
        if (state) {
            mediaRecorder?.stop()
            mediaRecorder?.reset()
            mediaRecorder?.release()
            state = false
            Toast.makeText(this, "중지 되었습니다.", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "레코딩 상태가 아닙니다.", Toast.LENGTH_SHORT).show()
        }
    }

    @Suppress("DEPRECATION")
    private fun startRecording() {
        // config and create MediaRecorder Object
        val values = ContentValues(10)
        values.put(MediaStore.MediaColumns.TITLE, "Recorded")
        values.put(MediaStore.Audio.Media.DISPLAY_NAME, fileName)
        values.put(MediaStore.Audio.Media.IS_RINGTONE, 1)
        values.put(MediaStore.Audio.Media.IS_MUSIC, 1)
        values.put(MediaStore.MediaColumns.DATE_ADDED, System.currentTimeMillis() / 1000)
        values.put(MediaStore.MediaColumns.MIME_TYPE, "audio/mp4")
        values.put(MediaStore.Audio.Media.DATA, fileName)
        values.put(MediaStore.Audio.Media.RELATIVE_PATH, "Music/Recordings/")

        audioUri = contentResolver.insert(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, values)!!
        val file = audioUri?.let { getContentResolver().openFileDescriptor(it, "w") }

        mediaRecorder = MediaRecorder()
        mediaRecorder?.setAudioSource((MediaRecorder.AudioSource.MIC))
        mediaRecorder?.setOutputFormat((MediaRecorder.OutputFormat.MPEG_4))
        mediaRecorder?.setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
        if (file != null) {
            mediaRecorder?.setOutputFile(file.getFileDescriptor())
        }

        try {
            mediaRecorder?.prepare()
            mediaRecorder?.start()
            state = true
            Toast.makeText(this, "레코딩 시작되었습니다.", Toast.LENGTH_SHORT).show()
        } catch (e: IllegalStateException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun uploadAudioUri(file: Uri) {
        firebaseStorage.reference.child(fileName).child("parent").child("parent($recordNum).mp4")
            .putFile(file).addOnCompleteListener {
                if (it.isSuccessful) {
                    Log.d("storage", "upload success")
                }
            }
        recordNum++
    }

}