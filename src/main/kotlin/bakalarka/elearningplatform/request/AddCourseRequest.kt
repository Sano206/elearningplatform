package bakalarka.elearningplatform.request

import bakalarka.elearningplatform.model.Instructor

data class AddCourseRequest(
        val instructor: Instructor,
        var title: String,
        var description: String = "",
        var fee: Long = 0,
        var language: String = ""
)