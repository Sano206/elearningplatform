package bakalarka.elearningplatform.db

import bakalarka.elearningplatform.model.Enrollment
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository


@Repository
interface EnrollmentRepository : CrudRepository<Enrollment, Long> {

    fun findByUserId(id: Long): MutableSet<Enrollment>
}
