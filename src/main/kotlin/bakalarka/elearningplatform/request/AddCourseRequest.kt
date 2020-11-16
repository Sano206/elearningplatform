package bakalarka.elearningplatform.request

import bakalarka.elearningplatform.model.Instructor

data class AddCourseRequest(
        //val instructorId: Long,
        var title: String,
        var description: String = "",
        var fee: Long = 0,
        var language: String = ""
)