package bakalarka.elearningplatform.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import org.springframework.stereotype.Component
import java.time.LocalDate
import javax.persistence.*

@Component
@Entity
@Table(name = "ENROLLMENT")
class Enrollment(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long? = null,
        var startingDate: LocalDate? = LocalDate.now(),
        var finishedDate: LocalDate? = null,

        @ManyToOne(cascade = [CascadeType.PERSIST])
        @JoinColumn(name = "user_id", referencedColumnName = "id")
        @JsonIgnoreProperties("enrollment")
        var user: User?,

        @ManyToOne(cascade = [CascadeType.PERSIST])
        @JoinColumn(name = "course_id", referencedColumnName = "id")
        @JsonIgnoreProperties("enrollment")
        var course: Course?,
)