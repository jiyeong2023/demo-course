package com.teamsparta.democourse11.domain.user.service

import com.teamsparta.democourse11.domain.user.dto.SignUpRequest
import com.teamsparta.democourse11.domain.user.dto.UserResponse
import domain.user.dto.SignUpRequest
import domain.user.dto.UpdateUserProfileRequest
import domain.user.dto.UserResponse

interface UserService {
    fun signup(request: SignUpRequest): UserResponse

    fun updateUserProfile(userId: Long, request: UpdateUserProfileRequest) : UserResponse

}