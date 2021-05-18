
package com.all_the_best.knock_knock.infant.home.view

import android.content.ComponentName
import android.content.Intent
import android.graphics.Color
import android.media.AudioManager
import android.media.MediaPlayer
import android.media.SoundPool
import android.os.Build
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.airbnb.lottie.LottieDrawable
import com.all_the_best.knock_knock.R
import com.all_the_best.knock_knock.infant.change.view.InfantSwitchCharacterActivity
import com.all_the_best.knock_knock.infant.cookie.view.InfantCookieSaveActivity
import com.all_the_best.knock_knock.infant.deco.view.InfantDecoActivity
import com.all_the_best.knock_knock.infant.gift.view.InfantGiftStartActivity
import com.all_the_best.knock_knock.infant.talk.view.InfantSelectFeelActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_infant_home.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.system.exitProcess


class InfantHomeActivity : AppCompatActivity() {
    
    //bgm
    private var musicPlay:Int=0
    var mediaPlayer: MediaPlayer? = null
    var soundPool:SoundPool?=null
    private var chSelect: Int = 0
    private var bgSelect: Int = 1
    private var cookieCount: Int = 6
    private var giftSelect:Int=0
    private var lottieSelect:Int=0

    //TTS 관련 변수들
    private var mTts: TextToSpeech? = null
    private var mLocale = Locale.KOREA
    private var mPitch = 1.5f
    private var mRate = 0.8f
    private var mQueue = TextToSpeech.QUEUE_FLUSH

    private val current = LocalDateTime.now()
    private val formatter = DateTimeFormatter.ISO_LOCAL_TIME
    private val formatted = current.format(formatter)

    private val database: FirebaseDatabase = FirebaseDatabase.getInstance()
    // 데이터베이스의 인스턴스를 가져온다고 생각(즉, Root를 가져온다고 이해하면 쉬움)
    private val databaseReference: DatabaseReference = database.reference

    // 백버튼 뒤로가기시 종료
    private val FINISH_INTERVAL_TIME: Long = 2000
    private var backPressedTime: Long = 0


