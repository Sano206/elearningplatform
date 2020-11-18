package bakalarka.elearningplatform.request

data class AddCourseChapterRequest(
        var chapterTitle: String = "",
        var description: String = "",
        var content: String = "",
        )
