package bakalarka.elearningplatform.actors.domain



data class AddInstructorRequest(
        val name : String,
        var surname : String,
        var email : String,
        var password : String,
        var introduction: String = "",
        var qualification: String = "",
        var avgReview: Long = 0L,
)