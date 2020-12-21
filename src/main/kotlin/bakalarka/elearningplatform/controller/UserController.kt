package bakalarka.elearningplatform.controller

import bakalarka.elearningplatform.request.AddUserRequest
import bakalarka.elearningplatform.service.EnrollmentService
import bakalarka.elearningplatform.service.UserService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
class UserController(
        var userService: UserService,
        var enrollmentService: EnrollmentService
) {

    @GetMapping("")
    fun getAll() = userService.getAll()

    @PostMapping("")
    fun add(@RequestBody request: AddUserRequest) = userService.add(request)

    @GetMapping("/{userId}")
    fun get(@PathVariable userId: Long) = userService.get(userId)

    @PutMapping("/{userId}")
    fun update(
            @PathVariable userId: Long,
            @RequestBody request: AddUserRequest) = userService.update(request, userId)

    @GetMapping("/{userId}/enrollments") //
    fun findByUserId(@PathVariable userId: Long) = enrollmentService.findByUserId(userId)

    @DeleteMapping("/{userId}")
    fun delete(@PathVariable userId: Long) = userService.delete(userId)
}
