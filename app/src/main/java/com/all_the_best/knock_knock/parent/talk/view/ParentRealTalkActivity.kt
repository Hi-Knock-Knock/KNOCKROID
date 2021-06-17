package com.all_the_best.knock_knock.parent.talk.view

import android.Manifest
import android.content.pm.PackageManager
import android.media.AudioRecord
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.PagerSnapHelper
import com.all_the_best.knock_knock.R
import com.all_the_best.knock_knock.databinding.ActivityParentRealTalkBinding
import com.all_the_best.knock_knock.parent.talk.adapter.ParentTalkAcceptTipRcvAdapter
import com.all_the_best.knock_knock.parent.talk.model.ParentTalkAcceptTip
import com.all_the_best.knock_knock.util.StatusBarUtil
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.io.BufferedOutputStream
import java.io.DataOutputStream
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*

class ParentRealTalkActivity : AppCompatActivity() {
    private lateinit var binding: ActivityParentRealTalkBinding

    private var firebaseStorage: FirebaseStorage = FirebaseStorage.getInstance()
    private lateinit var pathReference: StorageReference
    private lateinit var fileName: String
    private lateinit var audioUri: Uri
    private var recordNum: Int = 1
    private var getDataNum: Int = 1

    private val database: FirebaseDatabase = FirebaseDatabase.getInstance()
    private val databaseReference: DatabaseReference = database.reference
    private val parentId = "부모1"
    private val childName = "아이1"
    private var isClickPause = false
    val player = MediaPlayer()
    var isRecording: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(0, 0)
        StatusBarUtil.setStatusBar(
            this,
            resources.getColor(R.color.blue_status_bar, null)
        )
        binding = DataBindingUtil.setContentView(this, R.layout.activity_parent_real_talk)
        binding.txtSubmit = "전송하기"
        setChildProfileImage()
        setSelectedFeelingAndPerson()
        setChildRecordPlayClick()
        setChildRecordPauseClick()
        setTipRcvAdapter()
        setSubmitClick()
        setRecordBtnClick()
        getToday()
        finishActivityAfterFinishTalk()
        setLayout()
        initPermissions()
    }

    private fun initPermissions() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.RECORD_AUDIO
            ) !=
            PackageManager.PERMISSION_GRANTED
        ) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this,
                    Manifest.permission.RECORD_AUDIO
                )
            ) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.RECORD_AUDIO), 1
                )
            } else {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.RECORD_AUDIO), 1
                )
            }
        }
    }

    private fun setSelectedFeelingAndPerson() {
        val selectedFeeling: DatabaseReference =
            databaseReference.child(parentId).child(parentId + "의 child " + childName)
                .child("childFeel")
        val selectedPerson: DatabaseReference =
            databaseReference.child(parentId).child(parentId + "의 child " + childName)
                .child("childPerson")
        selectedFeeling.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                binding.txtFeeling = snapshot.value as String
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
        selectedPerson.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                binding.txtPerson = snapshot.value as String
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }

    private fun setFinishRecordParentAtFirebase(isDone: Boolean) {
        databaseReference.child(parentId).child(parentId + "의 child " + childName)
            .child("finishRecordParent")
            .setValue(isDone)
    }

    private fun finishActivityAfterFinishTalk() {
        val myValue: DatabaseReference =
            databaseReference.child(parentId).child(parentId + "의 child " + childName)
                .child("startTalkChild")
        myValue.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (!(snapshot.value as Boolean)) {
                    finish()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }

    private fun setLayout() {
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
                        acceptTalkBtnChildRecordPlay.visibility = View.VISIBLE
                        acceptTalkChildSeekBar.visibility = View.VISIBLE
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
        val recordValue: DatabaseReference =
            databaseReference.child(parentId).child(parentId + "의 child " + childName)
                .child("finishRecordAfterFirst")
        recordValue.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.value as Boolean) {
                    binding.apply {
                        acceptTalkBtnChildRecordPlay.visibility = View.VISIBLE
                        acceptTalkChildSeekBar.visibility = View.VISIBLE
                    }
                } else {
                    binding.apply {
                        acceptTalkBtnChildRecordPlay.visibility = View.INVISIBLE
                        acceptTalkChildSeekBar.visibility = View.INVISIBLE
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

    private fun getTalkTipList() =
        listOf(
            ParentTalkAcceptTip(1, getString(R.string.talk_tip_1)),
            ParentTalkAcceptTip(2, getString(R.string.talk_tip_2)),
            ParentTalkAcceptTip(3, getString(R.string.talk_tip_3)),
            ParentTalkAcceptTip(4, getString(R.string.talk_tip_4)),
            ParentTalkAcceptTip(5, getString(R.string.talk_tip_5)),
            ParentTalkAcceptTip(6, getString(R.string.talk_tip_6)),
            ParentTalkAcceptTip(7, getString(R.string.talk_tip_7))
        )

    private fun setTipRcvAdapter() {
        val tipRcvAdapter = ParentTalkAcceptTipRcvAdapter()
        tipRcvAdapter.submitList(getTalkTipList())
        binding.acceptTalkRcv.adapter = tipRcvAdapter
        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(binding.acceptTalkRcv)
    }

    private fun setChildRecordPlayClick() {
        binding.acceptTalkBtnChildRecordPlay.setOnClickListener {
            binding.acceptTalkBtnChildRecordPlay.visibility = View.INVISIBLE
            binding.acceptTalkBtnChildRecordPause.visibility = View.VISIBLE
            if (!isClickPause) {
                startChildRecord()
            } else {
                player.start()
                setSeekBar()
            }
        }
    }

    private fun setChildRecordPauseClick() {
        binding.acceptTalkBtnChildRecordPause.setOnClickListener {
            isClickPause = true
            binding.acceptTalkBtnChildRecordPause.visibility = View.INVISIBLE
            binding.acceptTalkBtnChildRecordPlay.visibility = View.VISIBLE
            player.pause()
        }
    }

    private fun setSeekBar(){
        Thread {
            while (player.isPlaying) {  // 음악이 실행중일때 계속 돌아가게 함
                try {
                    Thread.sleep(20)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
                // 현재 재생중인 위치를 가져와 시크바에 적용
                binding.acceptTalkChildSeekBar.progress = player.currentPosition.toFloat()
            }
        }.start()
    }

    private fun startChildRecord() {
        pathReference =
            firebaseStorage.reference.child(fileName).child("child").child("child($getDataNum).mp4")

        // createTempFile : 임시파일 생성 (so, 사용이 끝나면 삭제해줘야함.)
        // deleteOnExit을 사용해서 파일 삭제 -> 특징 : 파일을 바로 삭제하는 것이 아니라, JVM이 종료될 때 자동으로 저장된 파일을 삭제함.
        val localFile = File.createTempFile("temp_download", "mp4")
        localFile.deleteOnExit()

        pathReference.getFile(localFile).addOnSuccessListener {
            // Local temp file has been created
            player.reset()
            player.setDataSource(localFile.path)
            player.prepare()
            player.start()
            binding.acceptTalkChildSeekBar.max = player.duration.toFloat()
            setSeekBar()
            Log.d("getAudio", "success")
            getDataNum++
            player.setOnCompletionListener {
                isClickPause = false
                binding.acceptTalkBtnChildRecordPause.visibility=View.INVISIBLE
                binding.acceptTalkBtnChildRecordPlay.visibility=View.VISIBLE
                Log.d("finishRecord",isClickPause.toString())
            }
        }.addOnFailureListener {
            // Handle any errors
            Log.d("getAudio", "fail")
        }
    }

    private fun setRecordBtnClick() {
        binding.acceptTalkBtnRecord.setOnClickListener {
            Thread {
                startRecording()
            }.start()
            setFinishRecordParentAtFirebase(false)
            binding.acceptTalkBtnRecord.visibility = View.INVISIBLE
            binding.acceptTalkBtnRecordStop.visibility = View.VISIBLE
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
        isRecording = false
    }

    @Suppress("DEPRECATION")
    private fun startRecording() {
        isRecording = true

        val myFile = File(applicationContext.getExternalFilesDir(null)!!.absolutePath, "test.pom")

        myFile.createNewFile()

        val outputStream = FileOutputStream(myFile)
        val bufferedOutputStream = BufferedOutputStream(outputStream)
        val dataOutputStream = DataOutputStream(bufferedOutputStream)
        val minBufferSize = AudioRecord.getMinBufferSize(11025, 2, 2)

        val audioData = ShortArray(minBufferSize)

        val audioRecord = AudioRecord(1, 11025, 2, 2, minBufferSize)
        audioRecord.startRecording()

        while (isRecording) {
            val numberOfShort = audioRecord.read(audioData, 0, minBufferSize)

            for (i in 0 until numberOfShort) {
                dataOutputStream.writeShort(audioData[i].toInt())
            }
        }

        if (!isRecording) {
            audioRecord.stop()
            dataOutputStream.close()
            audioUri = Uri.fromFile(File(myFile.path))
        }
    }

    private fun uploadAudioUri(file: Uri) {
        firebaseStorage.reference.child(fileName).child("parent").child("parent($recordNum).mp4")
            .putFile(file).addOnCompleteListener {
                if (it.isSuccessful) {
                    Log.d("storage", "upload success")
                    setFinishRecordParentAtFirebase(true)
                }
            }
        recordNum++
    }

    private fun setChildProfileImage(){
        val myValue: DatabaseReference =
            databaseReference.child(parentId).child(parentId + "의 child " + childName)
                .child("imageUri(0)")
        myValue.get().addOnSuccessListener {
            binding.childProfileUri = it.value.toString()
            Log.d("firebase", it.value.toString())
        }.addOnFailureListener {
            Log.d("firebase", "Error getting data", it)
        }
    }

}