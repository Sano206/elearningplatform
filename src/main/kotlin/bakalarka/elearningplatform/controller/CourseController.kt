package bakalarka.elearningplatform.controller

import bakalarka.elearningplatform.model.Course
import bakalarka.elearningplatform.request.AddCourseChapterRequest
import bakalarka.elearningplatform.request.AddCourseRequest
import bakalarka.elearningplatform.service.CourseService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/courses")
class CourseController(
        var courseService: CourseService,
) {

    @GetMapping("")
    fun getAll() = courseService.getAll()

    @GetMapping("/{courseId}")
    fun getById(@PathVariable courseId: Long) = courseService.findById(courseId)

    @GetMapping("/find/{title}")
    fun findByTitle(@PathVariable title: String) = courseService.findByTitle(title)

    @PostMapping("/addCourse/{instructorId}")
    fun addCourse(
            @PathVariable instructorId : Long,
            @RequestBody request: AddCourseRequest) = courseService.addCourse(request, instructorId)

    @PutMapping("/{courseId}/update")
    fun updateCourse(
            @PathVariable courseId: Long,
            @RequestBody request: AddCourseRequest)= courseService.updateCourse(courseId, request)


}