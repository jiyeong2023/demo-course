package com.teamsparta.democourse11.domain.user.service

import com.teamsparta.democourse11.domain.user.dto.SignUpRequest
import com.teamsparta.democourse11.domain.user.dto.UpdateUserProfileRequest
import com.teamsparta.democourse11.domain.user.dto.UserResponse


interface UserService {
    fun signup(request: SignUpRequest): UserResponse

    fun updateUserProfile(userId: Long, request: UpdateUserProfileRequest) : UserResponse

}