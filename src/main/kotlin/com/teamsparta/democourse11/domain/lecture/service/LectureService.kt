package com.teamsparta.democourse11.domain.lecture.service

import com.teamsparta.democourse11.domain.lecture.dto.AddLectureRequest
import com.teamsparta.democourse11.domain.lecture.dto.LectureResponse
import com.teamsparta.democourse11.domain.lecture.dto.UpdateLectureRequest

interface LectureService {

    fun getLecture(courseId: Long): List<LectureResponse>

    fun addLecture(courseId: Long, request: AddLectureRequest): LectureResponse

    fun updateLecture(
        courseId: Long,
        lectureId: Long,
        request: UpdateLectureRequest
    ): LectureResponse

    fun removeLecture(courseId: Long, lectureId: Long)
}