package bakalarka.elearningplatform.actors.web

import bakalarka.elearningplatform.actors.domain.AddUserRequest
import bakalarka.elearningplatform.actors.domain.User
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
class UserController(
        var userService: UserService,
        private val users: MutableList<User> = mutableListOf()
) {


    @GetMapping("")
    fun getAll() = userService.getAll()

    @PostMapping("/add")
    fun add(@RequestBody request: AddUserRequest) = userService.add(request)

    @GetMapping("/{id}")
    fun get(@PathVariable id: Long) = userService.get(id)

    @PutMapping("/update")
    fun update(@RequestBody request: User) = userService.update(request)



    }
