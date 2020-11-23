package bakalarka.elearningplatform.service

import bakalarka.elearningplatform.db.CourseRepository
import bakalarka.elearningplatform.db.EnrollmentRepository
import bakalarka.elearningplatform.db.UserRepository
import bakalarka.elearningplatform.model.Enrollment
import bakalarka.elearningplatform.request.AddEnrollmentRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class EnrollmentService(
        var enrollmentRepository: EnrollmentRepository,
        var courseRepository: CourseRepository,
        var userRepository: UserRepository) {

    fun findByUserId(id: Long) = enrollmentRepository.findByUserId(id)

    fun add(request: AddEnrollmentRequest): Enrollment {
        val (userId, courseId) = request
        val userCheck = userRepository.findByIdOrNull(userId) ?: throw Exception("User doesn't exist!")
        val courseCheck = courseRepository.findByIdOrNull(courseId) ?: throw Exception("Course doesn't exist!")
        return enrollmentRepository.save(Enrollment(user = userCheck, course = courseCheck))
    }

    fun get(id: Long) = enrollmentRepository.findById(id)

    fun update(enrollment: Enrollment) = enrollmentRepository.save(enrollment)
}
