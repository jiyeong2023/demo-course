package com.teamsparta.democourse11.courseapplication.dto


import com.teamsparta.democourse11.domain.user.dto.UserResponse

data class CourseApplicationResponse(//렉쳐, 코스 정보 같이줘서, 상태만 보고도 알 수있도록.
    val id: Long,
    val course: com.teamsparta.democourse11.domain.course.dto.CourseResponse,//데이터 클래스 하위에 데이터클래스를 포함시킬수 있다.
    val user: UserResponse,
    val status: String,
)
