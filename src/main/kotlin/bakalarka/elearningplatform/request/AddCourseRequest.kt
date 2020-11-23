package bakalarka.elearningplatform.request

data class AddCourseRequest(
        var title: String,
        var description: String,
        var fee: Long,
        var language: String
)