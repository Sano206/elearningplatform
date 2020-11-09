package bakalarka.elearningplatform.model

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.stereotype.Component
import javax.persistence.*

@Component
@Entity
@Table(name = "course")
data class Course(
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "instructor_id", nullable = true)
        @JsonIgnore
        val instructor: Instructor,
        var title: String,
        var description: String = "",
        var fee: Long,
        var language: String,
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long? = null,
        @OneToMany(
                mappedBy = "course",
                cascade = [CascadeType.PERSIST],
        )
        @OrderBy("id")
        val courseChapers: MutableSet<CourseChapter> = mutableSetOf(),

        @OneToMany(
                mappedBy = "user",
                cascade = [CascadeType.PERSIST])
        @OrderBy("id")
        @JsonIgnore
        var enrollments: MutableSet<Enrollment> = mutableSetOf()
        )