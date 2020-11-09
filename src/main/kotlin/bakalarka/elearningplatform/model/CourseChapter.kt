package bakalarka.elearningplatform.model

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.stereotype.Component
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
                fetch = FetchType.LAZY,
                cascade = [CascadeType.PERSIST])
        @JoinColumn(name = "course_id", nullable = true)
        var course: Course,

)