package bakalarka.elearningplatform.controller

import bakalarka.elearningplatform.request.InstructorRequest
import bakalarka.elearningplatform.service.InstructorService
import bakalarka.elearningplatform.service.UserService
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/instructors")
class InstructorController(
    var instructorService: InstructorService,
) {

    @PostMapping("")
    @PreAuthorize("hasAuthority('create:user')")
    fun add(@RequestBody request: InstructorRequest) = instructorService.add(request)

    @GetMapping("")
    @PreAuthorize("hasAuthority('get:user')")
    fun get() = instructorService.findByUserID(UserService.getUserId())

    @PutMapping("")
    fun update(@RequestBody request: InstructorRequest) = instructorService.update(request)
}
