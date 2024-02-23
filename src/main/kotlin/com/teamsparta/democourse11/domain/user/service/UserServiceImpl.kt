package com.teamsparta.democourse11.domain.user.service

import com.teamsparta.democourse11.domain.user.dto.UpdateUserProfileRequest
import com.teamsparta.democourse11.domain.user.dto.UserResponse
import com.teamsparta.democourse11.domain.user.model.UserRole
import com.teamsparta.democourse11.domain.user.dto.SignUpRequest
import com.teamsparta.democourse11.domain.user.model.Profile
import com.teamsparta.democourse11.domain.user.model.User
import com.teamsparta.democourse11.domain.user.model.toResponse
import com.teamsparta.democourse11.domain.user.repository.UserRepository
import com.teamsparta.democourse11.exception.dto.ModelNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.transaction.annotation.Transactional

class UserServiceImpl(
    private val userRepository: UserRepository
): UserService {

    @Transactional
    override fun signUp(request: SignUpRequest): UserResponse {
    // TODO: Email이 중복되는지 확인, 중복된다면 throw IllegalStateException
    // TODO: request를 User로 변환 후 DB에 저장, 비밀번호는 저장시 암호화
    if (userRepository.existsByEmail(request.email)) {
        throw IllegalStateException("Email is already in use")
    }//이메일이 존재하지 않으면 예외 에러 보냄.

    return userRepository.save(
        User(
            email = request.email,
            // TODO: 비밀번호 암호화
            password = request.password,
            profile = Profile(
                nickname = request.nickname
            ), //역할에 따라서
            role = when (request.role) {
                UserRole.STUDENT.name -> UserRole.STUDENT
                UserRole.TUTOR.name -> UserRole.TUTOR
                else -> throw IllegalArgumentException("Invalid role")
            }
        )
    ).toResponse()
}

    @Transactional
    override fun updateUserProfile(userId: Long, request: UpdateUserProfileRequest): UserResponse {
        // TODO: 만약 userId에 해당하는 User가 없다면 throw ModelNotFoundException
        // TODO: DB에서 userId에 해당하는 User를 가져와서 updateUserProfileRequest로 업데이트 후 DB에 저장, 결과를 UserResponse로 변환 후 반환

        val user = userRepository.findByIdOrNull(userId) ?: throw ModelNotFoundException("User", userId)
        user.profile = Profile(
            nickname = request.nickname
        )

        return userRepository.save(user).toResponse()
    }

}