package bakalarka.elearningplatform.request

data class AddEnrollmentRequest(
        val userId: Long,
        val courseId: Long,
)