package com.teamsparta.democourse11.domain.user.dto

data class UserResponse(//응답, 보내줘야하는 정보모음. 비밀번호는 디비에 저장, 확인하는 용도로 쓴다.
    val id:Long,
    val email: String,
    val nickname: String,
    val role: String,

)
