package bakalarka.elearningplatform.request

data class AddCourseRequest(
    var title: String,
    var description: String,
    var shortDescription: String,
    var thumbnail: String,
    var fee: Float,
    var language: String,
    var topic: String,
)
