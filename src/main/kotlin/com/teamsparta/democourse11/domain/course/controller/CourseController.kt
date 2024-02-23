package com.teamsparta.democourse11.domain.course.controller


import com.teamsparta.democourse11.domain.course.service.CourseService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/courses")
@RestController

class CourseController(
       private val courseService: CourseService
//생성자주입.
) {

    @GetMapping
    fun getCourseList(): ResponseEntity<List<com.teamsparta.democourse11.domain.course.dto.CourseResponse>> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(courseService.getAllCourseList())//바디에 dto 담음
    }

    @GetMapping("/{courseId}")
    fun getCourse(@PathVariable courseId: Long) : ResponseEntity<com.teamsparta.democourse11.domain.course.dto.CourseResponse> {
        return  ResponseEntity
            .status(HttpStatus.OK)
            .body(courseService.getCourseById(courseId))
    }

    @PostMapping
    fun createCourse(@RequestBody createCourseRequest: com.teamsparta.democourse11.domain.course.dto.CreateCourseRequest) : ResponseEntity<com.teamsparta.democourse11.domain.course.dto.CourseResponse> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(courseService.createCourse(createCourseRequest))
    }

    @PutMapping("/{courseId}")
    fun updateCourse(@PathVariable courseId: Long, @RequestBody updateCourseRequest: com.teamsparta.democourse11.domain.course.dto.UpdateCourseRequest)
    : ResponseEntity<com.teamsparta.democourse11.domain.course.dto.CourseResponse>{
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(courseService.updateCourse(courseId, updateCourseRequest))
    }

    @DeleteMapping("/{courseId}")
    fun daleteCourse(@PathVariable courseId: Long) : ResponseEntity<Unit> {
        courseService.deleteCourse(courseId)
         return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build()

    }

}