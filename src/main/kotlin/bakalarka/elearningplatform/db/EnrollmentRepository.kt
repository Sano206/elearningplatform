package bakalarka.elearningplatform.db

import bakalarka.elearningplatform.model.Enrollment
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface EnrollmentRepository : CrudRepository<Enrollment, Long> {

    fun findByUserID(userID: String): MutableSet<Enrollment>

    fun findByUserIDAndCourseId(userID: String, courseId: Long): Optional<Enrollment>
}
