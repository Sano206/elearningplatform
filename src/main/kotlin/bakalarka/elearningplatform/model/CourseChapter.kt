package bakalarka.elearningplatform.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import org.springframework.stereotype.Component
import javax.persistence.*

@Component
@Entity
@Table(name = "COURSE_CHAPTER")
data class CourseChapter(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long? = null,
        var chapterTitle: String,
        @Column(length = 15000)
        var description: String,
        var content: String,
        @ManyToOne(
                fetch = FetchType.EAGER,
                cascade = [CascadeType.PERSIST])
        @JoinColumn(name = "course_id", nullable = true)
        @JsonIgnoreProperties("courseChapter")
        var course: Course?
)
