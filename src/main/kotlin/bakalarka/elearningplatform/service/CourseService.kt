package bakalarka.elearningplatform.service

import bakalarka.elearningplatform.db.CourseRepository
import bakalarka.elearningplatform.model.Course
import bakalarka.elearningplatform.request.AddCourseRequest
import org.springframework.stereotype.Service

@Service
class CourseService(var courseRepository: CourseRepository) {

    fun getAll() = courseRepository.findAll().toList()

    fun add(request: AddCourseRequest) : Course{
        val(instructor, title, description, fee, language) = request
        return courseRepository.save(Course(instructor, title, description, fee, language))
    }

}