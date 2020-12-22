package bakalarka.elearningplatform.controller

import bakalarka.elearningplatform.model.Course
import bakalarka.elearningplatform.request.AddCourseChapterRequest
import bakalarka.elearningplatform.request.AddCourseRequest
import bakalarka.elearningplatform.service.ChapterService
import bakalarka.elearningplatform.service.CourseService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/courses")
class CourseController(
        var courseService: CourseService,
        var chapterService: ChapterService
) {

    @GetMapping("/{courseId}")
    fun getById(@PathVariable courseId: Long) = courseService.findById(courseId)

    @GetMapping("")
    fun findByTitle(@RequestParam(required = false) title: String?): MutableSet<Course> {
        return if (title == null) {
            courseService.getAll()
        } else {
            courseService.findByTitle(title)
        }
    }

    @PutMapping("/{courseId}")
    fun updateCourse(
            @PathVariable courseId: Long,
            @RequestBody request: AddCourseRequest
    ) = courseService.updateCourse(courseId, request)

    @PostMapping("/{courseId}/chapters")
    fun addChapter(
            @PathVariable courseId: Long,
            @RequestBody request: AddCourseChapterRequest) = chapterService.addChapter(request, courseId)

    @DeleteMapping("/{courseId}")
    fun deleteCourse(
            @PathVariable courseId: Long) = courseService.deleteCourse(courseId = courseId)
}
