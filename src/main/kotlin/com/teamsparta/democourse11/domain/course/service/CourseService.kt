package com.teamsparta.democourse11.domain.course.service

import com.teamsparta.democourse11.courseapplication.dto.ApplyCourseRequest
import com.teamsparta.democourse11.courseapplication.dto.CourseApplicationResponse
import com.teamsparta.democourse11.courseapplication.dto.UpdateApplicationStatusRequest
import com.teamsparta.democourse11.domain.lecture.dto.AddLectureRequest
import com.teamsparta.democourse11.domain.lecture.dto.LectureResponse
import com.teamsparta.democourse11.domain.lecture.dto.UpdateLectureRequest

interface CourseService {

    fun getAllCourseList() : List<com.teamsparta.democourse11.domain.course.dto.CourseResponse>

    fun getCourseById(courseId: Long): com.teamsparta.democourse11.domain.course.dto.CourseResponse
//인자로 받은 것을 파라미터안에 넣는다.
    fun createCourse(request: com.teamsparta.democourse11.domain.course.dto.CreateCourseRequest) : com.teamsparta.democourse11.domain.course.dto.CourseResponse

    fun updateCourse(courseId: Long, request: com.teamsparta.democourse11.domain.course.dto.UpdateCourseRequest) : com.teamsparta.democourse11.domain.course.dto.CourseResponse

    fun deleteCourse(courseId: Long)


    fun getLecture(courseId: Long): List<LectureResponse>

    fun addLecture(courseId: Long, request: AddLectureRequest): LectureResponse

    fun updateLecture(
        courseId: Long,
        lectureId: Long,
        request: UpdateLectureRequest
    ): LectureResponse

    fun removeLecture(courseId: Long, lectureId: Long)

    fun applyCourse(courseId: Long, request: ApplyCourseRequest): CourseApplicationResponse

    fun getCourseApplication(
        courseId: Long,
        applicationId: Long
    ): CourseApplicationResponse

    fun getCourseApplicationList(courseId: Long): List<CourseApplicationResponse>

    fun updateCourseApplicationStatus(
        courseId: Long,
        applicationId: Long,
        request: UpdateApplicationStatusRequest
    ): CourseApplicationResponse
}