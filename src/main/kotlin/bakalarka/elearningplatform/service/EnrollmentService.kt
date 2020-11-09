package bakalarka.elearningplatform.service

import bakalarka.elearningplatform.db.EnrollmentRepository
import bakalarka.elearningplatform.model.Enrollment
import org.springframework.stereotype.Service

@Service
class EnrollmentService(var enrollmentRepository: EnrollmentRepository) {

    fun getAll(id: Long) = enrollmentRepository.findByUserId(id)
/*
    fun add(userId: Long, courseId: Long) : Enrollment {
        return enrollmentRepository.save(userId, courseId)
    }
*/
    fun get(id: Long) = enrollmentRepository.findById(id)

    fun update(enrollment: Enrollment) = enrollmentRepository.save(enrollment)
}