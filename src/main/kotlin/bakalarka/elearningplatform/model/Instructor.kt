package bakalarka.elearningplatform.model

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.stereotype.Component
import javax.persistence.*


@Component
@Entity
@Table(name = "instructor")
class Instructor(
        val name : String,
        var surname : String,
        var email : String,
        var password : String,
        var introduction: String,
        var qualification: String,
        var avgReview: Double = 0.0,
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long? = null,
        @OneToMany(
                mappedBy = "instructor",
                cascade = [CascadeType.PERSIST],
        )
        @OrderBy("id")
        @JsonIgnore
        val courses: MutableSet<Course> = mutableSetOf()
){

}