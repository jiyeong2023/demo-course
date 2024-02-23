package com.teamsparta.democourse11.domain.user.model

import jakarta.persistence.Column

class Profile (
    @Column(name = "nickname", nullable = false) //여기서 있어야할 데이터내용
    var nickname: String,
)