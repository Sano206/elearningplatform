package bakalarka.elearningplatform.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import org.springframework.stereotype.Component
import javax.persistence.*

@Component
@Entity
@Table(name = "USER")
data class User(
        @Id
        var id: Long? = null,
        @OneToMany(
                mappedBy = "user",
                cascade = [CascadeType.PERSIST],
        )
        @OrderBy("id")
        @JsonIgnoreProperties("user")
        var enrollments: MutableList<Enrollment> = mutableListOf()
)
