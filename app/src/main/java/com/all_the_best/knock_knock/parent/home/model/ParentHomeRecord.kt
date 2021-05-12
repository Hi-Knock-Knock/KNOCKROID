package com.all_the_best.knock_knock.parent.home.model

data class ParentHomeRecord(
    val nickname: String,
    var uri: String?,
    var answer1: String,
    var answer2: String,
    var selectedQuestion: String?
)
