package bakalarka.elearningplatform.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import org.springframework.stereotype.Component
import javax.persistence.*

@Component
@Entity
@Table(name = "course")
data class Course(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long? = null,
        var title: String,
        var description: String,
        var fee: Long,
        var language: String,
        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "instructor_id", nullable = true)
        @JsonIgnoreProperties("course")
        val instructor: Instructor?,

        @OneToMany(
                mappedBy = "course",
                cascade = [CascadeType.PERSIST],
        )
        @OrderBy("id")
        @JsonIgnoreProperties("course")
        val courseChapters: MutableList<CourseChapter> = mutableListOf(),

        @OneToMany(
                mappedBy = "user",
                cascade = [CascadeType.PERSIST])
        @OrderBy("id")
        @JsonIgnoreProperties("course")
        var enrollments: MutableList<Enrollment> = mutableListOf()
)
