package bakalarka.elearningplatform.model

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonRootName
import org.springframework.stereotype.Component
import javax.persistence.Entity
import javax.persistence.*

@Component
@Entity
@Table(name = "user")
@JsonRootName(value = "user")
data class User(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long? = null,
        val name : String,
        var surname : String,
        var email : String,
        var password : String,
        @OneToMany(
                mappedBy = "user",
                cascade = [CascadeType.PERSIST],
        )
        @OrderBy("id")
        @JsonIgnore
        var enrollment: MutableSet<Enrollment> = mutableSetOf()
        )