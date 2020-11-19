package bakalarka.elearningplatform.controller

import bakalarka.elearningplatform.model.Enrollment
import bakalarka.elearningplatform.service.EnrollmentService
import org.springframework.data.web.JsonPath
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/enrollments")
class EnrollmentController(
        var enrollmentService: EnrollmentService,
) {
    // TODO: GK - formatter

    // TODO: GK - move to GET /users/{userId}/enrollments
    @GetMapping("/{userId}") //
    fun findByUserId(@PathVariable userId: Long) = enrollmentService.findByUserId(userId)

    // TODO: GK - either move to POST /users/{userId}/enrollments or under POST /enrollments (so path="") and send
    //              userId and courseId in request object
    @PostMapping("/add/{userId}/{courseId}")
    fun add(@PathVariable userId: Long, @PathVariable courseId: Long) = enrollmentService.add(userId, courseId)

}
