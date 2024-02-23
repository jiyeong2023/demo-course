package com.teamsparta.democourse11.courseapplication.dto

data class UpdateApplicationStatusRequest(//튜터가 승인 혹은 거절하는 것, 상태값만 스트링으로 들어옴.
    val status: String,
)
