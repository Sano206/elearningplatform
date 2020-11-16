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
        var courses: MutableList<Course> = mutableListOf()) {

    @GetMapping("")
    fun getAll() = courseService.getAll()

    @GetMapping("/{courseId}")
    fun getById(@PathVariable courseId: Long) = courseService.findById(courseId)

 /*   @GetMapping("/{title}")
    fun getByTitle(@PathVariable title: String) = courseService.findByCourseTitle(title)
*/
    @PostMapping("/addCourse/{instructorId}")
    fun addCourse(
            @PathVariable instructorId : Long,
            @RequestBody request: AddCourseRequest) = courseService.addCourse(request, instructorId)

    @PostMapping("/{courseId}/addChapter")
    fun addChapter(
            @PathVariable courseId : Long,
            @RequestBody request: AddCourseChapterRequest) = courseService.addChapter(request, courseId)


}