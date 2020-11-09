package bakalarka.elearningplatform.db

import bakalarka.elearningplatform.model.Course
import bakalarka.elearningplatform.model.Enrollment
import bakalarka.elearningplatform.model.Instructor
import bakalarka.elearningplatform.model.User
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import sun.security.ec.point.ProjectivePoint

@Repository
interface UserRepository : CrudRepository<User, Long>

@Repository
interface InstructorRepository : CrudRepository<Instructor, Long>

@Repository
interface CourseRepository : CrudRepository<Course, Long>

@Repository
interface EnrollmentRepository : CrudRepository<Enrollment, Long>{

    fun findByUserId(id: Long) : MutableSet<Enrollment>
}