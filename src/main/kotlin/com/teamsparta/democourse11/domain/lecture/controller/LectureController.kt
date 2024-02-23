package com.teamsparta.democourse11.domain.lecture.controller

import com.teamsparta.democourse11.domain.lecture.dto.LectureResponse
import com.teamsparta.democourse11.domain.lecture.dto.UpdateLectureRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RequestMapping("/course/{courseId}/lectures")
@RestController
class LectureController(

) {

    @GetMapping
    fun getLectureList(@PathVariable courseId: Long): ResponseEntity<List<LectureResponse>> {
        TODO()
    }

    @GetMapping("/{lectureId}")//패스벨리버블- 변수 받음(api 참고), 리퀘스트 바디는 해당 dto 받음.
    fun  getLecture(@PathVariable courseId: Long, @PathVariable lectureId: Long) :ResponseEntity<LectureResponse>{
        TODO()
    }

    @PostMapping
    fun addLecture(
        @PathVariable courseId: Long,
        @PathVariable lectureId: Long,
        @RequestBody updateLectureRequest: UpdateLectureRequest
    ): ResponseEntity<LectureResponse> {
        TODO()
    }

    @PutMapping
    fun updateLecture(@PathVariable courseId: Long,
                      @PathVariable lectureId: Long,
                      @RequestBody updateLectureRequest: UpdateLectureRequest) :
     ResponseEntity<LectureResponse>{
        TODO()
    }

    @DeleteMapping("/{lectureId}")
    fun removeLecture(
        @PathVariable courseId: Long,
        @PathVariable lectureId: Long,
    ): ResponseEntity<Unit>{//<렉체리스폰스 들어가나 이번에는 없이함.>
        TODO()
    }
}