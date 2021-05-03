package bakalarka.elearningplatform.db

import bakalarka.elearningplatform.model.Course
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface CourseRepository : CrudRepository<Course, Long> {

    fun findByTitle(title: String): MutableSet<Course>

    fun findByInstructor_UserID(userId: String): MutableSet<Course>
}
