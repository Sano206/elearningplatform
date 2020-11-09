package bakalarka.elearningplatform.controller

import bakalarka.elearningplatform.model.Course
import bakalarka.elearningplatform.request.AddCourseRequest
import bakalarka.elearningplatform.request.AddInstructorRequest
import bakalarka.elearningplatform.service.CourseService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/courses")
class CourseController(
        var courseService: CourseService,
        var courses: MutableList<Course> = mutableListOf()) {

    @GetMapping("")
    fun getAll() = courseService.getAll()

    @PostMapping("/add")
    fun add(@RequestBody request: AddCourseRequest) = courseService.add(request)


}