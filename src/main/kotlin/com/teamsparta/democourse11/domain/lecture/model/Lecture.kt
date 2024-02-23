package com.teamsparta.democourse11.domain.lecture.model


import com.teamsparta.democourse11.domain.lecture.dto.LectureResponse
import jakarta.persistence.*

@Entity //엔티티 어노테이션 선언
@Table(name = "lecture")
class Lecture(
    @Column(name = "title", nullable = false)
    var title: String,

    @Column(name = "video_url", nullable = false)
    var videoUrl: String,

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    var course: com.teamsparta.democourse11.domain.course.model.Course
) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

}
fun Lecture.toResponse(): LectureResponse {
    return LectureResponse(
        id = id!!,
        title = title,
        videoUrl = videoUrl,
    )
}