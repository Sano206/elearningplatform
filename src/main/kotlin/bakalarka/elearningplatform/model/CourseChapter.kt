package bakalarka.elearningplatform.model

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import org.springframework.stereotype.Component
import java.util.*
import javax.persistence.*

@Component
@Entity
@Table(name="courseChapter")
data class CourseChapter(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long? = null,
        var chapterTitle: String,
        var description: String = "",
        @ManyToOne(
                fetch = FetchType.EAGER,
                cascade = [CascadeType.PERSIST])
        @JoinColumn(name = "course_id", nullable = true)
        @JsonIgnore
        var course: Course?,

        )