package bakalarka.elearningplatform.service

import bakalarka.elearningplatform.db.EnrollmentRepository
import bakalarka.elearningplatform.exceptions.BadRequestException
import bakalarka.elearningplatform.exceptions.ResourceNotFoundException
import bakalarka.elearningplatform.model.Enrollment
import bakalarka.elearningplatform.request.AddEnrollmentRequest
import bakalarka.elearningplatform.request.UpdateProgressRequest
import org.springframework.stereotype.Service
import java.util.*

@Service
class EnrollmentService(
    var enrollmentRepository: EnrollmentRepository,
    var courseService: CourseService
) {

    fun findByUserId(): MutableSet<Enrollment> {
        val id = UserService.getUserId()
        return enrollmentRepository.findByUserID(id)
    }

    fun add(request: AddEnrollmentRequest): Enrollment {
        val (courseId) = request
        val userID = UserService.getUserId()
        val enrollment = enrollmentRepository.findByUserIDAndCourseId(userID, courseId)
        val course = courseService.findById(courseId)
        if (enrollment.isPresent) {
            throw BadRequestException("User is already enrolled in this course.")
        }
        return enrollmentRepository.save(Enrollment(userID = userID, course = course))
    }

    fun get(id: Long) = enrollmentRepository.findById(id)

    fun findByCourseId(id: Long): Enrollment? {
        val enrollments = findByUserId()
        return enrollments.find { enrollment -> enrollment.course.id == id }
    }

    fun update(enrollment: Enrollment) = enrollmentRepository.save(enrollment)

    fun changeChapterProgress(courseId: Long, chapterId: Long, request: UpdateProgressRequest): Enrollment {
        val (value) = request
        val userID = UserService.getUserId()
        val enrollment = enrollmentRepository.findByUserIDAndCourseId(userID, courseId).orElseThrow { ResourceNotFoundException() }
        if (value) {
            enrollment.progress.add(chapterId)
        } else {
            enrollment.progress.remove(chapterId)
        }
        return enrollmentRepository.save(enrollment)
    }
}
