package bakalarka.elearningplatform.controller

import bakalarka.elearningplatform.model.Course
import bakalarka.elearningplatform.request.AddCourseChapterRequest
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

    @GetMapping("/{courseId}")
    fun getOne(@PathVariable courseId: Long) = courseService.getOne(courseId)

    @PostMapping("/addCourse/{instructorId}")
    fun addCourse(
            @PathVariable instructorId : Long,
            @RequestBody request: AddCourseRequest) = courseService.addCourse(request, instructorId)

    @PostMapping("/{courseId}/addChapter")
    fun addChapeter(@PathVariable courseId : Long, @RequestBody request: AddCourseChapterRequest) = courseService.addChapter(request, courseId)


}