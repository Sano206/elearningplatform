package bakalarka.elearningplatform.controller

// TODO: GK - remove unused imports
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
    // TODO: GK - formatter

    @GetMapping("")
    fun getAll() = courseService.getAll()

    @GetMapping("/{courseId}")
    fun getById(@PathVariable courseId: Long) = courseService.findById(courseId)

    // TODO: GK - merge with GET /courses, use @RequestParam instead, make title optional and either get all or filter
    @GetMapping("/find/{title}")
    fun findByTitle(@PathVariable title: String) = courseService.findByTitle(title)

    // TODO: GK - move to /instructors/{instructorId}/courses
    @PostMapping("/addCourse/{instructorId}")
    fun addCourse(
        @PathVariable instructorId: Long,
        @RequestBody request: AddCourseRequest
    ) = courseService.addCourse(request, instructorId)

    // TODO: GK - set path to /{courseId}
    @PutMapping("/{courseId}/update")
    fun updateCourse(
        @PathVariable courseId: Long,
        @RequestBody request: AddCourseRequest
    ) = courseService.updateCourse(courseId, request)

    // TODO: GK - clean up the empty lines
}
