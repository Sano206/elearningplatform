package bakalarka.elearningplatform.request

data class AddCourseChapterRequest(
    var title: String,
    var description: String,
    var content: String,
    var position: Int,
)
