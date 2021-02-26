package com.all_the_best.knock_knock.parent.faq.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.all_the_best.knock_knock.R
import com.all_the_best.knock_knock.parent.home.view.ParentHomeActivity
import com.all_the_best.knock_knock.parent.faq.adapter.ParentFaqData
import com.all_the_best.knock_knock.util.StatusBarUtil
import kotlinx.android.synthetic.main.activity_parent_faq_detail.*

class ParentFaqDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parent_faq_detail)

        StatusBarUtil.setStatusBar(this, R.color.light_blue_status_bar)

        val intent1 = Intent(this, ParentHomeActivity::class.java)
        faqdetail_btn_back.setOnClickListener{
            startActivity(intent1)
            //뒤로가기해도 뷰페이저에서 faq 화면 유지하도록 하는 방법 찾기
        }

        var faqData = intent.getParcelableExtra<ParentFaqData>("FaqData")

        faqdetail_txt_faqtitle.text = "Q " + faqData?.title
        faqdetail_txt_answer.text = "- \"미안해\" 를 강요하지 마세요.\n" +
                "- 따뜻한 눈길로 바라봐주세요\n" +
                "- 칭찬과 애정표현을 자주해주세요\n" +
                "- 아이 스스로 문제를 해결할 수 있게 \n" +
                "하세요.\n" +
                "- 아이의 눈높이에 맞게 설명해주세요\n" +
                "- 아이의 사소한 고민도 진지하게 \n" +
                "들어주세요.\n" +
                "- 야단보다는 잘못된 이유를 \n" +
                "설명해주세요.\n" +
                "- 아이의 시간에 맞춰 정말 재밌게 \n" +
                "놀아주세요.\n" +
                "- 인내심을 가지고 귀를 기울여주세요.\n" +
                "- 그림책을 함께 읽는 시간을 보내주세요."

        if (faqData != null) {
            if(faqData.checked){
                //넘어온 값이 true, 즉 북마크 체크 되어야 할 경우
                faqdetail_bookmark.setBackgroundResource(R.drawable.ic_bookmark_checked)
            }else{
                //넘어온 값이 false, 즉 북마크 체크 해제 되어야 할 경우
                faqdetail_bookmark.setBackgroundResource(R.drawable.ic_bookmark_unchecked)
            }
        }

        faqdetail_bookmark.setOnClickListener {
            if (faqData != null) {
                if(faqData.checked){
                    //버튼 클릭했을 시, 체크되어있는 북마크일 경우 -> 클릭하면 체크 해제 되도록
                    //상세화면에서 북마크 변경하면 리사이클러뷰에도 적용되도록 해야함...(추가 코딩 필요)
                    faqData.checked = false
                    faqdetail_bookmark.setBackgroundResource(R.drawable.ic_bookmark_unchecked)
                }else{
                    //버튼 클릭했을 시, 체크되어있지 않은 북마크일 경우 -> 클릭하면 체크 되도록
                    //상세화면에서 북마크 변경하면 리사이클러뷰에도 적용되도록 해야함...(추가 코딩 필요)
                    faqData.checked = true
                    faqdetail_bookmark.setBackgroundResource(R.drawable.ic_bookmark_checked)
                }
            }
        }



    }
}