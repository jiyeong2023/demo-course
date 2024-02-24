package com.teamsparta.democourse11.domain.course.service

import com.teamsparta.democourse11.courseapplication.dto.ApplyCourseRequest
import com.teamsparta.democourse11.courseapplication.dto.CourseApplicationResponse
import com.teamsparta.democourse11.courseapplication.dto.UpdateApplicationStatusRequest
import com.teamsparta.democourse11.courseapplication.model.CourseApplication
import com.teamsparta.democourse11.courseapplication.model.CourseApplicationStatus
import com.teamsparta.democourse11.courseapplication.model.toResponse
import com.teamsparta.democourse11.courseapplication.repository.CourseApplicationRepository
import com.teamsparta.democourse11.domain.course.dto.CourseResponse
import com.teamsparta.democourse11.domain.course.dto.CreateCourseRequest
import com.teamsparta.democourse11.domain.course.dto.UpdateCourseRequest
import com.teamsparta.democourse11.domain.course.model.Course
import com.teamsparta.democourse11.domain.course.model.CourseStatus
import com.teamsparta.democourse11.domain.course.model.toResponse
import com.teamsparta.democourse11.domain.lecture.dto.AddLectureRequest
import com.teamsparta.democourse11.domain.lecture.dto.LectureResponse
import com.teamsparta.democourse11.domain.course.repository.CourseRepository
import com.teamsparta.democourse11.domain.lecture.model.toResponse
import com.teamsparta.democourse11.domain.lecture.dto.UpdateLectureRequest
import com.teamsparta.democourse11.domain.lecture.model.Lecture
import com.teamsparta.democourse11.domain.lecture.model.toResponse
import com.teamsparta.democourse11.domain.lecture.repository.LectureRepository
import com.teamsparta.democourse11.domain.user.repository.UserRepository
import com.teamsparta.democourse11.exception.dto.ModelNotFoundException
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service


