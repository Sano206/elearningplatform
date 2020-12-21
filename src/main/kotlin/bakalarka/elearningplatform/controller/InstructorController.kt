package bakalarka.elearningplatform.controller

import bakalarka.elearningplatform.request.AddCourseRequest
import bakalarka.elearningplatform.request.AddInstructorRequest
import bakalarka.elearningplatform.request.AddUserRequest
import bakalarka.elearningplatform.service.CourseService
import bakalarka.elearningplatform.service.InstructorService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/instructors")
class InstructorController(
        var instructorService: InstructorService,
        var courseService: CourseService
) {

    @GetMapping("")
    fun getAll() = instructorService.getAll()

/*    @PostMapping("")
    fun add(@RequestBody request: AddInstructorRequest) = instructorService.add(request)*/

    @GetMapping("/{instructorId}")
    fun get(@PathVariable instructorId: Long) = instructorService.get(instructorId)

/*    @PutMapping("/{instructorId}")
    fun update(
            @PathVariable instructorId: Long,
            @RequestBody request: AddInstructorRequest) = instructorService.update(request, instructorId)*/

    @PostMapping("/{instructorId}/courses")
    fun addCourse(
            @PathVariable instructorId: Long,
            @RequestBody request: AddCourseRequest
    ) = courseService.addCourse(request, instructorId)
}
