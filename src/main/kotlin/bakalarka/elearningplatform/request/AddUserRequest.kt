package bakalarka.elearningplatform.request

data class AddUserRequest(
        val name: String,
        var surname: String,
        var email: String,
        var password: String,
)
