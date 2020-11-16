package bakalarka.elearningplatform.service

import bakalarka.elearningplatform.db.CourseRepository
import bakalarka.elearningplatform.db.EnrollmentRepository
import bakalarka.elearningplatform.db.UserRepository
import bakalarka.elearningplatform.model.Enrollment
import bakalarka.elearningplatform.model.User
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class EnrollmentService(
        var enrollmentRepository: EnrollmentRepository,
        var courseRepository: CourseRepository,
        var userRepository: UserRepository) {

    fun findByUserId(id: Long) = enrollmentRepository.findByUserId(id)

    fun add(userId: Long, courseId: Long) : Enrollment {
        val userCheck =  userRepository.findByIdOrNull(userId)
        val courseCheck = courseRepository.findByIdOrNull(courseId)
        // exception if(userCheck && courseCheck  == null) return
        return enrollmentRepository.save(Enrollment(user = userCheck, course = courseCheck))
    }

    fun get(id: Long) = enrollmentRepository.findById(id)

    fun update(enrollment: Enrollment) = enrollmentRepository.save(enrollment)
}