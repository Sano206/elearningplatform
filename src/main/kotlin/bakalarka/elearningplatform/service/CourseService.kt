package bakalarka.elearningplatform.service

import bakalarka.elearningplatform.db.CourseRepository
import bakalarka.elearningplatform.exceptions.AuthorizationException
import bakalarka.elearningplatform.exceptions.ResourceNotFoundException
import bakalarka.elearningplatform.model.Course
import bakalarka.elearningplatform.model.Topic
import bakalarka.elearningplatform.request.AddCourseRequest
import org.springframework.stereotype.Service

@Service
class CourseService(
    var courseRepository: CourseRepository,
    var instructorService: InstructorService,
    var userService: UserService,
) {

    fun getAll() = courseRepository.findAll().toMutableSet()

    fun findById(id: Long): Course = courseRepository.findById(id).orElseThrow { ResourceNotFoundException() }

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
                topic = Topic.valueOf(topic),
            )
        )
    }

    fun updateCourse(courseId: Long, request: AddCourseRequest): Course {
        val (title, description, shortDescription, thumbnail, fee, language, topic) = request
        val instructor = findById(courseId).instructor
        if (!userService.isOwnerOrAdmin(instructor)) throw AuthorizationException()
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
                topic = Topic.valueOf(topic)

            )
        )
    }

    fun deleteCourse(courseId: Long) {
        val course = courseRepository.findById(courseId).orElseThrow { ResourceNotFoundException("Course with ID $courseId doesn't exist.") }
        courseRepository.delete(course)
    }
}
