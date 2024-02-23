package com.teamsparta.democourse11.exception.dto

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler { //모든 예외상황 코드 작성, 관리하도록 한다.

    @ExceptionHandler(ModelNotFoundException::class)//id 등 해당모델을 찾을수 없을때
    fun headleModelNotFoundException(e: ModelNotFoundException): ResponseEntity<ErrorResponse> {//리스폰스엔티티<dto입력>
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(ErrorResponse( e.message))
    }

    @ExceptionHandler(IllegalStateException::class)//잘못된 상태를 나타내는 예외
    fun handleIlegalSataeException(e: IllegalStateException): ResponseEntity<ErrorResponse> {
        return ResponseEntity
            .status(HttpStatus.CONFLICT)
            .body(ErrorResponse(e.message))
    }
    @ExceptionHandler(IllegalArgumentException::class)//잘못된 인수(아규먼트)가 전달됬을떄
    fun handleIllegalArgumentException(e: IllegalArgumentException): ResponseEntity<ErrorResponse> {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorResponse(e.message))
    }

}
