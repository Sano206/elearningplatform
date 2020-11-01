package bakalarka.elearningplatform.actors.domain

import org.springframework.stereotype.Component
import javax.persistence.Entity
import javax.persistence.*

@Component
@Entity
@Table(name = "users")
class User(
        val name : String,
        var surname : String,
        var email : String,
        var password : String,
        var numberOfCoursesEnrolled: Int = 0,
        var numberOfCoursesFinished: Int = 0,
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long? = null,){

}