package bakalarka.elearningplatform.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import org.springframework.stereotype.Component
import javax.persistence.*


@Component
@Entity
@Table(name = "instructor")
class Instructor(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long? = null,
        @OneToOne(cascade = [CascadeType.ALL])
        @JsonIgnoreProperties("enrollments")
        var user: User,
        var introduction: String,
        var qualification: String,

        @OneToMany(
                mappedBy = "instructor",
                cascade = [CascadeType.PERSIST],
        )
        @OrderBy("id")
        @JsonIgnoreProperties("instructor")
        val courses: MutableList<Course> = mutableListOf()
)
