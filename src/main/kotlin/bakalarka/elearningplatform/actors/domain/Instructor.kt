package bakalarka.elearningplatform.actors.domain

import org.springframework.stereotype.Component
import javax.persistence.*


@Component
@Entity
@Table(name = "instructors")
class Instructor(
        val name : String,
        var surname : String,
        var email : String,
        var password : String,
        var introduction: String,
        var qualification: String,
        var avgReview: Long = 0L,
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long? = null,
){

}