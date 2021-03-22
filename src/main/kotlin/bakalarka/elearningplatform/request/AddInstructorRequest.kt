package bakalarka.elearningplatform.request

data class AddInstructorRequest(
        var name: String,
        var surname: String,
        var introduction: String,
        var qualification: String,
)