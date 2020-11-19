package bakalarka.elearningplatform.request

// TODO: GK - I wouldn't use default values
data class AddCourseChapterRequest(
        var chapterTitle: String = "",
        var description: String = "",
        var content: String = "",
        )
