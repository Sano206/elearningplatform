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

@Service
class CourseService(
        var courseRepository: CourseRepository,
        var courseChapterRepository: CourseChapterRepository,
        var instructorRepository: InstructorRepository
) {

    fun getAll() = courseRepository.findAll().toList()

    fun findById(id: Long) = courseRepository.findById(id)

//    fun findByCourseTitle(title: String) = courseRepository.findByCourseTitle(title)

    fun addCourse(request: AddCourseRequest, instructorId: Long) : Course{
        val(title, description, fee, language) = request
        return courseRepository.save(Course(
                instructor = instructorRepository.findByIdOrNull(instructorId),
                title = title,
                description = description,
                fee = fee,
                language = language))
    }

    fun addChapter(request: AddCourseChapterRequest, courseId: Long) : CourseChapter{
        val(chapterTitle) = request
         return courseChapterRepository.save(CourseChapter(
                chapterTitle = chapterTitle,
                course = courseRepository.findByIdOrNull(courseId)))
    }
}