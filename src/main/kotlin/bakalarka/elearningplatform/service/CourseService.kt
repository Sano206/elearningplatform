package bakalarka.elearningplatform.service

import bakalarka.elearningplatform.db.CourseChapterRepository
import bakalarka.elearningplatform.db.CourseRepository
import bakalarka.elearningplatform.db.InstructorRepository
import bakalarka.elearningplatform.model.Course
import bakalarka.elearningplatform.model.CourseChapter
import bakalarka.elearningplatform.request.AddCourseChapterRequest
import bakalarka.elearningplatform.request.AddCourseRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import kotlin.jvm.Throws

@Service
class CourseService(
        var courseRepository: CourseRepository,
        var instructorRepository: InstructorRepository
) {

    fun getAll() = courseRepository.findAll().toList()

    fun findById(id: Long) = courseRepository.findById(id)

    fun findByTitle(title: String) = courseRepository.findByTitle(title)

    fun addCourse(request: AddCourseRequest, instructorId: Long) : Course{
        val(title, description, fee, language) = request
        return courseRepository.save(Course(
                instructor = instructorRepository.findByIdOrNull(instructorId),
                title = title,
                description = description,
                fee = fee,
                language = language))
    }

    fun updateCourse(courseId: Long, request: AddCourseRequest): Course? {
        val(title, description, fee, language) = request
        val instructor = courseRepository.findByIdOrNull(courseId)?.instructor ?: return null
        return courseRepository.save(Course(
                id = courseId,
                instructor = instructor,
                title = title,
                description = description,
                fee = fee,
                language = language))
    }
}