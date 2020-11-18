package bakalarka.elearningplatform.controller

import bakalarka.elearningplatform.request.AddUserRequest
import bakalarka.elearningplatform.model.User
import bakalarka.elearningplatform.service.UserService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
class UserController(
        var userService: UserService,
) {


    @GetMapping("")
    fun getAll() = userService.getAll()

    @PostMapping("/add")
    fun add(@RequestBody request: AddUserRequest) = userService.add(request)

    @GetMapping("/{id}")
    fun get(@PathVariable id: Long) = userService.get(id)

    @PutMapping("/{userId}/update")
    fun update(
            @PathVariable userId: Long,
            @RequestBody request: AddUserRequest) = userService.update(request,userId)



    }
