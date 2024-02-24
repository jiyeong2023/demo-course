package com.teamsparta.democourse11.domain.lecture.repository

import com.teamsparta.democourse11.domain.lecture.model.Lecture
import org.springframework.data.jpa.repository.JpaRepository

interface LectureRepository : JpaRepository<Lecture, Long> {

    //  fun findByCourseIdAndId(courseId: Long, lectureId: Long): Lecture?

    fun findByCourseIdAndId(courseId: Long, lectureId: Long): List<Lecture>?
}