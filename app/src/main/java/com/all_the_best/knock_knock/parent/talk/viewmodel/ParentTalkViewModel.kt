package com.all_the_best.knock_knock.parent.talk.viewmodel

import androidx.lifecycle.ViewModel
import com.all_the_best.knock_knock.parent.talk.model.ParentTalkAcceptTip

class ParentTalkViewModel : ViewModel() {
    val tipList = listOf(
        ParentTalkAcceptTip(1, "아이의 말을 끝까지 들어주세요"),
        ParentTalkAcceptTip(2, "연주의 말을 끝까지 들어주세요"),
        ParentTalkAcceptTip(3, "지수의 말을 끝까지 들어주세요"),
        ParentTalkAcceptTip(4, "지호의 말을 끝까지 들어주세요"),
        ParentTalkAcceptTip(5, "윤정의 말을 끝까지 들어주세요")
    )
}