package bakalarka.elearningplatform.controller

import bakalarka.elearningplatform.request.AddEnrollmentRequest
import bakalarka.elearningplatform.request.UpdateProgressRequest
import bakalarka.elearningplatform.service.EnrollmentService
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/enrollments")
class EnrollmentController(
    var enrollmentService: EnrollmentService,
) {

    @PostMapping("")
    @PreAuthorize("hasAuthority('open:courses')")
    fun add(@RequestBody request: AddEnrollmentRequest) = ResponseEntity.ok(enrollmentService.add(request))

    @GetMapping("")
    @PreAuthorize("hasAuthority('open:courses')")
    fun getByUserId() = enrollmentService.findByUserId()

    @GetMapping("/{courseId}")
    @PreAuthorize("hasAuthority('open:courses')")
    fun getByCourseId(
        @PathVariable courseId: Long
    ) = enrollmentService.findByCourseId(courseId)

    @PutMapping("/{courseId}/{chapterId}")
    @PreAuthorize("hasAuthority('open:courses')")
    fun changeChapterProgress(
        @PathVariable courseId: Long,
        @PathVariable chapterId: Long,
        @RequestBody request: UpdateProgressRequest
    ) = ResponseEntity.ok(enrollmentService.changeChapterProgress(courseId, chapterId, request))
}
