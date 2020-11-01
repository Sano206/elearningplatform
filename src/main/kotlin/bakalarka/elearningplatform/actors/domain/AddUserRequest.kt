package bakalarka.elearningplatform.actors.domain

import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

data class AddUserRequest(
    val name: String,
    val surname: String,
    val email: String,
    val password: String,
    val numberOfCoursesEnrolled: Int = 0,
    val numberOfCoursesFinished: Int = 0,
)
