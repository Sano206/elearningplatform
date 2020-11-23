package bakalarka.elearningplatform.db

import bakalarka.elearningplatform.model.User
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : CrudRepository<User, Long>