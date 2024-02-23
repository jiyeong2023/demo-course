package com.teamsparta.democourse11.domain.user.controller

import com.teamsparta.democourse11.domain.user.dto.SignUpRequest
import com.teamsparta.democourse11.domain.user.dto.UpdateUserProfileRequest
import com.teamsparta.democourse11.domain.user.dto.UserResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(//유저는 인증인가 들어가면 유저로 묶이지 않음. 일단은 사인업만 구현.

) {
    @PostMapping("/signup")
    fun signUp(@RequestBody signUpRequest: SignUpRequest):ResponseEntity<UserResponse>{
        TODO()
    }

    @PutMapping("/users/")//유저의 프로파일변경
    fun updateUserProfile(@PathVariable userId: Long,
                           @RequestBody updateUserProfileRequest: UpdateUserProfileRequest
    ):
            ResponseEntity<UserResponse>{//단순이 유저리스폰스만 주도록 하겠습니다.
        TODO()
    }
}