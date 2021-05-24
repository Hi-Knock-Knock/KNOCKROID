package com.all_the_best.knock_knock.infant.talk.view

import android.app.Activity
import android.app.Dialog
import android.content.ComponentName
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.media.*
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.MediaStore
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.airbnb.lottie.LottieDrawable
import com.all_the_best.knock_knock.R
import com.all_the_best.knock_knock.infant.home.view.InfantHomeActivity
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_infant_talk_start.*
import java.io.*
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

@Suppress("DEPRECATION")
class InfantTalkStartActivity : AppCompatActivity() {

    // 효과음
    var soundPool:SoundPool?=null
    private var musicPlay:Int=0

    private var bgSelect: Int = 1
    private var chSelect: Int = 0
    private var cookieCount: Int = 5
    private var giftSelect:Int=0
    private var lottieSelect:Int=0

    //TTS 관련 변수들
    private var mTts: TextToSpeech? = null
    private var mLocale = Locale.KOREA
    private var mPitch = 1.5f
    private var mRate = 0.8f
    private var mQueue = TextToSpeech.QUEUE_FLUSH

    //private var loading by Delegates.notNull<Int>()
    private lateinit var fileName: String
    private lateinit var mediaRecorder: MediaRecorder
    private lateinit var audioUri:Uri
    private var state = false
    private val firebaseStorage: FirebaseStorage = FirebaseStorage.getInstance()
    private var recordNum: Int = 1
    private var getDataNum: Int = 1

    var finish:Boolean =false

