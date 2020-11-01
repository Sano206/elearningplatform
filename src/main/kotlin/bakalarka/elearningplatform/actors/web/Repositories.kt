package bakalarka.elearningplatform.actors.web

import bakalarka.elearningplatform.actors.domain.Instructor
import bakalarka.elearningplatform.actors.domain.User
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : CrudRepository<User, Long> {
}

@Repository
interface InstructorRepository : CrudRepository<Instructor, Long>