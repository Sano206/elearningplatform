package bakalarka.elearningplatform.request

import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

data class AddUserRequest(
        val name: String,
        val surname: String,
        val email: String,
        val password: String,
)
