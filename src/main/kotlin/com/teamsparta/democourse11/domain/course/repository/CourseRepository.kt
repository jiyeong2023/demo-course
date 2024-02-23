package com.teamsparta.democourse11.domain.course.repository


import org.springframework.data.jpa.repository.JpaRepository

interface CourseRepository: JpaRepository<com.teamsparta.democourse11.domain.course.model.Course, Long> {

}