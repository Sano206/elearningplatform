package bakalarka.elearningplatform.model

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonManagedReference
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
        @JsonIgnore
        val courseChaper: MutableSet<CourseChapter> = mutableSetOf(),

        @OneToMany(
                mappedBy = "user",
                cascade = [CascadeType.PERSIST])
        @OrderBy("id")
        @JsonIgnore
        var enrollment: MutableSet<Enrollment> = mutableSetOf()
        )