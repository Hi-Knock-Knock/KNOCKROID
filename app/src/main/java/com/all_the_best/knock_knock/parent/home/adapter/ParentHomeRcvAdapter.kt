package com.all_the_best.knock_knock.parent.home.adapter

import android.content.Context
import android.media.MediaPlayer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.all_the_best.knock_knock.R
import com.all_the_best.knock_knock.databinding.ItemParentHomeBinding
import com.all_the_best.knock_knock.parent.home.model.ParentHomeRecord
import com.bumptech.glide.Glide
import com.google.firebase.storage.FirebaseStorage
import java.io.File
import java.text.SimpleDateFormat
import java.util.*


class ParentHomeRcvAdapter(private val context: Context) :
    ListAdapter<ParentHomeRecord, ParentHomeRcvAdapter.ParentHomeRcvViewHolder>(
        ParentHomeRcvDiffUtil()
    ) {
    private val player1 = MediaPlayer()
    private val player2 = MediaPlayer()
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
            getTime(binding)
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
                setAudio(1, binding)
            } else {
                restartRecord(1, binding)
            }

            it.visibility = View.INVISIBLE
            binding.parentRecordBtnPauseQuestion3.visibility = View.VISIBLE
        }
    }


    private fun setOnSecondPlayBtnClick(binding: ItemParentHomeBinding) {
        binding.parentRecordBtnPlayQuestion4.setOnClickListener {
            if (!isClickPauseSecond) {
                setAudio(2, binding)
            } else {
                restartRecord(2, binding)
            }

            it.visibility = View.INVISIBLE
            binding.parentRecordBtnPauseQuestion4.visibility = View.VISIBLE
        }
    }

    private fun setOnFirstPauseBtnClick(binding: ItemParentHomeBinding) {
        binding.parentRecordBtnPauseQuestion3.setOnClickListener {
            isClickPauseFirst = true
            pauseRecord(1)
            it.visibility = View.INVISIBLE
            binding.parentRecordBtnPlayQuestion3.visibility = View.VISIBLE
        }
    }

    private fun setOnSecondPauseBtnClick(binding: ItemParentHomeBinding) {
        binding.parentRecordBtnPauseQuestion4.setOnClickListener {
            isClickPauseSecond = true
            pauseRecord(2)
            it.visibility = View.INVISIBLE
            binding.parentRecordBtnPlayQuestion4.visibility = View.VISIBLE
        }
    }


    private fun pauseRecord(recordNum: Int) {
        if (recordNum == 1) {
            player1.pause()
        } else {
            player2.pause()
        }
    }

    private fun restartRecord(recordNum: Int, binding: ItemParentHomeBinding) {
        if (recordNum == 1) {
            player1.start()
            Thread {
                while (player1.isPlaying) {  // 음악이 실행중일때 계속 돌아가게 함
                    try {
                        Thread.sleep(20) // 1초마다 시크바 움직이게 함
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                    // 현재 재생중인 위치를 가져와 시크바에 적용
                    binding.parentRecordSeekBarQuestion3.progress = player1.currentPosition
                }
            }.start()
            player1.setOnCompletionListener {
                isClickPauseFirst = false
                binding.parentRecordBtnPauseQuestion3.visibility = View.INVISIBLE
                binding.parentRecordBtnPlayQuestion3.visibility = View.VISIBLE
            }
        } else {
            player2.start()
            Thread {
                while (player2.isPlaying) {  // 음악이 실행중일때 계속 돌아가게 함
                    try {
                        Thread.sleep(20) // 1초마다 시크바 움직이게 함
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                    // 현재 재생중인 위치를 가져와 시크바에 적용
                    binding.parentRecordSeekBarQuestion4.progress = player2.currentPosition
                }
            }.start()
            player2.setOnCompletionListener {
                isClickPauseSecond = false
                binding.parentRecordBtnPauseQuestion4.visibility = View.INVISIBLE
                binding.parentRecordBtnPlayQuestion4.visibility = View.VISIBLE
            }
        }
    }

    private fun setSeekBar(recordNum: Int, binding: ItemParentHomeBinding) {
        if (recordNum == 1) {
            binding.parentRecordSeekBarQuestion3.apply {
                max = player1.duration
                setOnSeekBarChangeListener(object :
                    OnSeekBarChangeListener {
                    override fun onProgressChanged(
                        seekBar: SeekBar,
                        progress: Int,
                        fromUser: Boolean
                    ) {
                        if (fromUser)
                            player1.seekTo(progress)
                    }

                    override fun onStartTrackingTouch(seekBar: SeekBar) {}
                    override fun onStopTrackingTouch(seekBar: SeekBar) {}
                })
            }
        } else {
            binding.parentRecordSeekBarQuestion4.apply {
                max = player2.duration
                setOnSeekBarChangeListener(object :
                    OnSeekBarChangeListener {
                    override fun onProgressChanged(
                        seekBar: SeekBar,
                        progress: Int,
                        fromUser: Boolean
                    ) {
                        if (fromUser)
                            player2.seekTo(progress)
                    }

                    override fun onStartTrackingTouch(seekBar: SeekBar) {}
                    override fun onStopTrackingTouch(seekBar: SeekBar) {}
                })
            }
        }
    }

    private fun setAudio(recordNum: Int, binding: ItemParentHomeBinding) {
        val firebaseStorage: FirebaseStorage = FirebaseStorage.getInstance()
        val pathReference =
            firebaseStorage.reference.child(getToday()).child("child")
                .child("child($recordNum).mp4")
        // createTempFile : 임시파일 생성 (so, 사용이 끝나면 삭제해줘야함.)
        // deleteOnExit을 사용해서 파일 삭제 -> 특징 : 파일을 바로 삭제하는 것이 아니라, JVM이 종료될 때 자동으로 저장된 파일을 삭제함.
        val localFile = File.createTempFile("temp_download", "mp4")
        localFile.deleteOnExit()
        pathReference.getFile(localFile).addOnSuccessListener {
            if (recordNum == 1) {
                player1.reset()
                player1.setDataSource(localFile.path)
                player1.prepare()
            } else {
                player2.reset()
                player2.setDataSource(localFile.path)
                player2.prepare()
            }
            setSeekBar(recordNum, binding)
            restartRecord(recordNum, binding)

            Log.d("getAudio", "success")
        }.addOnFailureListener {

        }
    }

    private fun getToday(): String {
        val currentTime: Date = Calendar.getInstance().getTime()
        val fileNameFormat = SimpleDateFormat("yyyy-MM-dd")
        return fileNameFormat.format(currentTime).toString()
    }

    private fun getTime(binding: ItemParentHomeBinding) {

        val firebaseStorage: FirebaseStorage = FirebaseStorage.getInstance()
        val firstRecord =
            firebaseStorage.reference.child(getToday()).child("child")
                .child("child(1).mp4")
        val secondRecord =
            firebaseStorage.reference.child(getToday()).child("child")
                .child("child(2).mp4")
        val firstFile = File.createTempFile("temp_download1", "mp4")
        firstFile.deleteOnExit()
        val secondFile = File.createTempFile("temp_download2", "mp4")
        secondFile.deleteOnExit()

        firstRecord.getFile(firstFile).addOnSuccessListener {
            player1.setDataSource(firstFile.path)
            player1.prepare()
            val minute = player1.duration / 1000 / 60
            val second = (player1.duration / 1000) - (minute * 60)
            val time = String.format(
                "%01d:%02d",
                minute,
                second
            )
            binding.parentRecordTxtEndTimeQuestion3.text = time
            player1.reset()
        }.addOnFailureListener {}

        secondRecord.getFile(secondFile).addOnSuccessListener {
            player2.setDataSource(secondFile.path)
            player2.prepare()
            val minute = player2.duration / 1000 / 60
            val second = (player2.duration / 1000) - (minute * 60)
            val time = String.format(
                "%01d:%02d",
                minute,
                second
            )
            binding.parentRecordTxtEndTimeQuestion4.text = time
            player2.reset()
        }.addOnFailureListener {}
    }
}
