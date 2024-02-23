package com.teamsparta.democourse11.domain.user.dto

data class SignUpRequest (//로그인시 받을 정보.
    val email: String,
    val password: String,
    val nickname: String,
    val role: String,
)