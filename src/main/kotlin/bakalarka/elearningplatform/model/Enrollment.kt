package bakalarka.elearningplatform.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import org.springframework.stereotype.Component
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Component
@Entity
@Table(name = "enrollment")
class Enrollment(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long? = null,
        var startingDate: LocalDate? = LocalDate.now(),
        var finishedDate: LocalDate?,

        @ManyToOne(cascade = [CascadeType.PERSIST])
        @JoinColumn(name = "user_id", referencedColumnName = "id")
        var user: User,

        @ManyToOne(cascade = [CascadeType.PERSIST])
        @JoinColumn(name = "course_id", referencedColumnName = "id")
        @JsonIgnoreProperties("enrollment")
        var course: Course,
)