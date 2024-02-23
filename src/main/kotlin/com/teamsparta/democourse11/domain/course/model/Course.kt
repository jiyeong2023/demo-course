package com.teamsparta.democourse11.domain.course.model

import com.teamsparta.democourse11.courseapplication.model.CourseApplication
import com.teamsparta.democourse11.domain.lecture.model.Lecture
import jakarta.persistence.*
import org.springframework.data.jpa.domain.AbstractPersistable_.id

@Entity
@Table(name = "course")
class Course (
    @Column(name = "title", nullable = false)//컬럼은 테이블이랑 어떤 매핑이된다 의미(이름= "테이블내이름", 상태= 필요시 )
    var title: String,//어플리케이션에서 쓰는 용어: 타입. 경우따라 위의 파리미터 내 이름과 동일하지 않아도 된다.

    @Column(name = "description")
    var description: String? = null,

    @Enumerated(EnumType.STRING) //실수방지하기 위해 이넘사용. 이넘사용시 붙이는 어노테이션. 여기서는 0-오픈, 1-클로스 의미함. (이넘타입.스트링)
    @Column(name = "status", nullable = false)
    var status: com.teamsparta.democourse11.domain.course.model.CourseStatus = com.teamsparta.democourse11.domain.course.model.CourseStatus.OPEN,

    @Column(name = "max_applicants", nullable = false)
    val maxApplicants: Int = 30,//바뀌지 않는 값 val 지정.

    @Column(name = "num_applicants", nullable = false)
    var numApplicants: Int = 0,//강의신청시 신청인원없음. 0으로 지정

    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY, cascade = [CascadeType.ALL], orphanRemoval = true)
    var lectures: MutableList<Lecture> = mutableListOf(), //미터블리스트- 자료가 변할수 있을때 사용한다. 메트바이는 연관관계아닌쪽에 표시함
    //뱃지= 뱃지타입.성능항상 위해 레이지(지연로딩).

    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY, cascade = [CascadeType.ALL], orphanRemoval = true)
    //orphanRemoval 옵션을 쓰면 고아관계(부모자식 끊어진 spl 삭제가 가능하다.
    var courseApplications: MutableList<CourseApplication> = mutableListOf()

){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //제네레이티벨류
    var id: Long? = null

    fun isFull(): Boolean {
        return numApplicants >= maxApplicants
    }

    fun isClosed(): Boolean {
        return status == com.teamsparta.democourse11.domain.course.model.CourseStatus.CLOSED
    }

    fun close() {
        status = com.teamsparta.democourse11.domain.course.model.CourseStatus.CLOSED
    }

    fun addApplicant() {
        numApplicants += 1
    }

    fun addLecture(lecture: Lecture) {
        lectures.add(lecture)
    }

    fun removeLecture(lecture: Lecture) {
        lectures.remove(lecture)
    }

    fun addCourseApplication(courseApplication: CourseApplication) {
        courseApplications.add(courseApplication)
    }

}

fun Course.toResponse(): com.teamsparta.democourse11.domain.course.dto.CourseResponse {
    return com.teamsparta.democourse11.domain.course.dto.CourseResponse(
        id = id!!,
        title = title,
        description = description,
        status = status.name,
        maxApplicants = maxApplicants,
        numApplicants = numApplicants,
    )
}