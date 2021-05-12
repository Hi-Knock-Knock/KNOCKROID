package com.all_the_best.knock_knock.parent.home.adapter

import android.content.Context
import android.media.MediaPlayer
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
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
            getRecord(1)
        }
    }

    private fun setOnSecondPlayBtnClick(binding: ItemParentHomeBinding) {
        binding.parentRecordBtnPlayQuestion4.setOnClickListener {
            getRecord(2)
        }
    }

    private fun getRecord(recordNum: Int) {
        var firebaseStorage: FirebaseStorage = FirebaseStorage.getInstance()
        lateinit var pathReference: StorageReference
        pathReference =
            firebaseStorage.reference.child(getToday()).child("child")
                .child("child($recordNum).mp4")

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
        }.addOnFailureListener {
            // Handle any errors
            Log.d("getAudio", "fail")
        }
    }

    private fun getToday(): String {
        val currentTime: Date = Calendar.getInstance().getTime()
        val fileNameFormat = SimpleDateFormat("yyyy-MM-dd")
        return fileNameFormat.format(currentTime).toString()
    }
}
