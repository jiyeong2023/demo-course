package com.teamsparta.democourse11.courseapplication.controller

import com.teamsparta.democourse11.courseapplication.dto.ApplyCourseRequest
import com.teamsparta.democourse11.courseapplication.dto.CourseApplicationResponse
import com.teamsparta.democourse11.courseapplication.dto.UpdateApplicationStatusRequest
import com.teamsparta.democourse11.domain.course.service.CourseService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping


@RequestMapping("/course/{courseId}/applications")
class CourseApplicationController (
    private val courseService: CourseService
){

    @GetMapping//전체목록조회
    fun getApplicationList(@PathVariable courseId: Long): ResponseEntity<List<CourseApplicationResponse>>{
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(courseService.getCourseApplicationList(courseId))
    }

    @GetMapping("/{applicationId}")//단건조회
    fun getApplication(
        @PathVariable courseId: Long,
        @PathVariable applicationId: Long,
    ): ResponseEntity<CourseApplicationResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(courseService.getCourseApplication(courseId, applicationId))
    }

    @PostMapping
    fun applyCourse(//생성
        @PathVariable courseId: Long,
        applyCourseRequest: ApplyCourseRequest
    ): ResponseEntity<CourseApplicationResponse>{
        TODO()
    }
//에러부분: 패치매칭 함수 비즈니스로직에 : ResponseEntity<CourseApplicationResponse> { 가 빠짐.
    @PatchMapping("/{applicationId}")//패치는 어플리케이션 상태를 변경하기위해서
    fun updateApplicationStatus(
        @PathVariable courseId: Long,
        @PathVariable applicationId: Long,
        @RequestBody updateApplicationStatusRequest: UpdateApplicationStatusRequest
    ): ResponseEntity<CourseApplicationResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(
                courseService.updateCourseApplicationStatus(
                    courseId,applicationId,updateApplicationStatusRequest))
    }
}
