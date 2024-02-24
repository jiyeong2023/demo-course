package com.teamsparta.democourse11.courseapplication.model

import com.teamsparta.democourse11.courseapplication.dto.CourseApplicationResponse
import com.teamsparta.democourse11.domain.course.model.Course
import com.teamsparta.democourse11.domain.course.model.toResponse
import com.teamsparta.democourse11.domain.user.dto.UserResponse
import com.teamsparta.democourse11.domain.user.model.toResponse
import jakarta.persistence.*
import org.apache.catalina.User
import org.springframework.data.jpa.domain.AbstractPersistable_.id

@Entity//엔티티 선언함.
@Table(name = "course_application") //@테이블 (이름= "테이블이름")
class CourseApplication(
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    var status: CourseApplicationStatus = CourseApplicationStatus.PENDING,

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", nullable = false)
    val course: Course,

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false) //양뱡향 매핑, 조인(이름="포렌키", 상태-필요따라서)
    val user: com.teamsparta.democourse11.domain.user.model.User //유저: 유저시 강한결합이 될 수 있어 상황에 따라 id로 적기도 합니다.
) {
    @Id //아이디 관련 내용 작성. 이 작성후 다른데서 Id 관련 내용 작성안함.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    fun isProceeded(): Boolean {
        return status != CourseApplicationStatus.PENDING
    }

    fun accept() {
        status = CourseApplicationStatus.ACCEPTED
    }

    fun reject() {
        status = CourseApplicationStatus.REJECTED
    }
}
//fun User.toResponse(): UserResponse { 가 user.kt 문서에 있는데 연결안됨. 왜지?
fun CourseApplication.toResponse(): CourseApplicationResponse {
    return CourseApplicationResponse(
        id = id!!,
        course = course.toResponse(),
        user = user.toResponse(),
        status = status.name
    )
}
fun User.toResponse(): UserResponse {
    return UserResponse(
        id = id!!,
        nickname = profile.nickname,
        email = email,
        role = role.name
    ) //임시로 두긴 하는데 이건 아닌것 같다..
}