    override fun onResume() {
        super.onResume()
        mediaPlayer = MediaPlayer.create(this, R.raw.bgm)
    }
    @Suppress("DEPRECATION")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_infant_home)
       
        soundPool = SoundPool(1,AudioManager.STREAM_MUSIC,0)
        val soundId: Int = soundPool!!.load(this, R.raw.button, 1)

        bgSelect = intent.getIntExtra("bgSelect",1)
        chSelect = intent.getIntExtra("chSelect",0)
        cookieCount = intent.getIntExtra("cookieCount",6)
        giftSelect = intent.getIntExtra("giftSelect",0)
        lottieSelect = intent.getIntExtra("lottieSelect",0)
        musicPlay = intent.getIntExtra("musicPlay",0)

        if (musicPlay==0){
            mediaPlayer = MediaPlayer.create(this, R.raw.bgm)
            mediaPlayer!!.setVolume(0.3f,0.3f)
            mediaPlayer!!.start()

            Log.d("media",musicPlay.toString())
            musicPlay=1
            Log.d("media",musicPlay.toString())
        }else{
            Log.d("media",musicPlay.toString())
        }

        setBackgroundForTime()
        setSelectCharacter()
        setCookieSaveFirebase()
        init()

        infant_talk1.setOnClickListener{
            Log.d("time", formatted)
        }

        //선물상자에서 줄어든 쿠키 갯수 반영
        val cookieCountHome: TextView = findViewById(R.id.infant_home_cookie_count_txt)
        cookieCountHome.text = cookieCount.toString()

        //대화하기 버튼
        val intent1 = Intent(this, InfantSelectFeelActivity::class.java)
        char_talk_btn.setOnClickListener{
            soundPool!!.play(soundId, 1.0f, 1.0f, 1, 0, 1.0f)
            intent1.putExtra("chSelect",chSelect)
            intent1.putExtra("bgSelect", bgSelect)
            intent1.putExtra("cookieCount",cookieCount)
            intent1.putExtra("lottieSelect",lottieSelect)
            intent1.putExtra("giftSelect",giftSelect)
            intent1.putExtra("musicPlay",musicPlay)
            mediaPlayer!!.stop()
            setStartTalkAtFirebase()
            startActivity(intent1)
        }

        // 캐릭터 바꾸기 버튼
        val intent2 = Intent(this, InfantSwitchCharacterActivity::class.java)
        char_change_btn.setOnClickListener{
            intent2.putExtra("bgSelect",bgSelect)
            intent2.putExtra("cookieCount",cookieCount)
            intent2.putExtra("giftSelect",giftSelect)
            intent2.putExtra("lottieSelect",lottieSelect)
            intent2.putExtra("musicPlay",musicPlay)
            intent1.putExtra("chSelect",chSelect)
            soundPool!!.play(soundId, 1.0f, 1.0f, 1, 0, 1.0f)
            startActivityForResult(intent2,0)
        }

        // 선물상자 버튼
        val intent3 = Intent(this, InfantGiftStartActivity::class.java)
        infant_icon_gift.setOnClickListener{
            intent3.putExtra("bgSelect",bgSelect)
            intent3.putExtra("cookieCount",cookieCount)
            intent3.putExtra("chSelect",chSelect)
            intent3.putExtra("giftSelect",giftSelect)
            intent3.putExtra("lottieSelect",lottieSelect)
            intent3.putExtra("musicPlay",musicPlay)
            soundPool!!.play(soundId, 1.0f, 1.0f, 1, 0, 1.0f)
            startActivity(intent3)
        }

        // 배경 꾸미기 버튼
        val intent4 = Intent(this, InfantDecoActivity::class.java)
        char_deco_btn.setOnClickListener{
            intent4.putExtra("cookieCount",cookieCount)
            intent4.putExtra("chSelect",chSelect)
            intent4.putExtra("giftSelect",giftSelect)
            intent4.putExtra("lottieSelect",lottieSelect)
            intent4.putExtra("musicPlay",musicPlay)
            soundPool!!.play(soundId, 1.0f, 1.0f, 1, 0, 1.0f)
            startActivityForResult(intent4,1)
        }

        // 쿠키 저장 다람이 버튼
        val intent5 = Intent(this, InfantCookieSaveActivity::class.java)
        infant_cookie_view.setOnClickListener{
            intent5.putExtra("bgSelect",bgSelect)
            intent5.putExtra("cookieCount",cookieCount)
            intent5.putExtra("chSelect",chSelect)
            intent5.putExtra("giftSelect",giftSelect)
            intent5.putExtra("lottieSelect",lottieSelect)
            intent5.putExtra("musicPlay",musicPlay)
            soundPool!!.play(soundId, 1.0f, 1.0f, 1, 0, 1.0f)
            startActivity(intent5)
        }

    }

    // 백버튼 두번 뒤로가기시 종료
    override fun onBackPressed() {
        //mediaPlayer!!.stop()
        val tempTime = System.currentTimeMillis()
        val intervalTime: Long = tempTime - backPressedTime

        if (intervalTime in 0..FINISH_INTERVAL_TIME) {
            ActivityCompat.finishAffinity(this)
            exitProcess(0)
        } else {
            backPressedTime = tempTime
            Toast.makeText(this, "한번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 1){
            if (resultCode==RESULT_OK) {
                bgSelect = data!!.getIntExtra("bgSelect",1)
                setBackgroundForTime()
                Log.d("home", "$bgSelect")
            }else{
            }
        }else if(requestCode == 0){
            if (resultCode==RESULT_OK) {
                chSelect = data!!.getIntExtra("chSelect",0)
                setBackgroundForTime()
                Log.d("home", "$chSelect")
            }else{
            }
        }
    }

    private fun setBackgroundForTime(){
        when(formatted){
            in "08:00:000".."13:59:999" -> {
                window.statusBarColor = Color.parseColor("#57DDFF")         //상태바 색상 지정
                when (bgSelect) {
                    1 -> infant_home.setBackgroundResource(R.drawable.img_infant_home_morning_bg)
                    2 -> infant_home.setBackgroundResource(R.drawable.img_infant_home_bg_flower1)
                    3 -> infant_home.setBackgroundResource(R.drawable.img_infant_home_bg_sea1)
                    4 -> infant_home.setBackgroundResource(R.drawable.img_infant_home_bg_space1) // 우주
                }
                char_deco_btn.setImageResource(R.drawable.ic_infant_deco_btn_1)
                char_talk_btn.setImageResource(R.drawable.ic_infant_chat_btn_1)
                char_change_btn.setImageResource(R.drawable.ic_infant_change_btn_1)
                when(bgSelect){
                    4-> {
                        window.statusBarColor = Color.parseColor("#0F0E15")
                        char_deco_btn.setImageResource(R.drawable.ic_infant_deco_btn_3)
                        char_talk_btn.setImageResource(R.drawable.ic_infant_chat_btn_3)
                        char_change_btn.setImageResource(R.drawable.ic_infant_change_btn_3)

                    }
                }

            }
            in "14:00:000".."19:59:999" -> {
                window.statusBarColor = Color.parseColor("#FF6471")
                when (bgSelect) {
                    1 -> infant_home.setBackgroundResource(R.drawable.img_infant_home_after_bg)
                    2 -> infant_home.setBackgroundResource(R.drawable.img_infant_home_bg_flower2)
                    3 -> infant_home.setBackgroundResource(R.drawable.img_infant_home_bg_sea2)
                    4 -> infant_home.setBackgroundResource(R.drawable.img_infant_home_bg_space2) // 우주

                }
                char_deco_btn.setImageResource(R.drawable.ic_infant_deco_btn_2)
                char_talk_btn.setImageResource(R.drawable.ic_infant_chat_btn_2)
                char_change_btn.setImageResource(R.drawable.ic_infant_change_btn_2)
                when(bgSelect){
                    4-> {
                        window.statusBarColor = Color.parseColor("#0F0E15")
                        char_deco_btn.setImageResource(R.drawable.ic_infant_deco_btn_3)
                        char_talk_btn.setImageResource(R.drawable.ic_infant_chat_btn_3)
                        char_change_btn.setImageResource(R.drawable.ic_infant_change_btn_3)
                    }
                }

            }
            in "20:00:00".."23:59:999" -> {
                when(bgSelect){
                    1 -> infant_home.setBackgroundResource(R.drawable.img_infant_home_night_bg)
                    2 -> infant_home.setBackgroundResource(R.drawable.img_infant_home_bg_flower3)
                    3 -> infant_home.setBackgroundResource(R.drawable.img_infant_home_bg_sea3)
                    4 -> infant_home.setBackgroundResource(R.drawable.img_infant_home_bg_space3) // 우주
                }
                char_deco_btn.setImageResource(R.drawable.ic_infant_deco_btn_3)
                char_talk_btn.setImageResource(R.drawable.ic_infant_chat_btn_3)
                char_change_btn.setImageResource(R.drawable.ic_infant_change_btn_3)
                window.statusBarColor = Color.parseColor("#0F0E15")
            }
            !in "08:00:00".."23:59:999" -> {
                when(bgSelect){
                    1 -> infant_home.setBackgroundResource(R.drawable.img_infant_home_night_bg)
                    2 -> infant_home.setBackgroundResource(R.drawable.img_infant_home_bg_flower3)
                    3 -> infant_home.setBackgroundResource(R.drawable.img_infant_home_bg_sea3)
                    4 -> infant_home.setBackgroundResource(R.drawable.img_infant_home_bg_space3) // 우주
                }
                char_deco_btn.setImageResource(R.drawable.ic_infant_deco_btn_3)
                char_talk_btn.setImageResource(R.drawable.ic_infant_chat_btn_3)
                char_change_btn.setImageResource(R.drawable.ic_infant_change_btn_3)
                window.statusBarColor = Color.parseColor("#0F0E15")
            }
        }
    }

    private fun setSelectCharacter(){
        when(chSelect){
            0 -> {
                char_img.setAnimation("dami_idle.json")
                char_img.setOnClickListener{
                    char_img.setAnimation("dami_hi.json")
                    char_img.repeatMode = LottieDrawable.REVERSE
                    char_img.repeatCount = 1
                    char_img.playAnimation()
                }
            }
             // 담이 idle
            1 -> {
                char_img.setAnimation("knock_idle.json")
                char_img.setOnClickListener{
                    char_img.setAnimation("knock_wink.json")
                    char_img.repeatMode = LottieDrawable.REVERSE
                    char_img.repeatCount = 1
                    char_img.playAnimation()
                }
            } //녹녹이 idle로 바꾸기
            2 -> {char_img.setAnimation("timi_idle.json")
                char_img.setOnClickListener{
                    char_img.setAnimation("timi_jump.json")
                    char_img.repeatMode = LottieDrawable.REVERSE
                    char_img.repeatCount = 1
                    char_img.playAnimation()
                }
            } // 티미 idle
        }
        setOnMotionStart()
    }


    private fun setOnMotionStart(){
        char_img.repeatMode = LottieDrawable.REVERSE
        char_img.repeatCount = LottieDrawable.INFINITE
        char_img.playAnimation()
    }

    private fun setStartTalkAtFirebase() {
        val parentId = "부모1"
        val childName = "아이1"
        databaseReference.child(parentId).child(parentId + "의 child " + childName).child("startTalkChild")
            .setValue(true)
        Toast.makeText(this, "push", Toast.LENGTH_SHORT).show()
    }

    private fun setCookieSaveFirebase() {
        val childId = "아이1"
        databaseReference.child(childId).child(childId + "의 쿠키개수 " ).setValue(cookieCount)
        Toast.makeText(this, "push", Toast.LENGTH_SHORT).show()
    }


//    private fun getCookieDataFirebase(){
//        //lateinit var getcount: String
//        val childId = "아이1"
//        val getCountCookie: DatabaseReference =
//            databaseReference.child(childId).child(childId + "의 쿠키개수 " )
//        Toast.makeText(this, "push", Toast.LENGTH_SHORT).show()
//        getCountCookie.addValueEventListener(object : ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//                binding.txtCookie = snapshot.value as String
//            }
//            override fun onCancelled(error: DatabaseError) {
//
//            }
//        })
//    }

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

    /** 언어 선택  */
    fun setLanguage(locale: Locale?) {
        if (mTts != null) mTts!!.language = locale
    }

    fun setPitch(value: Float) {
        if (mTts != null) mTts!!.setPitch(value)
    }

    /** 속도 선택  */
    fun setSpeechRate(value: Float) {
        if (mTts != null) mTts!!.setSpeechRate(value)
    }

    /** TTS 설정 으로 이동  */
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

    /** 재생  */
    fun speak(text: String?, resId: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (mTts != null) mTts!!.speak(text, mQueue, null, "" + resId)
        } else {
            val map: HashMap<String, String> = HashMap()
            map[TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID] = "" + resId
            if (mTts != null) mTts!!.speak(text, mQueue, map)
        }
    }

    fun play(){
        if (mTts != null) {
            if (talk_txtview_home != null) {
                val text = talk_txtview_home.text.toString()
                if (text.isNotEmpty()) {
                    setLanguage(mLocale)
                    setPitch(mPitch)
                    setSpeechRate(mRate)
                    speak(text, 0)
                }
            }
        }
    }
}
