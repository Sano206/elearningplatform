package bakalarka.elearningplatform.service

import bakalarka.elearningplatform.db.CourseRepository
import bakalarka.elearningplatform.db.EnrollmentRepository
import bakalarka.elearningplatform.model.Enrollment
import bakalarka.elearningplatform.request.AddEnrollmentRequest
import bakalarka.elearningplatform.request.UpdateProgressRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class EnrollmentService(
        var enrollmentRepository: EnrollmentRepository,
        var courseService: CourseService) {

    fun findByUserId(): MutableSet<Enrollment> {
        val id = UserService.getUserId()
        return enrollmentRepository.findByUserID(id)
    }

    fun add(request: AddEnrollmentRequest): Enrollment? {
        val (courseId) = request
        val userID = UserService.getUserId()
        if(courseService.findById(courseId).isPresent) {
            val course = courseService.findById(courseId).get()
            if(findByUserId().any { enrollment: Enrollment -> enrollment.course == course }){
                return null
            }
            return enrollmentRepository.save(Enrollment(userID = userID, course = course))
        }else{
            return null
        }

    }

    fun get(id: Long) = enrollmentRepository.findById(id)

    fun findByCourseId(id: Long): Enrollment? {
        val enrollments = findByUserId()
        return enrollments.find { enrollment -> enrollment.course.id == id }
    }

    fun update(enrollment: Enrollment) = enrollmentRepository.save(enrollment)

    fun changeChapterProgress(courseId: Long, chapterId: Long, request: UpdateProgressRequest): Enrollment? {
        val (value) = request
        val enrollment = findByCourseId(courseId)

        return if(enrollment?.course != null){
            val index = enrollment.course.courseChapters.indexOfFirst { courseChapter -> courseChapter.id == chapterId }
            enrollment.progress[index] = value
            return enrollmentRepository.save(enrollment)
        }else{
            null
        }
    }
}
