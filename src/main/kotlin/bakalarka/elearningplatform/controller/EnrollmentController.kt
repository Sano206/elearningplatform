package bakalarka.elearningplatform.controller

import bakalarka.elearningplatform.model.Enrollment
import bakalarka.elearningplatform.service.EnrollmentService
import org.springframework.data.web.JsonPath
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/enrollments")
class EnrollmentController(
        var enrollmentService: EnrollmentService,
        var enrollments: MutableSet<Enrollment> = mutableSetOf()
) {

    @GetMapping("/{userId}") //
    fun findByUserId(@PathVariable userId: Long) = enrollmentService.findByUserId(userId)

    @PostMapping("/add/{userId}/{courseId}")
    fun add(@PathVariable userId: Long, @PathVariable courseId: Long) = enrollmentService.add(userId, courseId)

}