package com.all_the_best.knock_knock.parent.faq.adapter

import android.content.Intent
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.all_the_best.knock_knock.R
import com.all_the_best.knock_knock.parent.faq.model.ParentFaqData
import com.all_the_best.knock_knock.parent.faq.view.ParentFaqDetailActivity

class ParentFaqViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){
    private val faqTitle: TextView = itemView.findViewById(R.id.rcv_faq_txt_title)
    private val faqBookmark: ImageButton = itemView.findViewById(R.id.rcv_faq_bookmark)

    fun onBind(data: ParentFaqData){
        faqTitle.text = data.title

        if(data.isScrapped){
            //넘어온 값이 true, 즉 북마크 체크 되어야 할 경우
            faqBookmark.setImageResource(R.drawable.ic_bookmark_checked)
        }else{
            //넘어온 값이 false, 즉 북마크 체크 해제 되어야 할 경우
            faqBookmark.setBackgroundResource(R.drawable.ic_bookmark_unchecked)
        }

        faqBookmark.setOnClickListener {
            if(data.isScrapped){
                //버튼 클릭했을 시, 체크되어있는 북마크일 경우 -> 클릭하면 체크 해제 되도록
                data.isScrapped = false
                faqBookmark.setBackgroundResource(R.drawable.ic_bookmark_unchecked)
            }else{
                //버튼 클릭했을 시, 체크되어있지 않은 북마크일 경우 -> 클릭하면 체크 되도록
                data.isScrapped = true
                faqBookmark.setBackgroundResource(R.drawable.ic_bookmark_checked)
            }
        }

        itemView.setOnClickListener{
            var detailIntent= Intent(itemView.context, ParentFaqDetailActivity::class.java)
            detailIntent.putExtra("FaqData",data)
            itemView.context.startActivity(detailIntent)
        }

    }
}