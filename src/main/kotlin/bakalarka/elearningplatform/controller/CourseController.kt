package bakalarka.elearningplatform.controller

import bakalarka.elearningplatform.model.Course
import bakalarka.elearningplatform.request.AddCourseChapterRequest
import bakalarka.elearningplatform.request.AddCourseRequest
import bakalarka.elearningplatform.service.ChapterService
import bakalarka.elearningplatform.service.CourseService
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/courses")
class CourseController(
    var courseService: CourseService,
    var chapterService: ChapterService
) {

    @GetMapping("/{courseId}")
    fun getById(@PathVariable courseId: Long) = courseService.findById(courseId)

    @GetMapping("/instructor")
    @PreAuthorize("hasAuthority('update:courses')")
    fun getByUserId() = courseService.findByUserId()

    @GetMapping("")
    fun findByTitle(@RequestParam(required = false) title: String?): MutableSet<Course> {
        return if (title == null) {
            courseService.getAll()
        } else {
            courseService.findByTitle(title)
        }
    }

    @PutMapping("/{courseId}")
    @PreAuthorize("hasAuthority('update:courses')")
    fun updateCourse(
        @PathVariable courseId: Long,
        @RequestBody request: AddCourseRequest
    ) = ResponseEntity.ok(courseService.updateCourse(courseId, request))

    @PostMapping("")
    @PreAuthorize("hasAuthority('create:courses')")
    fun addCourse(
        @RequestBody request: AddCourseRequest
    ) = ResponseEntity.ok(courseService.addCourse(request))

    @PostMapping("/{courseId}/chapters")
    @PreAuthorize("hasAuthority('create:courses')")
    fun addChapter(
        @PathVariable courseId: Long,
        @RequestBody request: AddCourseChapterRequest
    ) = chapterService.addChapter(request, courseId)

    @DeleteMapping("/{courseId}")
    @PreAuthorize("hasAuthority('create:courses')")
    fun deleteCourse(
        @PathVariable courseId: Long
    ) = courseService.deleteCourse(courseId = courseId)
}
