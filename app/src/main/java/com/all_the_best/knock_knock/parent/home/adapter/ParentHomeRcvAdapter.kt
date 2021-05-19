package com.all_the_best.knock_knock.parent.home.adapter

import android.content.Context
import android.media.MediaPlayer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.all_the_best.knock_knock.R
import com.all_the_best.knock_knock.databinding.ItemParentHomeBinding
import com.all_the_best.knock_knock.parent.home.model.ParentHomeRecord
import com.bumptech.glide.Glide
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class ParentHomeRcvAdapter(private val context: Context) :
    ListAdapter<ParentHomeRecord, ParentHomeRcvAdapter.ParentHomeRcvViewHolder>(
        ParentHomeRcvDiffUtil()
    ) {
    private val player = MediaPlayer()
    private var isClickPauseFirst = false
    private var isClickPauseSecond = false

    inner class ParentHomeRcvViewHolder(private val binding: ItemParentHomeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(parentHomeRecordData: ParentHomeRecord) {
            binding.parentHomeRecordData = parentHomeRecordData
            Log.d("tag_img_bind", parentHomeRecordData.uri.toString())
            Glide.with(context)
                .load(parentHomeRecordData.uri)
                .centerCrop()
                .error(R.drawable.img_baby_mybaby1)
                .into(binding.rcvParentImgChild)
            setOnFirstPlayBtnClick(binding)
            setOnSecondPlayBtnClick(binding)
            setOnFirstPauseBtnClick(binding)
            setOnSecondPauseBtnClick(binding)
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParentHomeRcvViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemParentHomeBinding =
            DataBindingUtil.inflate(
                layoutInflater,
                R.layout.item_parent_home,
                parent,
                false
            )
        return ParentHomeRcvViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ParentHomeRcvViewHolder, position: Int) {
        Log.d("tag_img_adpater", getItem(position).uri.toString())
        holder.bind(getItem(position))
    }

    private class ParentHomeRcvDiffUtil : DiffUtil.ItemCallback<ParentHomeRecord>() {
        override fun areItemsTheSame(
            oldItem: ParentHomeRecord,
            newItem: ParentHomeRecord
        ) =
            (oldItem.nickname == newItem.nickname)

        override fun areContentsTheSame(
            oldItem: ParentHomeRecord,
            newItem: ParentHomeRecord
        ) =
            (oldItem == newItem)
    }

    private fun setOnFirstPlayBtnClick(binding: ItemParentHomeBinding) {
        binding.parentRecordBtnPlayQuestion3.setOnClickListener {
            if (!isClickPauseFirst) {
                getRecord(1,binding)
            } else {
                restartRecord()
            }
            it.visibility = View.INVISIBLE
            binding.parentRecordBtnPauseQuestion3.visibility = View.VISIBLE
        }
    }


    private fun setOnSecondPlayBtnClick(binding: ItemParentHomeBinding) {
        binding.parentRecordBtnPlayQuestion4.setOnClickListener {
            if (!isClickPauseSecond) {
                getRecord(2,binding)
            } else {
                restartRecord()
            }
            it.visibility = View.INVISIBLE
            binding.parentRecordBtnPauseQuestion4.visibility = View.VISIBLE
        }
    }

    private fun setOnFirstPauseBtnClick(binding: ItemParentHomeBinding) {
        binding.parentRecordBtnPauseQuestion3.setOnClickListener {
            isClickPauseFirst = true
            pauseRecord()
            it.visibility = View.INVISIBLE
            binding.parentRecordBtnPlayQuestion3.visibility = View.VISIBLE
        }
    }

    private fun setOnSecondPauseBtnClick(binding: ItemParentHomeBinding) {
        binding.parentRecordBtnPauseQuestion4.setOnClickListener {
            isClickPauseSecond = true
            pauseRecord()
            it.visibility = View.INVISIBLE
            binding.parentRecordBtnPlayQuestion4.visibility = View.VISIBLE
        }
    }

    private fun getRecord(recordNum: Int, binding:ItemParentHomeBinding) {
        val firebaseStorage: FirebaseStorage = FirebaseStorage.getInstance()
        val pathReference =
            firebaseStorage.reference.child(getToday()).child("child")
                .child("child($recordNum).mp4")
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
            player.setOnCompletionListener {
                if(recordNum == 1){
                    isClickPauseFirst = false
                    binding.parentRecordBtnPauseQuestion3.visibility = View.INVISIBLE
                    binding.parentRecordBtnPlayQuestion3.visibility = View.VISIBLE
                } else{
                    isClickPauseSecond = false
                    binding.parentRecordBtnPauseQuestion4.visibility = View.INVISIBLE
                    binding.parentRecordBtnPlayQuestion4.visibility = View.VISIBLE
                }
            }
            Log.d("getAudio", "success")
        }.addOnFailureListener {

        }
    }

    private fun pauseRecord() {
        player.pause()
    }

    private fun restartRecord() {
        player.start()
    }

    private fun getToday(): String {
        val currentTime: Date = Calendar.getInstance().getTime()
        val fileNameFormat = SimpleDateFormat("yyyy-MM-dd")
        return fileNameFormat.format(currentTime).toString()
    }
}
