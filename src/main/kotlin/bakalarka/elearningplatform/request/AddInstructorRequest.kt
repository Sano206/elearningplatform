package bakalarka.elearningplatform.request

data class AddInstructorRequest(
        val name: String,
        var surname: String,
        var email: String,
        var password: String,
        var introduction: String,
        var qualification: String,
)