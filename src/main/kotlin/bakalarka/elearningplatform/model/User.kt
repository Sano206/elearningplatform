package bakalarka.elearningplatform.model

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.stereotype.Component
import javax.persistence.Entity
import javax.persistence.*

@Component
@Entity
@Table(name = "user")
data class User(
        val name : String,
        var surname : String,
        var email : String,
        var password : String,
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long? = null,
        @OneToMany(
                mappedBy = "user",
                cascade = [CascadeType.PERSIST],
        )
        @OrderBy("id")
        @JsonIgnore
        var enrollments: MutableSet<Enrollment> = mutableSetOf()
        )