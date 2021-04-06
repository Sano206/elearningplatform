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

    fun update(enrollment: Enrollment) = enrollmentRepository.save(enrollment)
}
