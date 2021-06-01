package bakalarka.elearningplatform.controller

import bakalarka.elearningplatform.request.UpdateUserRequest
import bakalarka.elearningplatform.service.UserService
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
class UserController(
    var userService: UserService
) {

    @PutMapping("")
    @PreAuthorize("true")
    fun update(@RequestBody request: UpdateUserRequest) = userService.update(request)
}
