package bakalarka.elearningplatform.request

import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

data class AddUserRequest(
        val name : String,
        var surname : String,
        var email : String,
        var password : String,
        var introduction: String = "",
        var qualification: String = "",
        var avgReview: Double = 0.0,
)
