package bakalarka.elearningplatform.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import org.springframework.stereotype.Component
import javax.persistence.*

@Component
@Entity
@Table(name = "COURSE")
data class Course(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long? = null,
        var title: String,
        @Column(length = 15000)
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
                mappedBy = "userID",
                cascade = [CascadeType.PERSIST])
        @OrderBy("id")
        @JsonIgnoreProperties("course")
        var enrollments: MutableList<Enrollment> = mutableListOf()
)
