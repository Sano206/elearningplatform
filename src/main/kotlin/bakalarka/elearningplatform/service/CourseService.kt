package bakalarka.elearningplatform.service

import bakalarka.elearningplatform.db.CourseRepository
import bakalarka.elearningplatform.model.Course
import bakalarka.elearningplatform.model.TOPIC
import bakalarka.elearningplatform.request.AddCourseRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class CourseService(
    var courseRepository: CourseRepository,
    var instructorService: InstructorService
) {

    fun getAll() = courseRepository.findAll().toMutableSet()

    fun findById(id: Long) = courseRepository.findById(id)

    fun findByUserId(): MutableSet<Course> {
        val userId = UserService.getUserId()
        return courseRepository.findByInstructor_UserID(userId)
    }

    fun findByTitle(title: String): MutableSet<Course> = courseRepository.findByTitle(title)

    fun addCourse(request: AddCourseRequest): Course {
        val (title, description, shortDescription, thumbnail, fee, language, topic) = request
        val instructor = instructorService.findByUserID(UserService.getUserId())
        return courseRepository.save(
            Course(
                instructor = instructor,
                title = title,
                description = description,
                thumbnail = thumbnail,
                shortDescription = shortDescription,
                fee = fee,
                language = language,
                topic = TOPIC.valueOf(topic),
            )
        )
    }

    fun updateCourse(courseId: Long, request: AddCourseRequest): Course? {
        val (title, description, shortDescription, thumbnail, fee, language, topic) = request
        val instructor = courseRepository.findByIdOrNull(courseId)?.instructor ?: return null
        val userId = UserService.getUserId()
        if (instructor.userID != userId) return null
        return courseRepository.save(
            Course(
                id = courseId,
                instructor = instructor,
                title = title,
                description = description,
                thumbnail = thumbnail,
                shortDescription = shortDescription,
                fee = fee,
                language = language,
                topic = TOPIC.valueOf(topic)

            )
        )
    }

    fun deleteCourse(courseId: Long) {
        val course = courseRepository.findByIdOrNull(courseId) ?: throw IllegalArgumentException()
        courseRepository.delete(course)
    }
}
