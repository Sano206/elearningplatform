package bakalarka.elearningplatform.service

import bakalarka.elearningplatform.db.CourseRepository
import bakalarka.elearningplatform.db.EnrollmentRepository
import bakalarka.elearningplatform.model.Enrollment
import bakalarka.elearningplatform.request.AddEnrollmentRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class EnrollmentService(
        var enrollmentRepository: EnrollmentRepository,
        var courseRepository: CourseRepository) {

    fun findByUserId(id: String) = enrollmentRepository.findByUserID(id)

    fun add(request: AddEnrollmentRequest): Enrollment {
        val (courseId) = request
        val userID = UserService.getUserId()
        val courseCheck = courseRepository.findByIdOrNull(courseId) ?: throw Exception("Course doesn't exist!")
        return enrollmentRepository.save(Enrollment(userID = userID, course = courseCheck))
    }

    fun get(id: Long) = enrollmentRepository.findById(id)

    fun update(enrollment: Enrollment) = enrollmentRepository.save(enrollment)
}
