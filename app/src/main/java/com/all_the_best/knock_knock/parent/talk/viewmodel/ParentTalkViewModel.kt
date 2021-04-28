package com.all_the_best.knock_knock.parent.talk.viewmodel

import androidx.lifecycle.ViewModel
import com.all_the_best.knock_knock.parent.talk.model.ParentTalkAcceptTip

class ParentTalkViewModel : ViewModel() {
    val tipList = listOf(
        ParentTalkAcceptTip(1, "아이의 말을 끝까지 들어주세요"),
        ParentTalkAcceptTip(2, "아이의 입장에서 꾸준히 공감하는 질문을 많이 해주세요."),
        ParentTalkAcceptTip(3, "먼저 답을 정해놓고 아이에게 원하는 대답을 듣는 것을 강요하지 마세요."),
        ParentTalkAcceptTip(4, "아이의 말을 맞장구치며 듣는 것이 중요해요. 함께 공감해주세요."),
        ParentTalkAcceptTip(5, "아이에게 칭찬하는 것이 가장 중요해요. 아낌없이 칭찬해주세요."),
        ParentTalkAcceptTip(6, "아이와 대화 할 때 '하지 마라'는 부정 지시어보다 '하라'는 긍정 지시어를 사용해주세요."),
        ParentTalkAcceptTip(7, "차분한 목소리로 대화해주세요.")
    )
}