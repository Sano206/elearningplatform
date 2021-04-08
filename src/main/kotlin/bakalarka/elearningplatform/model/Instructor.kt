package bakalarka.elearningplatform.model

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import org.springframework.stereotype.Component
import javax.persistence.*


@Component
@Entity
@Table(name = "INSTRUCTOR")
class Instructor(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @JsonIgnore
        var id: Long? = null,
        val userID: String,
        @Column(length = 15000)
        var introduction: String,
        @Column(length = 15000)
        var qualification: String,

        @OneToMany(
                mappedBy = "instructor",
                cascade = [CascadeType.PERSIST],
        )
        @OrderBy("id")
        @JsonIgnoreProperties("instructor")
        val courses: MutableList<Course> = mutableListOf()
)
