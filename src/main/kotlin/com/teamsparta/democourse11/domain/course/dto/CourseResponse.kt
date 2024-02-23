package com.teamsparta.democourse11.domain.course.dto

import com.teamsparta.democourse11.domain.lecture.dto.LectureResponse


data class CourseResponse (
    val id: Long,
    val title: String,
    val description: String?,
    val status: String,
    val maxApplicants: Int,
    val numApplication: Int,
    val lectures: List<LectureResponse>,
)