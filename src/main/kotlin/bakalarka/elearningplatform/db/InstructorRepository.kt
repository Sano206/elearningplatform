package bakalarka.elearningplatform.db

import bakalarka.elearningplatform.model.Enrollment
import bakalarka.elearningplatform.model.Instructor
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface InstructorRepository : CrudRepository<Instructor, Long>{

    fun findByUserID(userID: String): Instructor?

}