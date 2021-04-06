package bakalarka.elearningplatform.controller

import bakalarka.elearningplatform.request.AddEnrollmentRequest
import bakalarka.elearningplatform.service.EnrollmentService
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/enrollments")
class EnrollmentController(
        var enrollmentService: EnrollmentService,
) {

    @PostMapping("")
    @PreAuthorize("hasAuthority('open:courses')")
    fun add(@RequestBody request: AddEnrollmentRequest) = enrollmentService.add(request)

    @GetMapping("")
    @PreAuthorize("hasAuthority('open:courses')")
    fun getByUserId() = enrollmentService.findByUserId()

}
