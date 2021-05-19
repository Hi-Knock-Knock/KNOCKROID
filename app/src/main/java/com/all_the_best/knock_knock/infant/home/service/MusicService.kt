package com.all_the_best.knock_knock.infant.home.service

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import com.all_the_best.knock_knock.R


class MusicService : Service() {
    // 배경음악 서비스 
    var mediaPlayer: MediaPlayer? = null

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }


    override fun onCreate() {
        super.onCreate()
        mediaPlayer = MediaPlayer.create(this, R.raw.bgm)
        mediaPlayer!!.setVolume(0.3f,0.3f)
        mediaPlayer!!.isLooping = true
    }


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        mediaPlayer!!.start()
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer!!.stop()
    }


}