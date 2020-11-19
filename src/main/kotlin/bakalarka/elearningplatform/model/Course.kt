package bakalarka.elearningplatform.model

import com.fasterxml.jackson.annotation.JsonIgnore
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
        // TODO: GK - I would remove the default values if they are unnecessary (also in other models)
        var description: String = "",
        var fee: Long = 0,
        var language: String = "Slovak",
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
        // TODO: GK - fix typo
        // TODO: GK - this is a list - use plural name
        val courseChaper: MutableList<CourseChapter> = mutableListOf(),

        @OneToMany(
                mappedBy = "user",
                cascade = [CascadeType.PERSIST])
        @OrderBy("id")
        @JsonIgnoreProperties("course")
        // TODO: GK - this is a list - use plural name
        var enrollment: MutableList<Enrollment> = mutableListOf()
)
