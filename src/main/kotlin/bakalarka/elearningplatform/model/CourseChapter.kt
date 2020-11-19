package bakalarka.elearningplatform.model

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import org.springframework.stereotype.Component
import java.util.*
import javax.persistence.*

@Component
@Entity
// TODO: GK - I would prefer course_chapters naming style, but apparently there isn't a single agreed on convention
@Table(name="courseChapter")
data class CourseChapter(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long? = null,
        var chapterTitle: String,
        var description: String = "",
        var content: String = "",
        @ManyToOne(
                fetch = FetchType.EAGER,
                cascade = [CascadeType.PERSIST])
        // TODO: GK - use the same convention for all database related names (either use courseId here or rename table to course_chapters)
        @JoinColumn(name = "course_id", nullable = true)
        @JsonIgnoreProperties("courseChapter")
        var course: Course?
        )
