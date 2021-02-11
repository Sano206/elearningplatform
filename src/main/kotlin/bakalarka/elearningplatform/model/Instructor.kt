package bakalarka.elearningplatform.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import org.springframework.stereotype.Component
import javax.persistence.*


@Component
@Entity
@Table(name = "INSTRUCTOR")
class Instructor(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long? = null,
        @OneToOne(cascade = [CascadeType.PERSIST])
        @JsonIgnoreProperties("enrollments")
        var user: User,
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
