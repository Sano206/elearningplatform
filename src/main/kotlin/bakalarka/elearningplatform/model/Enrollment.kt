package bakalarka.elearningplatform.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import org.springframework.stereotype.Component
import java.time.LocalDate
import javax.persistence.*

@Component
@Entity
@Table(name = "ENROLLMENT")
data class Enrollment(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long? = null,
        var startingDate: LocalDate? = LocalDate.now(),
        var finishedDate: LocalDate? = null,

        var userID: String,

        @ManyToOne(cascade = [CascadeType.PERSIST])
        @JoinColumn(name = "course_id")
        @JsonIgnoreProperties("enrollment")
        var course: Course,
){
        @ElementCollection
        var progress: MutableList<Boolean> = mutableListOf()

        init{
                for (chapter in course.courseChapters){
                        progress.add(false)
                }
        }
}