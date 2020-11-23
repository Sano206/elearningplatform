package bakalarka.elearningplatform.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import org.springframework.stereotype.Component
import javax.persistence.*

@Component
@Entity
@Table(name = "user")
data class User(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long? = null,
        val name: String,
        var surname: String,
        var email: String,
        var password: String,
        @OneToMany(
                mappedBy = "user",
                cascade = [CascadeType.PERSIST],
        )
        @OrderBy("id")
        @JsonIgnoreProperties("user")
        var enrollments: MutableList<Enrollment> = mutableListOf()
)