@Service//클래스 하나에 다하기도 한다. 상황따라 인터페이스 만들고 구현을 따로 하기도 한다.
class CourseServiceImpl(// 트랙센션- c,u,d에 어노테이션을 걸어준다.
    private val courseRepository: CourseRepository,//테스트 위해 임의로 h2 빌드에 적용하면 사용가능함.(트래센션)
    private val lectureRepository: LectureRepository,
    private val courseApplicationRepository: CourseApplicationRepository,
    private val userRepository: UserRepository,
): CourseService{
    //콜스서비스 상속
    override fun getAllCourseList(): List<CourseResponse> {
        //TODO: 만약 couresId 기반으로 해당하는 Course가 없다면 throw ModelNotFoundException
        // TODO: DB에서 모든 Course를 가져와서 CourseResponse로 변환 후 반환
    return courseRepository.findAll().map { it.toResponse() }
    }
//맥 커멘드+5= 상속받을 메소드 클릭하기
    override fun getCourseById(courseId: Long): CourseResponse {
        // TODO: DB에서 courseId에 해당하는 Course를 가져와서 CourseResponse로 변환 후 반환
        //TODO: 만약 couresId 기반으로 해당하는 Course가 없다면 throw ModelNotFoundException
    val course = courseRepository.findByIdOrNull(courseId) ?: throw ModelNotFoundException("Course", courseId)
    return course.toResponse()
}
  @Transactional
    override fun createCourse(request: CreateCourseRequest): CourseResponse {//예외- 아이디가 없을경우.
        // TODO: request를 Course로 변환 후 DB에 저장

      return courseRepository.save(
          Course(
              title = request.title,
              description = request.description,
              status = CourseStatus.OPEN,
          )
              ).toResponse()
  }//크리에이콜스도 세이브 하면 콜스가 저장이 되고, 저장이 되면 리턴으로 콜스 자체에 내용이 담겨서 나온다.
    @Transactional
    override fun updateCourse(courseId: Long, request: UpdateCourseRequest): CourseResponse {
        //TODO: 만약 couresId 기반으로 해당하는 Course가 없다면 throw ModelNotFoundException
        // TODO: DB에서 courseId에 해당하는 Course를 가져와서 request로 업데이트 후 DB에 저장, 결과를 CourseResponse로 변환 후 반환
        val course = courseRepository.findByIdOrNull(courseId) ?: throw ModelNotFoundException("Course", courseId)
        val (title, description) = request

        course.title = title
        course.description = description

        return courseRepository.save(course).toResponse()
    }

    @Transactional
    override fun deleteCourse(courseId: Long) {
        //TODO: 만약 couresId 기반으로 해당하는 Course가 없다면 throw ModelNotFoundException
        // TODO: DB에서 courseId에 해당하는 Course를 삭제, 연관된 CourseApplication과 Lecture도 모두 삭제
        val course = courseRepository.findByIdOrNull(courseId) ?: throw ModelNotFoundException("Course", courseId)
        courseRepository.delete(course)
    }

    //에러수정: 패키지구조 바꾸면서 코드에 반영된 것을 보지 못함.(임포트 바꾸는것에 신경씀) 인텔리제이 에러메시지 구글 검색 더 할 것.
     //        동일한 코드를 오버로딩두번해서 에러냄, 서비스 인터페이스에 서비스 임플에 있는 코드와 달라서 에러남.
    @Transactional //트랙색션 어노테이션: 성공하지 못했을시 롤백을 한다. 성공시 앱종료되도 DB에 저장한다.
    override fun addLecture(courseId: Long, request: AddLectureRequest): LectureResponse {
        // TODO: 만약 courseId에 해당하는 Course가 없다면 throw ModelNotFoundException
        // TODO: DB에서 courseId에 해당하는 Course를 가져와서 Lecture를 추가 후 DB에 저장, 결과를을 LectureResponse로 변환 후 반환
        val course = courseRepository.findByIdOrNull(courseId) ?: throw ModelNotFoundException("Course", courseId)

        val lecture = Lecture(
            title = request.title,
            videoUrl = request.videoUrl,
            course = course
        )
        // Course에 Lecture 추가
        course.addLecture(lecture)
        // Lecture에 영속성을 전파
        courseRepository.save(course)
        return lecture.toResponse()
    }
    //
    override fun getLecture(courseId: Long, lectureId: Long): LectureResponse {//콜스아이디와 렉쳐아이디 기반으로 다 가져오고,
        // TODO: 만약 courseId, lectureId에 해당하는 Lecture가 없다면 throw ModelNotFoundException
        // TODO: DB에서 courseId, lectureId에 해당하는 Lecture를 가져와서 LectureResponse로 변환 후 반환
        val lecture = lectureRepository.findByCourseIdAndId(courseId, lectureId)
            ?: throw ModelNotFoundException("Lecture", lectureId)//해당코스아이디 렉쳐아아디 조회하기에 렉쳐를 가져옴.
    //에러부분: val~ find~(lectureId)는 렉치리포지토리에 렉처아이디: 자료형 추가함. 렉처리포지토리 find~ ?(nullable 허용)
        return lecture.toResponse()
    }

    override fun getLectureList(courseId: Long): List<LectureResponse> {
        // TODO: 만약 courseId에 해당하는 Course가 없다면 throw ModelNotFoundException
        // TODO: DB에서 courseId에 해당하는 Course목록을 가져오고, 하위 lecture들을 가져온 다음, LectureResopnse로 변환해서 반환

        val course = courseRepository.findByIdOrNull(courseId) ?: throw ModelNotFoundException("Course", courseId)
        return course.lectures.map { it.toResponse() }
    }//코스아이디 렉쳐아이디 기반으로 다 가져와서 파인드로 다 찾아 비교를 합니다.

    @Transactional
    override fun updateLecture(
        courseId: Long,
        lectureId: Long,
        request: UpdateLectureRequest
    ): LectureResponse {
        // TODO: 만약 courseId, lectureId에 해당하는 Lecture가 없다면 throw ModelNotFoundException
        /* TODO: DB에서 courseId, lectureId에 해당하는 Lecture를 가져와서
            request로 업데이트 후 DB에 저장, 결과를을 LectureResponse로 변환 후 반환 */
        val lecture = lectureRepository.findByCourseIdAndId(courseId, lectureId) ?: throw ModelNotFoundException("Lecture", lectureId) //코스아이디와 렉쳐아이디 기반으로 조회한다음에

        val (title, videoUrl) = request
        lecture.title = title
        lecture.videoUrl = videoUrl

        return lectureRepository.save(lecture).toResponse()
    }

    @Transactional
    override fun removeLecture(courseId: Long, lectureId: Long) {
        // TODO: 만약 courseId에 해당하는 Course가 없다면 throw ModelNotFoundException
        // TODO: DB에서 courseId, lectureId에 해당하는 Lecture를 가져오고, 삭제
        val course = courseRepository.findByIdOrNull(courseId) ?: throw ModelNotFoundException("Course", courseId)
        val lecture = lectureRepository.findByIdOrNull(lectureId)
            ?: throw ModelNotFoundException("Lecture", lectureId)

        course.removeLecture(lecture)
        // Lecture에 영속성을 전파
        //  courseRepository.save(course)
        courseRepository.save(course)
    }

    @Transactional
    override fun applyCourse(courseId: Long, request: ApplyCourseRequest): CourseApplicationResponse {
        // TODO: 만약 courseId에 해당하는 Course가 없다면 throw ModelNotFoundException
        // TODO: 만약 course가 이미 마감됐다면, throw IllegalStateException
        // TODO: 이미 신청했다면, throw IllegalStateException
        val course = courseRepository.findByIdOrNull(courseId) ?: throw ModelNotFoundException("Course", courseId)
        val user = userRepository.findByIdOrNull(request.userId)
            ?: throw ModelNotFoundException("User", request.userId)
       //에러부분: 유저레포지토리 생성자로 넣지 않아 생김. 유저레포지토리 생성자로 넣음.

        // Course 마감여부 체크
        if (course.isClosed()) {
            throw IllegalStateException("Course is already closed. courseId: $courseId")
        }

        // CourseApplication 중복 체크
        if (courseApplicationRepository.existsByCourseIdAndUserId(courseId, request.userId)) {
            throw IllegalStateException("Already applied. courseId: $courseId, userId: ${request.userId}")
        }

        val courseApplication = CourseApplication(
            course = course,
            user = user,
        )
        course.addCourseApplication(courseApplication)
        // CourseApplication 영속성을 전파
        courseRepository.save(course)

        return courseApplication.toResponse()
    }

    override fun getCourseApplication(courseId: Long, applicationId: Long): CourseApplicationResponse {
        // TODO: 만약 courseId, applicationId에 해당하는 CourseApplication 없다면 throw ModelNotFoundException
        // TODO: DB에서 courseId, applicationId에 해당하는 CourseApplication 가져와서 CourseApplicationResponse 변환 후 반환
        val application = courseApplicationRepository.findByCourseIdAndId(courseId, applicationId)
            ?: throw ModelNotFoundException("CourseApplication", applicationId)

        return application.toResponse()
    }

    override fun getCourseApplicationList(courseId: Long): List<CourseApplicationResponse> {
        // TODO: 만약 courseId에 해당하는 Course 없다면 throw ModelNotFoundException
        // TODO: DB에서 courseId에 해당하는 Course 가져오고, 하위 courseApplication CourseApplicationResponse 변환 후 반환

        val course = courseRepository.findByIdOrNull(courseId) ?: throw ModelNotFoundException("Course", courseId)

        return course.courseApplications.map { it.toResponse() }
    }

    @Transactional
    override fun updateCourseApplicationStatus(
        courseId: Long,
        applicationId: Long,
        request: UpdateApplicationStatusRequest
    ): CourseApplicationResponse {
        // TODO: 만약 courseId, applicationId에 해당하는 CourseApplication이 없다면 throw ModelNotFoundException
        // TODO: 만약 status가 이미 변경된 상태면 throw IllegalStateException
        // TODO: Course의 status가 CLOSED상태 일시 throw IllegalStateException
        // TODO: 승인을 하는 케이스일 경우, course의 numApplicants와 maxApplicants가 동일하면, course의 상태를 CLOSED로 변경
        // TODO: DB에서 courseApplication을 가져오고, status를 request로 업데이트 후 DB에 저장, 결과를 CourseApplicationResponse로 변환 후 반환
        val course = courseRepository.findByIdOrNull(courseId) ?: throw ModelNotFoundException("Course", courseId)
        val application = courseApplicationRepository.findByCourseIdAndId(courseId, applicationId)
            ?: throw ModelNotFoundException("CourseApplication", applicationId)

        // 이미 승인 혹은 거절된 신청건인지 체크
        if (application.isProceeded()) {
            throw IllegalStateException("Application is already proceeded. applicationId: $applicationId")
        }

        // Course 마감여부 체크
        if (course.isClosed()) {
            throw IllegalStateException("Course is already closed. courseId: $courseId")
        }

        // 승인 ,거절 따른 처리
        when (request.status) {
            // 승인 일때
            CourseApplicationStatus.ACCEPTED.name -> {
                // 승인 처리
                application.accept()
                // Course 신청 인원 늘려줌
                course.addApplicant()
                // 만약 신청 인원이 꽉 찬다면 마감 처리
                if (course.isFull()) {
                    course.close()//정책상 코드가 필요함.
                }
                courseRepository.save(course) //가독성과 캡슐화를 위해?
            }

            // 거절 일때
            CourseApplicationStatus.REJECTED.name -> {
                // 거절 처리
                application.reject()
            }
            // 승인 거절이 아닌 다른 인자가 들어올 경우 에러 처리
            else -> {
                throw IllegalArgumentException("Invalid status: ${request.status}")
            }
        }

        return courseApplicationRepository.save(application).toResponse()
    }

}