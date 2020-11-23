package bakalarka.elearningplatform.controller

import bakalarka.elearningplatform.request.AddEnrollmentRequest
import bakalarka.elearningplatform.service.EnrollmentService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/enrollments")
class EnrollmentController(
        var enrollmentService: EnrollmentService,
) {

    @PostMapping("")
    fun add(@RequestBody request: AddEnrollmentRequest) = enrollmentService.add(request)
}