    private val database: FirebaseDatabase = FirebaseDatabase.getInstance()
    private val databaseReference: DatabaseReference = database.reference
    val parentId = "부모1"
    val childName = "아이1"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_infant_talk_start)
        bgSelect = intent.getIntExtra("bgSelect",1)
        chSelect = intent.getIntExtra("chSelect",0)
        cookieCount = intent.getIntExtra("cookieCount",5)
        giftSelect = intent.getIntExtra("giftSelect",0)
        lottieSelect = intent.getIntExtra("lottieSelect",0)
        musicPlay = intent.getIntExtra("musicPlay",0)

        setSelectCharacter()
        setPlayParentRecord()
        getToday()
        setOnBtnRecordClick()
        setOnLottieStart()
        init()
        setPlayDenyParent()

        window.statusBarColor = Color.parseColor("#FCC364")

        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ISO_LOCAL_TIME

        when(current.format(formatter)){
            in "08:00:000".."13:59:999" -> {
                infant_talk_start.setBackgroundResource(R.drawable.img_infant_room_morning_bg)
            }
            in "14:00:000".."19:59:999" -> {
                infant_talk_start.setBackgroundResource(R.drawable.img_infant_room_after_bg)
            }
            in "20:00:00".."23:59:999" -> {
                infant_talk_start.setBackgroundResource(R.drawable.img_infant_room_night_bg)
            }
            !in "08:00:00".."23:59:999" -> {
                infant_talk_start.setBackgroundResource(R.drawable.img_infant_room_night_bg)
            }
        }


        infant_icon_out.setOnClickListener{
            setGoOut()
        }
    }

    private fun setGoOut(){
        val intent1 = Intent(this, InfantHomeActivity::class.java)
        setDefaultVariableAtFirebase()
        setMotionInit()
        // 쿠키 받는 팝업
        val cookiePopUp = Dialog(this)
        cookiePopUp?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        cookiePopUp.setContentView(R.layout.activity_infant_get_cookie_popup)
        cookiePopUp.show()
        cookieCount += 1
        intent1.putExtra("bgSelect", bgSelect)
        intent1.putExtra("chSelect",chSelect)
        intent1.putExtra("cookieCount",cookieCount)
        intent1.putExtra("giftSelect",giftSelect)
        intent1.putExtra("lottieSelect",lottieSelect)
        musicPlay = 0
        intent1.putExtra("musicPlay",musicPlay)
        Handler(Looper.getMainLooper()).postDelayed ({
            startActivity(intent1)
            cookiePopUp.dismiss()
            finish()
        }, 2000)
        overridePendingTransition(0, 0)
    }

    override fun onBackPressed(){
        Log.d("backpress","막음")
    }

    private fun setSelectCharacter(){
        when(chSelect){
            0 -> talk_start_char_dam.setAnimation("dami_ear.json")
            1 -> talk_start_char_dam.setAnimation("knock_ear.json")
            2 -> talk_start_char_dam.setAnimation("timi_ear.json")
        }
    }

    private fun setSelectTalkCharacter(){
        when(chSelect){
            0 -> talk_start_char_dam.setAnimation("dami_talk.json")
            1 -> talk_start_char_dam.setAnimation("knock_talk.json")
            2 -> talk_start_char_dam.setAnimation("timi_talk.json")
        }
    }

    private fun setMotionInit(){
        when(lottieSelect){
            in 1..6 ->  lottieSelect = 0
        }
    }

    private fun setOnLottieStart(){
        talk_start_char_dam.repeatMode = LottieDrawable.REVERSE
        talk_start_char_dam.repeatCount = LottieDrawable.INFINITE
        talk_start_char_dam.playAnimation()
    }

    private fun getToday() {
        val currentTime: Date = Calendar.getInstance().getTime()
        val simpleDate = SimpleDateFormat("yyyy-MM-dd")
        val date = simpleDate.format(currentTime)
        fileName = date.toString()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && data != null) {
            when (requestCode) {
                1 -> {
                    val selectedAudio: Uri? = data.data
                    uploadAudioUri(selectedAudio!!)
                }
            }
        }
    }

    private fun setOnBtnRecordClick() {
        talk_start_char_dam.setOnClickListener {
            databaseReference.child(parentId).child(parentId + "의 child " + childName).child("finishRecordAfterFirst")
                .setValue(false)
            if (ContextCompat.checkSelfPermission(
                    this,
                    android.Manifest.permission.RECORD_AUDIO
                ) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(
                    this,
                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                //Permission is not granted
                val permissions = arrayOf(
                    android.Manifest.permission.RECORD_AUDIO,
                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    android.Manifest.permission.READ_EXTERNAL_STORAGE
                )
                ActivityCompat.requestPermissions(this, permissions, 0)
            } else {
                //Log.d("record", "start")
                this.talk_txtview.text = "다 말하면 버튼을 눌러!"
                play()
                if(play()){
                    startRecording()
                }
            }
            setOnBtnRecordStopClick()
        }
    }

    private fun setOnBtnRecordStopClick(){
        stopRecordBtn.setOnClickListener {
//            val soundId: Int = soundPool!!.load(this, R.raw.button, 1)
//            soundPool!!.play(soundId, 1.0f, 1.0f, 1, 0, 1.0f)
            talk_txtview.visibility = View.INVISIBLE
            infant_talk1.visibility = View.INVISIBLE
            when(chSelect){
                0 -> {
                    talk_start_char_dam.setAnimation("dami_think.json")
                    talk_start_char_dam.repeatMode = LottieDrawable.REVERSE
                    talk_start_char_dam.repeatCount = LottieDrawable.INFINITE
                    talk_start_char_dam.playAnimation()
                }
                1 -> {
                    talk_start_char_dam.setAnimation("knock_think.json")
                    talk_start_char_dam.repeatMode = LottieDrawable.REVERSE
                    talk_start_char_dam.repeatCount = LottieDrawable.INFINITE
                    talk_start_char_dam.playAnimation()
                }
                2 -> {
                    talk_start_char_dam.setAnimation("timi_think.json")
                    talk_start_char_dam.repeatMode = LottieDrawable.REVERSE
                    talk_start_char_dam.repeatCount = LottieDrawable.INFINITE
                    talk_start_char_dam.playAnimation()
                }
            }
            stopRecording()
            setFinishRecordChildAtFirebase()
            if (finish){
                setSelectTalkCharacter()
                setOnLottieStart()
                this.talk_txtview.text = "그렇구나! 말해줘서 고마워! 다음에 또 보자!!"
                play()
                Handler(Looper.getMainLooper()).postDelayed ({
                    setGoOut()
                }, 8500)

            }
        }
    }

    @Suppress("DEPRECATION")
    private fun startRecording() {
        //config and create MediaRecorder Object
        val values = ContentValues(10)
        values.put(MediaStore.MediaColumns.TITLE, "Recorded")
        values.put(MediaStore.Audio.Media.DISPLAY_NAME, fileName)
        values.put(MediaStore.Audio.Media.IS_RINGTONE, 1)
        values.put(MediaStore.Audio.Media.IS_MUSIC, 1)
        values.put(MediaStore.MediaColumns.DATE_ADDED, System.currentTimeMillis() / 1000)
        values.put(MediaStore.MediaColumns.MIME_TYPE, "audio/mp4")
        values.put(MediaStore.Audio.Media.DATA, fileName)
        values.put(MediaStore.Audio.Media.RELATIVE_PATH, "Music/Recordings/");

        audioUri = contentResolver.insert(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, values)!!
        val file = audioUri?.let { getContentResolver().openFileDescriptor(it, "w") };

        mediaRecorder = MediaRecorder()
        mediaRecorder?.setAudioSource((MediaRecorder.AudioSource.MIC))
        mediaRecorder?.setOutputFormat((MediaRecorder.OutputFormat.MPEG_4))
        mediaRecorder?.setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
        if (file != null) {
            mediaRecorder?.setOutputFile(file.fileDescriptor)
        }

        try {
            mediaRecorder?.prepare()
            mediaRecorder?.start()
            state = true
            Toast.makeText(this, "대화시작!", Toast.LENGTH_SHORT).show()
        } catch (e: IllegalStateException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun stopRecording() {
        if (state) {
            mediaRecorder?.stop()
            mediaRecorder?.reset()
            mediaRecorder?.release()
            state = false
            soundPool = SoundPool(1,AudioManager.STREAM_MUSIC,0)
            uploadAudioUri(audioUri)
        } else {
            Toast.makeText(this, "레코딩오류", Toast.LENGTH_SHORT).show()
        }
    }

    private fun uploadAudioUri(file: Uri) {
        firebaseStorage.reference.child(fileName).child("child").child("child($recordNum).mp4")
            .putFile(file).addOnCompleteListener {
                if (it.isSuccessful) {
                    //Log.d("storage", "upload success")
                }
            }
        recordNum++
    }

    private fun getDataFromStorage() {
        var pathReference = firebaseStorage.reference.child(fileName).child("parent").child("parent($getDataNum).mp4")

        val localFile = File(applicationContext.getExternalFilesDir(null), "test.pom")
        val myFile = File(applicationContext.getExternalFilesDir(null)!!.absolutePath, "test.pom")
        myFile.createNewFile()

        pathReference.getFile(myFile).addOnSuccessListener {
            playRecording(localFile)
            Log.d("getAudio", "success")
        }.addOnFailureListener {
            // Handle any errors
            Log.d("getAudio", "fail")
        }
    }

    private fun setFinishRecordChildAtFirebase() {
        databaseReference.child(parentId).child(parentId + "의 child " + childName).child("finishRecordChild")
            .setValue(true)
        databaseReference.child(parentId).child(parentId + "의 child " + childName).child("finishRecordAfterFirst")
            .setValue(true)
    }

    private fun setPlayParentRecord() {
        val myValue: DatabaseReference =
            databaseReference.child(parentId).child(parentId + "의 child " + childName)
                .child("finishRecordParent")
        myValue.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.value as Boolean) {
                    //stopRecordBtn.isClickable = false
                    getDataFromStorage()
                    setSelectTalkCharacter()
                    setOnLottieStart()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }

    private fun setPlayDenyParent() {
        val myValue: DatabaseReference =
            databaseReference.child(parentId).child(parentId + "의 child " + childName)
                .child("parentDenyTalk")
        myValue.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.value as Boolean) {
                    getFinishRecordChild()
                }
            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }

    private fun setDefaultVariableAtFirebase() {
        databaseReference.child(parentId).child(parentId + "의 child " + childName).child("startTalkChild")
            .setValue(false)
        databaseReference.child(parentId).child(parentId + "의 child " + childName).child("parentAcceptTalk")
            .setValue(false)
        databaseReference.child(parentId).child(parentId + "의 child " + childName).child("finishRecordChild")
            .setValue(false)
        databaseReference.child(parentId).child(parentId + "의 child " + childName).child("finishRecordParent")
            .setValue(false)
        databaseReference.child(parentId).child(parentId + "의 child " + childName).child("finishRecordAfterFirst")
            .setValue(false)
        databaseReference.child(parentId).child(parentId + "의 child " + childName).child("parentDenyTalk")
            .setValue(false)
    }

    private fun playRecording(localFile: File){

        var i = 0
        val shortSizeInBytes = Short.SIZE_BYTES
        val bufferSizeInBytes = (localFile.length() / shortSizeInBytes).toInt()

        val audioData = ShortArray(bufferSizeInBytes)

        val inputStream = FileInputStream(localFile)
        val bufferedInputStream = BufferedInputStream(inputStream)
        val dataInputStream = DataInputStream(bufferedInputStream)

        var j = 0
        while (dataInputStream.available() > 0){
            audioData[j] = dataInputStream.readShort()
            j++
        }

        dataInputStream.close()
        i = 15060 // 16000
        val audioAttrs = AudioAttributes.Builder()
            .setLegacyStreamType(3)
            .build()

        val audioFormat = AudioFormat.Builder()
            .setSampleRate(i)
            .setEncoding(AudioFormat.ENCODING_PCM_16BIT)
            .setChannelMask(AudioFormat.CHANNEL_OUT_MONO) // channel config
            .build()

        val audioTrack = AudioTrack(audioAttrs, audioFormat, bufferSizeInBytes, 1, 1)
        audioTrack!!.play()
        audioTrack!!.write(audioData, 0 , bufferSizeInBytes)
        getDataNum++
    }

    //----------------------------tts------------------------------------------
    override fun onPause() {
        super.onPause()
        if (mTts != null) {
            if (mTts!!.isSpeaking) {
                mTts!!.stop()
            }
        }
    }

    override fun onDestroy() {
        if (mTts != null) {
            if (mTts!!.isSpeaking) {
                mTts!!.stop()
            }
            mTts!!.shutdown()
        }
        super.onDestroy()
    }

    private fun init() {
        mTts = TextToSpeech(baseContext, TextToSpeech.OnInitListener { status ->
            if (status == TextToSpeech.SUCCESS) {
                play()
            } else {
                startActivity(getSettingActIntent())
                play()
            }
        })
    }

    // 언어선택
    fun setLanguage(locale: Locale?) {
        if (mTts != null) mTts!!.language = locale
    }

    fun setPitch(value: Float) {
        if (mTts != null) mTts!!.setPitch(value)
    }

    // 속도 선택
    fun setSpeechRate(value: Float) {
        if (mTts != null) mTts!!.setSpeechRate(value)
    }

    // TTS 설정 으로 이동
    fun getSettingActIntent(): Intent? {
        val intent = Intent()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            intent.action = "com.android.settings.TTS_SETTINGS"
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        } else {
            intent.addCategory(Intent.CATEGORY_LAUNCHER)
            intent.component = ComponentName("com.android.settings", "com.android.settings.TextToSpeechSettings")
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }
        return intent
    }

    // 재생
    @Suppress("DEPRECATION")
    fun speak(text: String?, resId: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (mTts != null) mTts!!.speak(text, mQueue, null, "" + resId)
        } else {
            val map: HashMap<String, String> = HashMap()
            map[TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID] = "" + resId
            if (mTts != null) mTts!!.speak(text, mQueue, map)
        }
    }

    fun play():Boolean{
        if (mTts != null) {
            if (talk_txtview != null) {
                val text = talk_txtview.text.toString()
                if (text.isNotEmpty()) {
                    setLanguage(mLocale)
                    setPitch(mPitch)
                    setSpeechRate(mRate)
                    speak(text, 0)
                }
            }
        }
        return true
    }

    fun ParentDenyplay(){
        val myValue: DatabaseReference =
            databaseReference.child(parentId).child(parentId + "의 child " + childName).child("selectedQuestionAtDialog")
        myValue.get().addOnSuccessListener {
            finish = true
            talk_txtview.text = it.value.toString()
            play()
            setSelectTalkCharacter()
            setOnLottieStart()
            Log.d("deny", talk_txtview.text.toString())
        }.addOnFailureListener {  }
    }

    fun getFinishRecordChild(){
        val myValue: DatabaseReference =
            databaseReference.child(parentId).child(parentId + "의 child " + childName).child("finishRecordChild")
        myValue.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.value as Boolean){
                    ParentDenyplay()
                }
            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }


}