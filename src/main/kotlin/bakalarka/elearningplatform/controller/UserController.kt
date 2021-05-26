package bakalarka.elearningplatform.controller

import bakalarka.elearningplatform.request.UpdateUserRequest
import bakalarka.elearningplatform.service.EnrollmentService
import bakalarka.elearningplatform.service.UserService
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
class UserController(
    var enrollmentService: EnrollmentService,
    var userService: UserService
) {
    @PutMapping("")
    @PreAuthorize("true")
    fun update(@RequestBody request: UpdateUserRequest) = userService.update(request)
}
